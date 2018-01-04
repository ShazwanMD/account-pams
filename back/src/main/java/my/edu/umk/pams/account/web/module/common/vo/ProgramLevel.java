package my.edu.umk.pams.account.web.module.common.vo;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;
import java.io.IOException;
/**
 * @author PAMS
 */
public class ProgramLevel extends MetaObject {
    private String code;
    private String prefix;
    private String description;
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getPrefix() {
        return prefix;
    }
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    @JsonCreator
    public static ProgramLevel create(String jsonString) {
        ProgramLevel o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ProgramLevel.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}