package my.edu.umk.pams.account.account.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author PAMS
 */
@Entity(name = "AcSecurityCharge")
@Table(name = "AC_SCTY_CHRG")
public class AcSecurityChargeImpl extends AcAccountChargeImpl implements AcSecurityCharge {

    @NotNull
    @ManyToOne(targetEntity = AcChargeCodeImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARGE_CODE_ID")
    private AcChargeCode chargeCode;

    public AcSecurityChargeImpl() {
        setChargeType(AcAccountChargeType.SECURITY);
    }

    public AcChargeCode getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(AcChargeCode chargeCode) {
        this.chargeCode = chargeCode;
    }
}
