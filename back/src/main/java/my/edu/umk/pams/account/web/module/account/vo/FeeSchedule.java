package my.edu.umk.pams.account.web.module.account.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.common.vo.CohortCode;

import java.io.IOException;

public class FeeSchedule {

    private Long id;
    private String code;
    private String description;
    private CohortCode cohortCode;

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
