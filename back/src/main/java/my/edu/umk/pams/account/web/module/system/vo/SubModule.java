package my.edu.umk.pams.account.web.module.system.vo;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

import my.edu.umk.pams.account.web.module.core.vo.MetaObject;


/**
 * @author PAMS
 */
public class SubModule extends MetaObject {

    private String code;
    private String canonicalCode;
    private String description;
    private Integer ordinal = 0;
    private boolean enabled = true;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCanonicalCode() {
        return canonicalCode;
    }

    public void setCanonicalCode(String canonicalCode) {
        this.canonicalCode = canonicalCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getOrdinal() {
        return ordinal;
    }

    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @JsonCreator
    public static SubModule create(String jsonString) {
        SubModule o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, SubModule.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
