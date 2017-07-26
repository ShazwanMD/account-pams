package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcAccountDao extends GenericDao<Long, AcAccount> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcAccount findByCode(String code);

    AcAccount findByNricNo(String nricNo);

    AcAccount findByActor(AcActor actor);

    AcAccountTransaction findAccountTransactionById(Long id);

    List<AcAccount> find(String filter, Integer offset, Integer limit);

    List<AcAccount> find(String filter, AcActorType actorType, Integer offset, Integer limit);

    List<AcAccountTransaction> findAccountTransactions(String sourceNo, AcAccount account);

    List<AcAccountTransaction> findAccountTransactions(AcAccount account);

    List<AcAccountTransaction> findAccountTransactions(AcAccount account, Integer offset, Integer limit);

    List<AcAccountTransaction> findAccountTransactions(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit);

    List<AcAccountTransaction> findAccountTransactions(String filter, AcAccount account, Integer offset, Integer limit);

    List<AcAccountActivityImpl> findAccountActivities(AcAccount account);

    List<AcAccountActivityImpl> findAccountActivities(AcAcademicSession academicSession, AcAccount account);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(String filter);

    Integer count(String filter, AcActorType actorType);

    Integer countAccountTransaction(AcAccount account);

    Integer countAccountTransaction(AcAcademicSession academicSession, AcAccount account);

    Integer countAccountTransaction(String filter, AcAccount account);

    BigDecimal sumBalanceAmount(AcAccount account);

    BigDecimal sumSurplusAmount(AcAccount account);

    BigDecimal sumDebitAmount(AcAccount account);

    BigDecimal sumCreditAmount(AcAccount account);

    BigDecimal sumWaiverAmount(AcAccount account);

    BigDecimal sumAccountTransaction(AcAccount account);

    BigDecimal sumAccountTransaction(AcAccount account, AcAcademicSession academicSession);

    // check
    boolean hasAccount(AcActor actor);

    boolean hasAccountTransaction(AcAccount account);

    boolean hasAccountTransaction(AcAcademicSession academicSession, AcAccount account);

    boolean hasAccountTransaction(String sourceNo);

    boolean hasSurplus(AcAccount account);

    boolean hasBalance(AcAcademicSession academicSession, AcActor actor);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addCharge(AcAccount account, AcAccountCharge charge, AcUser user);

    void updateCharge(AcAccount account, AcAccountCharge charge, AcUser user);

    void deleteCharge(AcAccount account, AcAccountCharge charge, AcUser user);

    void addWaiver(AcAccount account, AcAcademicSession academicSession, AcAccountWaiver waiver, AcUser user);

    void deleteWaiver(AcAccount account, AcAcademicSession academicSession, AcAccountWaiver waiver, AcUser user);

    void addTransaction(AcAccount account, AcAccountTransaction transaction, AcUser user);

    void updateAccountTransaction(AcAccount account, AcAccountTransaction transaction, AcUser user);

    void deleteTransaction(AcAccount account, AcAccountTransaction transaction, AcUser user);

    @Deprecated
    void addShortTermLoan(AcAccount account, AcAcademicSession academicSession, AcAccountSTL shortTermLoan, AcUser user);

    @Deprecated
    void deleteShortTermLoan(AcAccount account, AcAcademicSession academicSession, AcAccountSTL shortTermLoan, AcUser user);
}
