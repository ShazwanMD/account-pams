package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author PAMS
 */
public interface AcReceiptDebitNote extends AcMetaObject {

    AcReceipt getReceipt();

    void setReceipt(AcReceipt receipt);

    AcDebitNote getDebitNote();

    void setDebitNote(AcDebitNote debitNote);
}
