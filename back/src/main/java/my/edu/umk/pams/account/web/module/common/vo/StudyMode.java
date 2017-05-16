package my.edu.umk.pams.account.web.module.common.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author PAMS
 */
public class StudyMode {

    private Long id;
    private String code;
    private String descriptionMs;
    private String descriptionEn;

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

    public String getDescriptionMs() {
        return descriptionMs;
    }

    public void setDescriptionMs(String descriptionMs) {
        this.descriptionMs = descriptionMs;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    @JsonCreator
    public static StudyMode create(String jsonString) {
        StudyMode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, StudyMode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
