package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author PAMS
 */
public interface AcReceiptInvoice extends AcMetaObject {

    AcReceipt getReceipt();

    void setReceipt(AcReceipt receipt);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
}
