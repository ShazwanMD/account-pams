package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;

/**
 * @author PAMS
 */
@Deprecated
public interface AcAdmissionCharge extends AcAccountCharge {

    /**
     *
     * @return
     */
    AcCohortCode getCohortCode();

    void setCohortCode(AcCohortCode cohortCode);

    /**
     *
     * @return
     */
    AcStudyMode getStudyMode();

    void setStudyMode(AcStudyMode studyMode);
    // locality
}
