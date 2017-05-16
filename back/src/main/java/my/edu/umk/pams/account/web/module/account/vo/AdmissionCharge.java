package my.edu.umk.pams.account.web.module.account.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.common.vo.CohortCode;
import my.edu.umk.pams.account.web.module.common.vo.StudyMode;

import java.io.IOException;

/**
 * @author PAMS
 */
public class AdmissionCharge extends AccountCharge {
    private CohortCode cohortCode;
    private StudyMode studyMode;

    public CohortCode getCohortCode() {
        return cohortCode;
    }

    public void setCohortCode(CohortCode cohortCode) {
        this.cohortCode = cohortCode;
    }

    public StudyMode getStudyMode() {
        return studyMode;
    }

    public void setStudyMode(StudyMode studyMode) {
        this.studyMode = studyMode;
    }

    @JsonCreator
    public static AdmissionCharge create(String jsonString) {
        AdmissionCharge o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, AdmissionCharge.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
