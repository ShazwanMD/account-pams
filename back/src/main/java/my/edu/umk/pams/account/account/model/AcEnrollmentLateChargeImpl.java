package my.edu.umk.pams.account.account.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author PAMS
 */
@Entity(name = "AcEnrollmentLateCharge")
@Table(name = "AC_ENMT_LATE_CHRG")
public class AcEnrollmentLateChargeImpl extends AcAccountChargeImpl implements AcEnrollmentCharge {

    public AcEnrollmentLateChargeImpl() {
        setChargeType(AcAccountChargeType.ENROLLMENT_LATE);
    }
}
