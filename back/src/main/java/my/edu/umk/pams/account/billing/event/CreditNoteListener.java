package my.edu.umk.pams.account.billing.event;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.account.model.AcAccountTransaction;
import my.edu.umk.pams.account.account.model.AcAccountTransactionCode;
import my.edu.umk.pams.account.account.model.AcAccountTransactionImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.event.AccessListener;

@Component("creditNoteListener")
public class CreditNoteListener implements ApplicationListener<CreditNoteEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(AccessListener.class);

	@Autowired
	private BillingService billingService;

	@Autowired
	private AccountService accountService;
	
	@Override
	public void onApplicationEvent(CreditNoteEvent event) {
		if (event instanceof CreditNoteApprovedEvent) {
			AcCreditNote creditNote = event.getCreditNote();
			AcInvoice invoice = creditNote.getInvoice();
			
			LOG.debug("Invoice for creditNote", invoice.getReferenceNo());
			
			invoice.setBalanceAmount(invoice.getBalanceAmount().subtract(creditNote.getTotalAmount()));
			invoice.setPaid(false);
			//invoice.setSourceNo(creditNote.getReferenceNo());
			billingService.updateInvoice(invoice);
			
			AcAccountTransaction tx = new AcAccountTransactionImpl();
			tx.setSession(invoice.getSession());
			tx.setPostedDate(new Date());
			tx.setBalanceAmount(BigDecimal.ZERO);
			tx.setDescription(invoice.getDescription());
			tx.setChargeCode(creditNote.getChargeCode());
			tx.setSourceNo(invoice.getReferenceNo());
			tx.setTransactionCode(AcAccountTransactionCode.INVOICE);
			tx.setAccount(invoice.getAccount());
			tx.setAmount(invoice.getBalanceAmount());
			accountService.addAccountTransaction(invoice.getAccount(), tx);
		}
		
	}
}
