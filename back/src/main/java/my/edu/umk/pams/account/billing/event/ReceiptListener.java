package my.edu.umk.pams.account.billing.event;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.event.AccountEvent;
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
import my.edu.umk.pams.account.billing.model.AcDebitNoteItem;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.model.AcStudentStatus;
import my.edu.umk.pams.account.security.event.AccessListener;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.connector.payload.AccountPayload;
import my.edu.umk.pams.connector.payload.StudentStatus;

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
	
	@Autowired
	private ApplicationContext applicationContext;

	@Override
	public void onApplicationEvent(ReceiptEvent event) {
		if (event instanceof ReceiptApprovedEvent) {
			AcReceipt receipt = event.getReceipt();

			// Invoice
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

					invoice.setBalanceAmount(billingService.sumBalanceAmount(invoice, invoiceItem));					
					LOG.debug("Invoice Balance Amount after subtract ", invoice.getBalanceAmount());
					billingService.updateInvoice(invoice);
				}
				
				if (invoice.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					invoice.setPaid(true);
					billingService.updateInvoice(invoice);
					
					//hantar payload tidak berhutang ke akademik
					
					LOG.info("Start Send Invoice Receipt Listener No Debt");
					AccountPayload invoicePayload = new AccountPayload();
					invoicePayload.setOutstanding(false);
					invoicePayload.setStudentStatus(StudentStatus.ACTIVE);
					invoicePayload.setMatricNo(invoice.getAccount().getActor().getIdentityNo());
					invoicePayload.setBalance(invoice.getBalanceAmount());
					
					AccountEvent invoiceEvent = new AccountEvent(invoicePayload);
					applicationContext.publishEvent(invoiceEvent);
					LOG.info("Finish Send Invoice Receipt Listener No Debt");
					
				}
				else
				{
					//hantar payload berhutang ke akademik
					
					LOG.info("Start Send Invoice Receipt Listener Has Debt");
					AccountPayload invoicePayload = new AccountPayload();
					invoicePayload.setOutstanding(true);
					invoicePayload.setStudentStatus(StudentStatus.BARRED);
					invoicePayload.setMatricNo(invoice.getAccount().getActor().getIdentityNo());
					invoicePayload.setBalance(invoice.getBalanceAmount());
					
					AccountEvent invoiceEvent = new AccountEvent(invoicePayload);
					applicationContext.publishEvent(invoiceEvent);
					LOG.info("Finish Send Invoice Receipt Listener Has Debt");
				}

				total = total.add(receiptDao.sumAmount(invoice, receipt, securityService.getCurrentUser()));
				LOG.debug("total receipt {}", total);
			}

			// Charges

			BigDecimal totalCharge = BigDecimal.ZERO;
			List<AcAccountCharge> accountCharges = receipt.getAccountCharges();
			for (AcAccountCharge accountCharge : accountCharges) {

				LOG.debug("receipt get acc charges ", receipt.getAccountCharges());

				AcReceiptItem receiptItem = billingService.findReceiptItemByCharge(accountCharge, receipt);
				accountCharge.setBalanceAmount(receiptItem.getTotalAmount());
				accountService.updateAccountCharge(receipt.getAccount(), accountCharge);

				if (accountCharge.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					accountCharge.setPaid(true);
					accountService.updateAccountCharge(receipt.getAccount(), accountCharge);
					
					//hantar payload tidak berhutang ke akademik
					
					LOG.info("Start Send accChargeEvent Listener No Debt");
					AccountPayload accChargePayload = new AccountPayload();
					accChargePayload.setOutstanding(false);
					accChargePayload.setStudentStatus(StudentStatus.ACTIVE);
					accChargePayload.setMatricNo(accountCharge.getAccount().getActor().getIdentityNo());
					accChargePayload.setBalance(accountCharge.getBalanceAmount());
					
					AccountEvent accChargeEvent = new AccountEvent(accChargePayload);
					applicationContext.publishEvent(accChargeEvent);
					LOG.info("Finish Send accChargeEvent Listener No Debt");
				}
				else
				{
					//hantar payload berhutang ke akademik
					
					LOG.info("Start Send accChargeEvent Listener Has Debt");
					AccountPayload accChargePayload = new AccountPayload();
					accChargePayload.setOutstanding(true);
					accChargePayload.setStudentStatus(StudentStatus.BARRED);
					accChargePayload.setMatricNo(accountCharge.getAccount().getActor().getIdentityNo());
					accChargePayload.setBalance(accountCharge.getBalanceAmount());
					
					AccountEvent accChargeEvent = new AccountEvent(accChargePayload);
					applicationContext.publishEvent(accChargeEvent);
					LOG.info("Finish Send accChargeEvent Listener Has Debt");
				}

				totalCharge = totalCharge
						.add(receiptDao.sumTotalAmount(receipt, accountCharge, securityService.getCurrentUser()));
			}

			// debit note

			BigDecimal totaldebit = BigDecimal.ZERO;
			List<AcDebitNote> debitNotes = receipt.getDebitNotes();
			for (AcDebitNote debitNote : debitNotes) {
				
				List<AcDebitNoteItem>  debitNoteItems = billingService.findDebitNoteItems(debitNote);
				for (AcDebitNoteItem debitNoteItem : debitNoteItems) {
					AcReceiptItem receiptItem = billingService.findReceiptItemByChargeCode(debitNoteItem.getChargeCode(),
							debitNote.getInvoice(), debitNote, receipt);

					if (receiptItem != null) {
						LOG.debug("Invoice Item ", debitNoteItem.getBalanceAmount());
						debitNoteItem.setBalanceAmount(receiptItem.getTotalAmount());
						billingService.updateDebitNoteItem(debitNote, debitNoteItem);;
					}

				}
				
				debitNote.setBalanceAmount(
						debitNote.getBalanceAmount().subtract(billingService.sumAppliedAmount(debitNote, receipt)));
				LOG.debug("Invoice Balance Amount after subtract ", debitNote.getBalanceAmount());
				billingService.updateDebitNote(debitNote);
				
				if (debitNote.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					debitNote.setPaid(true);
					billingService.updateDebitNote(debitNote);
					
					//hantar payload tidak berhutang ke akademik
					
					LOG.info("Start Send debitNotePayloadEvent Listener No Debt");
					AccountPayload debitNotePayload = new AccountPayload();
					debitNotePayload.setOutstanding(false);
					debitNotePayload.setStudentStatus(StudentStatus.ACTIVE);
					debitNotePayload.setMatricNo(debitNote.getInvoice().getAccount().getActor().getIdentityNo());
					debitNotePayload.setBalance(debitNote.getBalanceAmount());
					
					AccountEvent debitNotePayloadEvent = new AccountEvent(debitNotePayload);
					applicationContext.publishEvent(debitNotePayloadEvent);
					LOG.info("Finish Send debitNotePayloadEvent Listener No Debt");
				}
				else
				{
					//hantar payload berhutang ke akademik
					
					LOG.info("Start Send debitNotePayloadEvent Listener Has Debt");
					AccountPayload debitNotePayload = new AccountPayload();
					debitNotePayload.setOutstanding(true);
					debitNotePayload.setStudentStatus(StudentStatus.BARRED);
					debitNotePayload.setMatricNo(debitNote.getInvoice().getAccount().getActor().getIdentityNo());
					debitNotePayload.setBalance(debitNote.getBalanceAmount());
					
					AccountEvent debitNotePayloadEvent = new AccountEvent(debitNotePayload);
					applicationContext.publishEvent(debitNotePayloadEvent);
					LOG.info("Finish Send debitNotePayloadEvent Listener Has Debt");
				}

				totaldebit = totaldebit
						.add(receiptDao.sumTotalAmount(receipt, debitNote, securityService.getCurrentUser()));
			}


			BigDecimal Amount = total.add(totaldebit);

			if (Amount.signum() > 0) {
				AcAccountTransaction trx = new AcAccountTransactionImpl();
				trx.setSession(receipt.getSession());
				trx.setPostedDate(new Date());
				trx.setDescription(receipt.getDescription());
				trx.setSourceNo(receipt.getReferenceNo());
				trx.setTransactionCode(AcAccountTransactionCode.RECEIPT);
				trx.setAccount(receipt.getAccount());
				trx.setAmount(receipt.getTotalReceived().negate());
				accountService.addAccountTransaction(receipt.getAccount(), trx);
			}
			
			if (totalCharge.signum() > 0) {
				AcAccountChargeTransaction tx = new AcAccountChargeTransactionImpl();
				tx.setSession(receipt.getSession());
				tx.setPostedDate(new Date());
				tx.setDescription(receipt.getDescription());
				tx.setSourceNo(receipt.getReferenceNo());
				tx.setTransactionCode(AcAccountChargeType.RECEIPT);
				tx.setAccount(receipt.getAccount());
				tx.setAmount(receipt.getTotalReceived().negate());
				accountService.addAccountChargeTransaction(receipt.getAccount(), tx);
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
				advancePayment.setSourceNo(receipt.getReferenceNo());
				advancePayment.setStatus(false);
				advancePayment.setAccount(receipt.getAccount());
				advancePayment.setSession(receipt.getSession());
				billingService.addAdvancePayment(advancePayment, securityService.getCurrentUser());

			}
		}
	}
}
