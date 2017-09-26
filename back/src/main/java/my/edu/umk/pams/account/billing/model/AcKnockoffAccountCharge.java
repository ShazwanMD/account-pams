package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcKnockoffAccountCharge extends AcMetaObject {

    AcKnockoff getKnockoff();

    void setKnockoff(AcKnockoff knockoff);

	AcAccountCharge getAccountCharge();

	void setAccountCharge(AcAccountCharge accountCharge);

}
