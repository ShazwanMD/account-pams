package my.edu.umk.pams.account.account.model;

/**
 * @author PAMS
 */
@Deprecated
public interface AcSecurityCharge extends AcAccountCharge {

    // todo(uda): confirm
    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);
}
