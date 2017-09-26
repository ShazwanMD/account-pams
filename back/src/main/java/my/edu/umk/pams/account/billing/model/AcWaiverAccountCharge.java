package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcWaiverAccountCharge extends AcMetaObject {

    AcWaiverFinanceApplication getWaiverFinanceApplication();

    void setWaiverFinanceApplication(AcWaiverFinanceApplication waiverFinanceApplication);

	AcAccountCharge getAccountCharge();

	void setAccountCharge(AcAccountCharge accountCharge);
}
