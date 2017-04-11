package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcAccountWaiver extends AcMetaObject {

    String getSourceNo();

    void setSourceNo(String sourceNo);
    
    /**
     *
     * @return
     */
    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    /**
     *
     * @return
     */
    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);

    /**
     *
     * @return
     */
    AcAccount getAccount();

    void setAccount(AcAccount account);
}
