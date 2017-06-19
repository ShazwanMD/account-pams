package my.edu.umk.pams.account.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author PAMS
 */
@Deprecated
//@Entity(name = "AcCompoundCharge")
//@Table(name = "AC_CMPD_CHRG")
//@Inheritance(strategy = InheritanceType.JOINED)
public class AcCompoundChargeImpl extends AcAccountChargeImpl implements AcCompoundCharge {

    @NotNull
    @Column(name = "COMPOUND_CODE", unique = true, nullable = false)
    private String compoundCode;

    @Column(name = "COMPOUND_DESCRIPTION", nullable = false)
    private String compoundDescription;
    
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ACCOUNT_CHARGE_TYPE")
    private AcAccountChargeType chargeType;
    
	@Override
	public String getCompoundCode() {
		return compoundCode;
	}

	@Override
	public void setCompoundCode(String compoundCode) {
		this.compoundCode = compoundCode;
	}

	@Override
	public String getCompoundDescription() {
		return compoundDescription;
	}

	@Override
	public void setCompoundDescription(String compoundDescription) {
		this.compoundDescription = compoundDescription;
	}

	@Override
	public AcAccountChargeType getChargeType() {
		return chargeType;
	}

	@Override
	public void setChargeType(AcAccountChargeType chargeType) {
		this.chargeType = chargeType;
	}

   
    
}
