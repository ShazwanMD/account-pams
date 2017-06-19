package my.edu.umk.pams.account.account.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author PAMS
 */
@Deprecated
//@Entity(name = "AcEnrollmentCharge")
//@Table(name = "AC_ENMT_CHRG")
public class AcEnrollmentChargeImpl extends AcAccountChargeImpl implements AcEnrollmentCharge {

    public AcEnrollmentChargeImpl() {
        setChargeType(AcAccountChargeType.ENROLLMENT);
    }
}
