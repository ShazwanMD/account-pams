package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface RefundApplication {

    Long getId();

    void setId(Long id);

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);

    AcAccount getAccount();

    void setAccount(AcAccount account);
}
