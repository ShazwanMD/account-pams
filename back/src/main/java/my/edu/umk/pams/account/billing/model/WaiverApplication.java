package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;

/**
 * @author PAMS
 */
public interface WaiverApplication {


    Long getId();

    void setId(Long id);

    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);

    AcAccount getAccount();

    void setAccount(AcAccount account);
}
