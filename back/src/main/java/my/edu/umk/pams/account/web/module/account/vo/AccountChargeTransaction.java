package my.edu.umk.pams.account.web.module.account.vo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

public class AccountChargeTransaction extends MetaObject{

    private String sourceNo;
    private String description;
    private BigDecimal amount;
    private Date postedDate;
    private Account account;
    private AccountCharge chargeCode;
    private AcademicSession session;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public AccountCharge getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(AccountCharge chargeCode) {
        this.chargeCode = chargeCode;
    }

    public AcademicSession getSession() {
        return session;
    }

    public void setSession(AcademicSession session) {
        this.session = session;
    }

    @JsonCreator
    public static AccountTransaction create(String jsonString) {
        AccountTransaction o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, AccountTransaction.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
