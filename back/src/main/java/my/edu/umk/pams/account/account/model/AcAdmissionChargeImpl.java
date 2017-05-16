package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcCohortCodeImpl;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.common.model.AcStudyModeImpl;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author PAMS
 */
@Entity(name = "AcAdmissionCharge")
@Table(name = "AC_ADMN_CHRG")
public class AcAdmissionChargeImpl extends AcAccountChargeImpl implements AcAdmissionCharge {

    @NotNull
    @OneToOne(targetEntity = AcStudyModeImpl.class)
    @JoinColumn(name = "STUDY_MODE_ID")
    private AcStudyMode studyMode;

    @NotNull
    @OneToOne(targetEntity = AcCohortCodeImpl.class)
    @JoinColumn(name = "COHORT_CODE_ID")
    private AcCohortCode cohortCode;

    public AcAdmissionChargeImpl() {
        setChargeType(AcAccountChargeType.ADMISSION);
    }

    @Override
    public AcStudyMode getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(AcStudyMode studyMode) {
        this.studyMode = studyMode;
    }

    @Override
    public AcCohortCode getCohortCode() {
        return cohortCode;
    }

    @Override
    public void setCohortCode(AcCohortCode cohortCode) {
        this.cohortCode = cohortCode;
    }
}
