package my.edu.umk.pams.account.account.model;

/**
 * @author PAMS
 */
public interface AcAccountTransaction {


    Long getId();

    void setId(Long id);

    AcAccount getAccount();

    void setAccount(AcAccount account);

    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);
}
