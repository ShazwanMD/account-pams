package my.edu.umk.pams.account.account.model;

/**
 * @author PAMS
 */
public interface AcSecurityCharge extends AcAccountCharge {

    // todo(uda): confirm
    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);
}
