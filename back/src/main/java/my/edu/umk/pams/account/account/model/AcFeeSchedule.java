package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcResidencyCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcFeeSchedule extends AcMetaObject{

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    AcCohortCode getCohortCode();

    void setCohortCode(AcCohortCode cohortCode);

    AcResidencyCode getResidencyCode();

    void setResidencyCode(AcResidencyCode residencyCode);

    AcStudyMode getStudyMode();

    void setStudyMode(AcStudyMode studyMode);
    
    Boolean getStatus();

    void setStatus(Boolean status);
}
