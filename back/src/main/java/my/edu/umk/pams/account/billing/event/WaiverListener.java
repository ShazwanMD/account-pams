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
import my.edu.umk.pams.account.account.model.AcAccountWaiver;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.dao.AcReceiptDao;
import my.edu.umk.pams.account.billing.dao.AcWaiverFinanceApplicationDao;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcDebitNoteItem;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcKnockoffItem;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.model.AcWaiverItem;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplicationImpl;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.security.event.AccessListener;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;

@Component("waiverListener")
public class WaiverListener implements ApplicationListener<WaiverEvent> {
	
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
	private FinancialAidService financialAidService;

	@Autowired
	private AcWaiverFinanceApplicationDao waiverDao;

	@Override
	public void onApplicationEvent(WaiverEvent event) {
		if (event instanceof WaiverApprovedEvent) {
			AcWaiverFinanceApplication waiver = event.getWaiverFinanceApplication();
			
			BigDecimal total = BigDecimal.ZERO;
			
			List<AcInvoice> invoices = waiver.getInvoices();
			for (AcInvoice invoice : invoices) {
				List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
				for (AcInvoiceItem invoiceItem : invoiceItems) {
					// find matching receipt item
					AcWaiverItem waiverItem = billingService.findWaiverItemByChargeCode(invoiceItem.getChargeCode(),
							invoiceItem.getInvoice(), waiver);

					if (waiverItem != null) {
						LOG.debug("waiverItem ", invoiceItem.getBalanceAmount());
						invoiceItem.setBalanceAmount(waiverItem.getTotalAmount());
						billingService.updateInvoiceItem(invoice, invoiceItem);
					}

				}
				invoice.setBalanceAmount(
						invoice.getBalanceAmount().subtract(billingService.sumAppliedAmount(invoice, waiver)));
				LOG.debug("Invoice Balance Amount after subtract ", invoice.getBalanceAmount());
				billingService.updateInvoice(invoice);

				if (invoice.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					invoice.setPaid(true);
					billingService.updateInvoice(invoice);
				}
				
				total = total.add(waiverDao.sumAmount(invoice, waiver, securityService.getCurrentUser()));

			}
			
			BigDecimal totalCharge = BigDecimal.ZERO;
			
			List<AcAccountCharge> accountCharges = waiver.getAccountCharges();
			for (AcAccountCharge accountCharge : accountCharges) {

				LOG.debug("receipt get acc charges ", waiver.getAccountCharges());

				AcWaiverItem waiverItem = billingService.findWaiverItemByCharge(accountCharge, waiver);
				accountCharge.setBalanceAmount(waiverItem.getTotalAmount());
				accountService.updateAccountCharge(waiver.getAccount(), accountCharge);

				if (accountCharge.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					accountCharge.setPaid(true);
					accountService.updateAccountCharge(waiver.getAccount(), accountCharge);
				}

				totalCharge = totalCharge.add(waiverDao.sumTotalAmount(waiver, accountCharge, securityService.getCurrentUser()));
				
			}
			
			BigDecimal totaldebit = BigDecimal.ZERO;
			List<AcDebitNote> debitNotes = waiver.getDebitNotes();
			for (AcDebitNote debitNote : debitNotes) {
				
				List<AcDebitNoteItem>  debitNoteItems = billingService.findDebitNoteItems(debitNote);
				for (AcDebitNoteItem debitNoteItem : debitNoteItems) {
					AcWaiverItem waiverItem = billingService.findWaiverItemByChargeCode(debitNoteItem.getChargeCode(),
							debitNote.getInvoice(), debitNote, waiver);

					if (waiverItem != null) {
						LOG.debug("waiverItem Item {} ", debitNoteItem.getChargeCode().getDescription() + debitNoteItem.getBalanceAmount());
						debitNoteItem.setBalanceAmount(waiverItem.getTotalAmount());
						billingService.updateDebitNoteItem(debitNote, debitNoteItem);;
					}

				}
				LOG.debug("Debit Note Balance Amount {}", debitNote.getBalanceAmount());
				LOG.debug("Debit Note Total Amount {}", debitNote.getTotalAmount());
				
				BigDecimal balance = billingService.sumBalanceAmount(debitNote);
				BigDecimal totalAmount = billingService.sumTotalAmount(debitNote);
				
				debitNote.setBalanceAmount(balance);
				debitNote.setTotalAmount(totalAmount);
				LOG.debug("Debit Note Balance Amount after subtract {}", debitNote.getBalanceAmount());
				LOG.debug("Debit Note Total Amount after subtract {}", debitNote.getTotalAmount());
				billingService.updateDebitNote(debitNote);
				
				if (debitNote.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
					debitNote.setPaid(true);
					billingService.updateDebitNote(debitNote);
				}

				totaldebit = totaldebit
						.add(waiverDao.sumTotalAmount(waiver, debitNote, securityService.getCurrentUser()));
			}
			
			BigDecimal Amount = total.add(totaldebit);
			
			if(Amount.compareTo(BigDecimal.ZERO) > 0) {
				AcAccountTransaction trx = new AcAccountTransactionImpl();
				trx.setSession(waiver.getSession());
				trx.setPostedDate(new Date());
				trx.setDescription(waiver.getDescription());
				trx.setSourceNo(waiver.getReferenceNo());
				trx.setTransactionCode(AcAccountTransactionCode.WAIVER);
				trx.setAccount(waiver.getAccount());
				trx.setAmount(Amount.negate());
				accountService.addAccountTransaction(waiver.getAccount(), trx);
			}
			
			if(totalCharge.compareTo(BigDecimal.ZERO) > 0) {
				AcAccountChargeTransaction tx = new AcAccountChargeTransactionImpl();
				tx.setSession(waiver.getSession());
				tx.setPostedDate(new Date());
				tx.setDescription(waiver.getDescription());
				tx.setSourceNo(waiver.getReferenceNo());
				tx.setTransactionCode(AcAccountChargeType.WAIVER);
				tx.setAccount(waiver.getAccount());
				tx.setAmount(totalCharge.negate());
				accountService.addAccountChargeTransaction(waiver.getAccount(), tx);
			}
			
			AcWaiverApplication waiverApp = financialAidService.findWaiverApplicationById(waiver.getAccWaiver().getId());
			waiverApp.setBalance(waiver.getGracedAmount());
			financialAidService.updateWaiverApplication(waiverApp);
			
			if(waiverApp.getBalance().compareTo(BigDecimal.ZERO) == 0) {
				AcAccountWaiver accWaiver = accountService.findAccountWaiverById(waiver.getAccWaiver().getId());
				accWaiver.setStatus(true);
				accountService.updateAccountWaiver(accWaiver);
			}
		}
	}

}
