package my.edu.umk.pams.account.billing.event;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.account.model.AcAccountTransaction;
import my.edu.umk.pams.account.account.model.AcAccountTransactionCode;
import my.edu.umk.pams.account.account.model.AcAccountTransactionImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.event.AccessListener;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;

@Component("debitNoteListener")
public class DebitNoteListener implements ApplicationListener<DebitNoteEvent> {

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
	public void onApplicationEvent(DebitNoteEvent event) {
		if (event instanceof DebitNoteApprovedEvent) {
			AcDebitNote debitNote = event.getDebitNote();
			AcInvoice invoice = debitNote.getInvoice();
			
			LOG.debug("Invoice for debitNote", invoice.getReferenceNo());
			
			invoice.setBalanceAmount(debitNote.getTotalAmount().add(invoice.getBalanceAmount()));
			invoice.setPaid(false);
			//invoice.setSourceNo(debitNote.getReferenceNo());
			billingService.updateInvoice(invoice);
			
			List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
			for (AcInvoiceItem invoiceItem : invoiceItems) {
				if(debitNote.getChargeCode()==invoiceItem.getChargeCode()){
					invoiceItem.setBalanceAmount(invoiceItem.getBalanceAmount().add(debitNote.getTotalAmount()));
				}
			}
			
			AcAccountTransaction tx = new AcAccountTransactionImpl();
			tx.setSession(invoice.getSession());
			tx.setPostedDate(new Date());
			tx.setDescription(invoice.getDescription());
			tx.setChargeCode(debitNote.getChargeCode());
			tx.setSourceNo(invoice.getReferenceNo());
			tx.setTransactionCode(AcAccountTransactionCode.INVOICE);
			tx.setAccount(invoice.getAccount());
			tx.setAmount(invoice.getBalanceAmount());
			accountService.addAccountTransaction(invoice.getAccount(), tx);
		}
	}
}
