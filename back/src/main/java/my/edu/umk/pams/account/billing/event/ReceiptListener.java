package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.event.AccessListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
@Component("receiptListener")
public class ReceiptListener implements ApplicationListener<ReceiptEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(AccessListener.class);

	@Autowired
	private BillingService billingService;

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
					if( receiptItem != null){
					LOG.debug("Invoice Item ", invoiceItem.getBalanceAmount());
					invoiceItem
							.setBalanceAmount(invoiceItem.getBalanceAmount().subtract(receiptItem.getAppliedAmount()));
					billingService.updateInvoiceItem(invoice, invoiceItem);
//					LOG.debug("Invoice Item ", invoiceItem.getBalanceAmount());
					
					//
					// if(invoice.getBalanceAmount()==BigDecimal.ZERO){
					// invoice.setPaid(true);
					// billingService.updateInvoice(invoice);
					// }
					}

				}
				invoice.setBalanceAmount(
						invoice.getBalanceAmount().subtract(billingService.sumAppliedAmount(invoice)));
				LOG.debug("Invoice Balance Amount after subtract ", invoice.getBalanceAmount());
				billingService.updateInvoice(invoice);
			}
		}
	}
}
