package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcKnockoffDebitNote extends AcMetaObject {

    AcKnockoff getKnockoff();

    void setKnockoff(AcKnockoff knockoff);

    AcDebitNote getDebitNote();

    void setDebitNote(AcDebitNote debitNote);
}
