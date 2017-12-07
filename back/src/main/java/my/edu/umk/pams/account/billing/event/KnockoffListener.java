package my.edu.umk.pams.account.billing.event;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeTransaction;
import my.edu.umk.pams.account.account.model.AcAccountChargeTransactionImpl;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcAccountTransaction;
import my.edu.umk.pams.account.account.model.AcAccountTransactionCode;
import my.edu.umk.pams.account.account.model.AcAccountTransactionImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.dao.AcKnockoffDao;
import my.edu.umk.pams.account.billing.dao.AcReceiptDao;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.model.AcAdvancePaymentImpl;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcDebitNoteItem;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcKnockoffItem;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;

@Component("knockoffListener")
public class KnockoffListener implements ApplicationListener<KnockoffEvent> {
	
	private static final Logger LOG = LoggerFactory.getLogger(KnockoffListener.class);

	@Autowired
	private BillingService billingService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private AcKnockoffDao knockoffDao;

	@Override
	public void onApplicationEvent(KnockoffEvent event) {
		if (event instanceof KnockoffApprovedEvent) {
			AcKnockoff knockoff = event.getKnockoff();
			
			BigDecimal total = BigDecimal.ZERO;
			
			List<AcInvoice> invoices = knockoff.getInvoices();
			for (AcInvoice invoice : invoices) {
				List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
				for (AcInvoiceItem invoiceItem : invoiceItems) {
					// find matching receipt item
					AcKnockoffItem knockoffItem = billingService.findKnockoffItemByChargeCode(invoiceItem.getChargeCode(),
							invoiceItem.getInvoice(), knockoff);

					if (knockoffItem != null) {
						LOG.debug("Invoice Item ", invoiceItem.getBalanceAmount());
						invoiceItem.setBalanceAmount(knockoffItem.getTotalAmount());
						billingService.updateInvoiceItem(invoice, invoiceItem);
					}

				}
				invoice.setBalanceAmount(
						invoice.getBalanceAmount().subtract(billingService.sumAppliedAmount(invoice, knockoff)));
				LOG.debug("Invoice Balance Amount after subtract ", invoice.getBalanceAmount());
				billingService.updateInvoice(invoice);

				if (invoice.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					invoice.setPaid(true);
					billingService.updateInvoice(invoice);
				}
				
				total = total.add(knockoffDao.sumAmount(invoice, knockoff, securityService.getCurrentUser()));

			}
			BigDecimal totalCharge = BigDecimal.ZERO;
			
			List<AcAccountCharge> accountCharges = knockoff.getAccountCharges();
			for (AcAccountCharge accountCharge : accountCharges) {

				LOG.debug("receipt get acc charges ", knockoff.getAccountCharges());

				AcKnockoffItem knockoffItem = billingService.findKnockoffItemByCharge(accountCharge, knockoff);
				BigDecimal balanceAmount = knockoffDao.sumTotalAmount(knockoff, accountCharge, securityService.getCurrentUser());
				accountCharge.setBalanceAmount(accountCharge.getBalanceAmount().subtract(balanceAmount));
				accountService.updateAccountCharge(knockoff.getPayments().getAccount(), accountCharge);

				if (accountCharge.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					accountCharge.setPaid(true);
					accountService.updateAccountCharge(knockoff.getPayments().getAccount(), accountCharge);
				}
				
				totalCharge = totalCharge.add(knockoffDao.sumTotalAmount(knockoff, accountCharge, securityService.getCurrentUser()));

			}
			
			BigDecimal totaldebit = BigDecimal.ZERO;

			List<AcDebitNote> debitNotes = knockoff.getDebitNotes();
			for (AcDebitNote debitNote : debitNotes) {

				List<AcDebitNoteItem>  debitNoteItems = billingService.findDebitNoteItems(debitNote);
				for (AcDebitNoteItem debitNoteItem : debitNoteItems) { 
					
					AcKnockoffItem knockoffItem = billingService.findKnockoffItemByChargeCode(debitNoteItem.getChargeCode(), 
							debitNote.getInvoice(), debitNote, knockoff);
				
					if (knockoffItem != null) {
						LOG.debug("Invoice Item ", debitNoteItem.getBalanceAmount());
						debitNoteItem.setBalanceAmount(knockoffItem.getTotalAmount());
						billingService.updateKnockoffItem(knockoff, knockoffItem);
					}
				}
				
				debitNote.setBalanceAmount(
						debitNote.getBalanceAmount().subtract(billingService.sumAppliedAmount(debitNote, knockoff)));
				billingService.updateDebitNote(debitNote);

				if (debitNote.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					debitNote.setPaid(true);
					billingService.updateDebitNote(debitNote);
				}
				
				totaldebit = totaldebit.add(knockoffDao.sumTotalAmount(debitNote, knockoff, securityService.getCurrentUser()));

			}
			
			BigDecimal Amount = total.add(totaldebit);
			
			if(Amount.compareTo(BigDecimal.ZERO) > 0) {
				AcAccountTransaction trx = new AcAccountTransactionImpl();
				trx.setSession(knockoff.getPayments().getSession());
				trx.setPostedDate(new Date());
				trx.setDescription(knockoff.getDescription());
				trx.setSourceNo(knockoff.getReferenceNo());
				trx.setTransactionCode(AcAccountTransactionCode.RECEIPT);
				trx.setAccount(knockoff.getPayments().getAccount());
				trx.setAmount(Amount.negate());
				accountService.addAccountTransaction(knockoff.getPayments().getAccount(), trx);
			}
			
			if(totalCharge.compareTo(BigDecimal.ZERO) > 0) {
				AcAccountChargeTransaction tx = new AcAccountChargeTransactionImpl();
				tx.setSession(knockoff.getPayments().getSession());
				tx.setPostedDate(new Date());
				tx.setDescription(knockoff.getDescription());
				tx.setSourceNo(knockoff.getReferenceNo());
				tx.setTransactionCode(AcAccountChargeType.KNOCKOFF);
				tx.setAccount(knockoff.getPayments().getAccount());
				tx.setAmount(totalCharge.negate());
				accountService.addAccountChargeTransaction(knockoff.getPayments().getAccount(), tx);
			}
			
			AcAdvancePayment advance = billingService.findAdvancePaymentByReferenceNo(knockoff.getPayments().getReferenceNo());
			BigDecimal amountItem = knockoffDao.sumAppliedAmount(knockoff, securityService.getCurrentUser());
			advance.setBalanceAmount(advance.getBalanceAmount().subtract(amountItem));
			billingService.updateAdvancePayment(advance);
			
			if(advance.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
				advance.setStatus(true);
				billingService.updateAdvancePayment(advance);
			}

		}
		
		
	}
}
