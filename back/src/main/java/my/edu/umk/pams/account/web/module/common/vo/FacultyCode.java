package my.edu.umk.pams.account.web.module.common.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

import java.io.IOException;

/**
 * @author PAMS
 */
public class FacultyCode extends MetaObject {
    private String code;
    private String description;

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

    @JsonCreator
    public static FacultyCode create(String jsonString) {
        FacultyCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, FacultyCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
