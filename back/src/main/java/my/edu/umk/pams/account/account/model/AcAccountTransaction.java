package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
public interface AcAccountTransaction extends AcMetaObject {

    String getSourceNo();

    void setSourceNo(String sourceNo);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);
    
    String getDescription();

    void setDescription(String description);

    Date getPostedDate();

    void setPostedDate(Date postedDate);

    AcAccountTransactionCode getTransactionCode();

    void setTransactionCode(AcAccountTransactionCode transactionCode);

    AcAccount getAccount();

    void setAccount(AcAccount account);

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

    AcAcademicSession getSession();

    void setSession(AcAcademicSession academicSession);
}
