package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.dao.AcAccountDao;
import my.edu.umk.pams.account.account.model.AcAccountTransaction;
import my.edu.umk.pams.account.account.model.AcAccountTransactionCode;
import my.edu.umk.pams.account.account.model.AcAccountTransactionImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.model.AcAdvancePaymentImpl;
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

	@Override
	public void onApplicationEvent(ReceiptEvent event) {
		if (event instanceof ReceiptApprovedEvent) {
			AcReceipt receipt = event.getReceipt();
			List<AcInvoice> invoices = receipt.getInvoices();
			for (AcInvoice invoice : invoices) {
				List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
				for (AcInvoiceItem invoiceItem : invoiceItems) {
					// find matching receipt item
					AcReceiptItem receiptItem = billingService.findReceiptItemByChargeCode(invoiceItem.getChargeCode(),
							invoiceItem.getInvoice());
					// knock off
					if (receiptItem != null) {
						LOG.debug("Invoice Item ", invoiceItem.getBalanceAmount());
						invoiceItem.setBalanceAmount(
								invoiceItem.getBalanceAmount().subtract(receiptItem.getAppliedAmount()));
						billingService.updateInvoiceItem(invoice, invoiceItem);
					}

				}
				invoice.setBalanceAmount(invoice.getBalanceAmount().subtract(billingService.sumAppliedAmount(invoice)));
				LOG.debug("Invoice Balance Amount after subtract ", invoice.getBalanceAmount());
				billingService.updateInvoice(invoice);

				if(invoice.getBalanceAmount().equals(0.00)){
					invoice.setPaid(true);
					billingService.updateInvoice(invoice);
				}
				
				BigDecimal balance = receipt.getTotalReceived().subtract(receipt.getTotalApplied());

				if (balance.negate() != null) {
					String referenceNo = systemService
							.generateReferenceNo(AccountConstants.ADVANCE_PAYMENT_REFRENCE_NO);
					LOG.debug("Processing application with refNo {}", referenceNo);

					AcAdvancePayment advancePayment = new AcAdvancePaymentImpl();
					advancePayment.setReferenceNo(referenceNo);
					advancePayment.setAmount(balance);
					advancePayment.setBalanceAmount(balance);
					advancePayment.setDescription("Advance Payment " + referenceNo);
					advancePayment.setReceipt(receipt);
					advancePayment.setStatus(false);
					advancePayment.setAccount(receipt.getAccount());
					billingService.addAdvancePayment(advancePayment, securityService.getCurrentUser());

					AcAccountTransaction tx = new AcAccountTransactionImpl();
					tx.setSession(receipt.getSession());
					tx.setPostedDate(new Date());
					tx.setSourceNo(advancePayment.getReferenceNo());
					tx.setTransactionCode(AcAccountTransactionCode.ADVANCE_PAYMENT);
					tx.setAccount(receipt.getAccount());
					tx.setAmount(advancePayment.getAmount());
					accountService.addAccountTransaction(receipt.getAccount(), tx);
				}
			}
		}
	}
}
