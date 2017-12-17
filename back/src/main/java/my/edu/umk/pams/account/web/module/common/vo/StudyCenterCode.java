package my.edu.umk.pams.account.web.module.common.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class StudyCenterCode {

	private Long id;
    private String code;
    private String description;
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
    
    @JsonCreator
    public static StudyCenterCode create(String jsonString) {
    	StudyCenterCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, StudyCenterCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
