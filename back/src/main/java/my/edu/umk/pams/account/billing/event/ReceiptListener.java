package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.event.AccessListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

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
//        if (event instanceof ReceiptApprovedEvent) {
//            AcReceipt receipt = event.getReceipt();
//            List<AcInvoice> invoices = receipt.getInvoices();
//
//            // knock off invoice items
//            for (AcInvoice invoice : invoices) {
//                List<AcInvoiceItem> items = billingService.findInvoiceItems(invoice);
//                for (AcInvoiceItem invoiceItem : items) {
//                    // find matching receipt item
//                    AcReceiptItem receiptItem = billingService.findReceiptItemByChargeCode(invoiceItem.getChargeCode());
//                    // knock off
//                    invoiceItem.setBalanceAmount(
//                            invoice.getBalanceAmount().subtract(receiptItem.getAppliedAmount()));
//                    billingService.updateInvoiceItem(invoice, invoiceItem);
//                }
//            }
//        }
    }
}
