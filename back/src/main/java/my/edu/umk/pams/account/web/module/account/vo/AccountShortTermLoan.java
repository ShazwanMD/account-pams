package my.edu.umk.pams.account.web.module.account.vo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountShortTermLoan {
	private AcademicSession session;
	private String sourceNo;
	private BigDecimal amount;
	
    public AcademicSession getSession() {
        return session;
    }

    public void setSession(AcademicSession session) {
        this.session = session;
    }

	public String getSourceNo() {
		return sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
    @JsonCreator
    public static AccountShortTermLoan create(String jsonString) {
    	AccountShortTermLoan o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, AccountShortTermLoan.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
    
    

}
