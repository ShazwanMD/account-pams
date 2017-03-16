package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.model.AcDocument;

/**
 * @author PAMS
 */
public interface AcDebitNote extends AcDocument{

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);

}
