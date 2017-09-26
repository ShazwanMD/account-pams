package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcWaiverDebitNote extends AcMetaObject {
	
	AcDebitNote getDebitNote();

    void setDebitNote(AcDebitNote debitNote);
    
    AcWaiverFinanceApplication getWaiverFinanceApplication();

    void setWaiverFinanceApplication(AcWaiverFinanceApplication waiverFinanceApplication);

}
