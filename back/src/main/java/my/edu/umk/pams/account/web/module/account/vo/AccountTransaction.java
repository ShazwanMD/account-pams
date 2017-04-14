package my.edu.umk.pams.account.web.module.account.vo;

import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
public class AccountTransaction extends MetaObject{

    private String sourceNo;
    private String description;
    private BigDecimal amount;
    private Date postedDate;
    private Account account;
    private ChargeCode chargeCode;
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

    public ChargeCode getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(ChargeCode chargeCode) {
        this.chargeCode = chargeCode;
    }

    public AcademicSession getSession() {
        return session;
    }

    public void setSession(AcademicSession session) {
        this.session = session;
    }
}
