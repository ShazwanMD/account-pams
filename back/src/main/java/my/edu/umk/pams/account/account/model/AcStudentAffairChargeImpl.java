package my.edu.umk.pams.account.account.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author PAMS
 */
@Entity(name = "AcStudentAffairCharge")
@Table(name = "AC_STDN_AFFR_CHRG")
public class AcStudentAffairChargeImpl extends AcAccountChargeImpl implements AcStudentAffairCharge {

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
