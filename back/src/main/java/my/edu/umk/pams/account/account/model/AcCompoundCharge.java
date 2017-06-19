package my.edu.umk.pams.account.account.model;

/**
 * @author PAMS
 */
@Deprecated
public interface AcCompoundCharge extends AcAccountCharge {

    String getCompoundCode();

    void setCompoundCode(String compoundCode);

    String getCompoundDescription();

    void setCompoundDescription(String compoundDescription);
    
    AcAccountChargeType getChargeType();

    void setChargeType(AcAccountChargeType chargeType);
}