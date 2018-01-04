package my.edu.umk.pams.account.web.module.common.vo;
import java.io.IOException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.financialaid.vo.GraduateCenterType;

public class ProgramCode {
    private Long id;
    private String code;
    private String description;
    
    private ProgramLevel programLevel;
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
    
    public ProgramLevel getProgramLevel() {
        return programLevel;
    }
    public void setProgramLevel(ProgramLevel programLevel) {
        this.programLevel = programLevel;
    }
    @JsonCreator
    public static ProgramCode create(String jsonString) {
        ProgramCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ProgramCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
    
    
    
}