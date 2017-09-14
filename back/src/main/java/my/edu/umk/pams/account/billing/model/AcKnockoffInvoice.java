package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcKnockoffInvoice extends AcMetaObject {

    AcKnockoff getKnockoff();

    void setKnockoff(AcKnockoff knockoff);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
}
