package my.edu.umk.pams.account.account.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author PAMS
 */
@Entity(name = "AcAcademicCharge")
@Table(name = "AC_ACDM_CHRG")
public class AcAcademicChargeImpl extends AcAccountChargeImpl implements AcAcademicCharge {

    @NotNull
    @ManyToOne(targetEntity = AcChargeCodeImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARGE_CODE_ID")
    private AcChargeCode chargeCode;

    public AcChargeCode getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(AcChargeCode chargeCode) {
        this.chargeCode = chargeCode;
    }

}
