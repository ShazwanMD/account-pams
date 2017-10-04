package my.edu.umk.pams.account.web.module.account.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplicationType;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
public class AccountWaiver extends MetaObject{

    private String sourceNo;
    private BigDecimal amount;
    private AcademicSession session;
    private boolean status;
    private WaiverApplicationType waiverType;

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

    public AcademicSession getSession() {
        return session;
    }

    public void setSession(AcademicSession session) {
        this.session = session;
    }

    public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public WaiverApplicationType getWaiverType() {
		return waiverType;
	}

	public void setWaiverType(WaiverApplicationType waiverType) {
		this.waiverType = waiverType;
	}

	@JsonCreator
    public static AccountWaiver create(String jsonString) {
        AccountWaiver o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, AccountWaiver.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
