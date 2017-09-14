package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.account.vo.AcademicSession;
import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.core.vo.Document;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplicationType;
import my.edu.umk.pams.account.web.module.billing.vo.WaiverFinanceApplication;

public class WaiverFinanceApplication extends Document {

    private String reason;
    private String memo;
    private BigDecimal balance;
    private BigDecimal effectiveBalance;
    private BigDecimal waivedAmount;
    private BigDecimal gracedAmount;
    private Account account;
    private AcademicSession academicSession;
    private WaiverApplicationType waiverType;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public BigDecimal getEffectiveBalance() {
        return effectiveBalance;
    }

    public void setEffectiveBalance(BigDecimal effectiveBalance) {
        this.effectiveBalance = effectiveBalance;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getWaivedAmount() {
        return waivedAmount;
    }

    public void setWaivedAmount(BigDecimal waivedAmount) {
        this.waivedAmount = waivedAmount;
    }

    public BigDecimal getGracedAmount() {
        return gracedAmount;
    }

    public void setGracedAmount(BigDecimal gracedAmount) {
        this.gracedAmount = gracedAmount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AcademicSession getAcademicSession() {
        return academicSession;
    }

    public void setAcademicSession(AcademicSession academicSession) {
        this.academicSession = academicSession;
    }
    
    public WaiverApplicationType getWaiverType() {
		return waiverType;
	}

	public void setWaiverType(WaiverApplicationType waiverType) {
		this.waiverType = waiverType;
	}

    @JsonCreator
    public static WaiverFinanceApplication create(String jsonString) {
    	WaiverFinanceApplication o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, WaiverFinanceApplication.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
