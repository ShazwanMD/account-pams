package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author PAMS
 */
public interface AcCoverage extends AcMetaObject{

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

    AcSponsor getSponsor();

    void setSponsor(AcSponsor sponsor);
}
