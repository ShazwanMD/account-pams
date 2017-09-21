package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcWaiverInvoice extends AcMetaObject {

    AcWaiverFinanceApplication getWaiverFinanceApplication();

    void setWaiverFinanceApplication(AcWaiverFinanceApplication waiverFinanceApplication);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
}
