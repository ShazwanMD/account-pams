package my.edu.umk.pams.account.billing.event;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.dao.AcInvoiceDao;
import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcCreditNoteItem;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.event.AccessListener;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("creditNoteListener")
public class CreditNoteListener implements ApplicationListener<CreditNoteEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(AccessListener.class);

	@Autowired
	private BillingService billingService;

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private AcInvoiceDao invoiceDao;
	
	@Override
	public void onApplicationEvent(CreditNoteEvent event) {
		if (event instanceof CreditNoteApprovedEvent) {
			AcCreditNote creditNote = event.getCreditNote();
			AcInvoice invoice = creditNote.getInvoice();
			
			LOG.debug("Invoice for creditNote", invoice.getReferenceNo());
			
			List<AcCreditNoteItem> creditNoteItems = billingService.findCreditNoteItems(creditNote);
			for(AcCreditNoteItem creditNoteItem: creditNoteItems) {
				
				List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
				for (AcInvoiceItem invoiceItem : invoiceItems) {
					if(creditNoteItem.getChargeCode() == invoiceItem.getChargeCode()) {
						invoiceItem.setBalanceAmount(creditNoteItem.getBalanceAmount());
						billingService.updateInvoiceItem(invoice, invoiceItem);
					}
					
					invoice.setBalanceAmount(invoiceDao.sumBalanceAmount(invoice, invoiceItem, securityService.getCurrentUser()));
					if (invoice.getBalanceAmount().compareTo(BigDecimal.ZERO) == 0) {
						invoice.setPaid(true);
						billingService.updateInvoice(invoice);
					}
				}
				
			}
		
		}
	}
}
