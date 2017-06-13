package my.edu.umk.pams.account.web.module.account.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * @author PAMS
 */
public class CompoundCharge extends AccountCharge {
    private String compoundCode;
    private String compoundDescription;



    public String getCompoundCode() {
		return compoundCode;
	}



	public void setCompoundCode(String compoundCode) {
		this.compoundCode = compoundCode;
	}



	public String getCompoundDescription() {
		return compoundDescription;
	}



	public void setCompoundDescription(String compoundDescription) {
		this.compoundDescription = compoundDescription;
	}



	@JsonCreator
    public static CompoundCharge create(String jsonString) {
        CompoundCharge o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, CompoundCharge.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
