package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author PAMS
 */
public interface AcReceiptAccountCharge extends AcMetaObject {

    AcReceipt getReceipt();

    void setReceipt(AcReceipt receipt);

	AcAccountCharge getAccountCharge();

	void setAccountCharge(AcAccountCharge accountCharge);
}
