package my.edu.umk.pams.account.web.module.account.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.common.vo.CohortCode;
import my.edu.umk.pams.account.web.module.common.vo.ResidencyCode;
import my.edu.umk.pams.account.web.module.common.vo.StudyMode;

import java.io.IOException;
import java.math.BigDecimal;

public class FeeSchedule {

    private Long id;
    private String code;
    private String description;
    private ResidencyCode residencyCode;
    private CohortCode cohortCode;
    private StudyMode studyMode;
    private BigDecimal totalAmount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public ResidencyCode getResidencyCode() {
        return residencyCode;
    }

    public void setResidencyCode(ResidencyCode residencyCode) {
        this.residencyCode = residencyCode;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @JsonCreator
    public static FeeSchedule create(String jsonString) {
    	FeeSchedule o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, FeeSchedule.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
