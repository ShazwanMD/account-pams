package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.dao.AcAccountDao;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeTransaction;
import my.edu.umk.pams.account.account.model.AcAccountChargeTransactionImpl;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcAccountTransaction;
import my.edu.umk.pams.account.account.model.AcAccountTransactionCode;
import my.edu.umk.pams.account.account.model.AcAccountTransactionImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.dao.AcReceiptDao;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.model.AcAdvancePaymentImpl;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.event.AccessListener;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PAMS
 */
@Component("receiptListener")
public class ReceiptListener implements ApplicationListener<ReceiptEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(AccessListener.class);

	@Autowired
	private BillingService billingService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private AcReceiptDao receiptDao;

	@Override
	public void onApplicationEvent(ReceiptEvent event) {
		if (event instanceof ReceiptApprovedEvent) {
			AcReceipt receipt = event.getReceipt();
			
			BigDecimal total = BigDecimal.ZERO;
			
			List<AcInvoice> invoices = receipt.getInvoices();
			for (AcInvoice invoice : invoices) {
				List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
				for (AcInvoiceItem invoiceItem : invoiceItems) {
					// find matching receipt item
					AcReceiptItem receiptItem = billingService.findReceiptItemByChargeCode(invoiceItem.getChargeCode(),
							invoiceItem.getInvoice(), receipt);

					if (receiptItem != null) {
						LOG.debug("Invoice Item ", invoiceItem.getBalanceAmount());
						invoiceItem.setBalanceAmount(receiptItem.getTotalAmount());
						billingService.updateInvoiceItem(invoice, invoiceItem);
					}

				}
				invoice.setBalanceAmount(
						invoice.getBalanceAmount().subtract(billingService.sumAppliedAmount(invoice, receipt)));
				LOG.debug("Invoice Balance Amount after subtract ", invoice.getBalanceAmount());
				billingService.updateInvoice(invoice);

				if (invoice.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					invoice.setPaid(true);
					billingService.updateInvoice(invoice);
				}
				
				total = receiptDao.sumAmount(invoice, receipt, securityService.getCurrentUser());

			}

			List<AcAccountCharge> accountCharges = receipt.getAccountCharges();
			for (AcAccountCharge accountCharge : accountCharges) {

				LOG.debug("receipt get acc charges ", receipt.getAccountCharges());

				AcReceiptItem receiptItem = billingService.findReceiptItemByCharge(accountCharge, receipt);
				accountCharge.setBalanceAmount(receiptItem.getTotalAmount());
				accountService.updateAccountCharge(receipt.getAccount(), accountCharge);

				if (accountCharge.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					accountCharge.setPaid(true);
					accountService.updateAccountCharge(receipt.getAccount(), accountCharge);
				}

				AcAccountChargeTransaction tx = new AcAccountChargeTransactionImpl();
				tx.setSession(receipt.getSession());
				tx.setPostedDate(new Date());
				tx.setDescription(receipt.getDescription());
				tx.setSourceNo(receipt.getReferenceNo());
				tx.setTransactionCode(AcAccountChargeType.RECEIPT);
				tx.setAccount(receipt.getAccount());
				tx.setAmount(receiptDao.sumTotalAmount(receipt, accountCharge, securityService.getCurrentUser()).negate());
				accountService.addAccountChargeTransaction(receipt.getAccount(), tx);
			}
			
			BigDecimal totaldebit = BigDecimal.ZERO;

			List<AcDebitNote> debitNotes = receipt.getDebitNotes();
			for (AcDebitNote debitNote : debitNotes) {

				AcReceiptItem receiptItem = billingService.findReceiptItemByDebitNote(debitNote, receipt);
				debitNote.setBalanceAmount(receiptItem.getTotalAmount());
				billingService.updateDebitNote(debitNote);

				if (debitNote.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					debitNote.setPaid(true);
					billingService.updateDebitNote(debitNote);
				}
				
				totaldebit = receiptDao.sumTotalAmount(receipt, debitNote, securityService.getCurrentUser());

			}
			
			BigDecimal Amount = total.add(totaldebit);
			
			if(Amount.compareTo(BigDecimal.ZERO) > 0) {
				AcAccountTransaction trx = new AcAccountTransactionImpl();
				trx.setSession(receipt.getSession());
				trx.setPostedDate(new Date());
				trx.setDescription(receipt.getDescription());
				trx.setSourceNo(receipt.getReferenceNo());
				trx.setTransactionCode(AcAccountTransactionCode.RECEIPT);
				trx.setAccount(receipt.getAccount());
				trx.setAmount(Amount.negate());
				accountService.addAccountTransaction(receipt.getAccount(), trx);
			}
			
			BigDecimal balance = receipt.getTotalPayment();

			if (balance.signum() > 0) {

				String referenceNo = systemService.generateReferenceNo(AccountConstants.ADVANCE_PAYMENT_REFRENCE_NO);
				LOG.debug("Processing application with refNo {}", referenceNo);

				AcAdvancePayment advancePayment = new AcAdvancePaymentImpl();
				advancePayment.setReferenceNo(referenceNo);
				advancePayment.setAmount(balance);
				advancePayment.setBalanceAmount(balance);
				advancePayment.setDescription("Advance Payment " + referenceNo);
				advancePayment.setReceipt(receipt);
				advancePayment.setStatus(false);
				advancePayment.setAccount(receipt.getAccount());
				advancePayment.setSession(receipt.getSession());
				billingService.addAdvancePayment(advancePayment, securityService.getCurrentUser());

				AcAccountTransaction tx = new AcAccountTransactionImpl();
				tx.setSession(receipt.getSession());
				tx.setPostedDate(new Date());
				tx.setDescription(advancePayment.getDescription());
				tx.setSourceNo(advancePayment.getReferenceNo());
				tx.setTransactionCode(AcAccountTransactionCode.ADVANCE_PAYMENT);
				tx.setAccount(receipt.getAccount());
				tx.setAmount(advancePayment.getAmount().negate());
				accountService.addAccountTransaction(receipt.getAccount(), tx);
			}
		}
	}
}
