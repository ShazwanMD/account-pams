package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.web.module.account.vo.AccountActivityHolder;

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

    List<AcAccountActivityHolder> findAccountActivities(AcAccount account);

    List<AcAccountActivityHolder> findAccountActivities(AcAcademicSession academicSession, AcAccount account);
    
    List<AcActivityChargeHolder> findAccountActivitiesCharge(AcAccount account);

    List<AcActivityChargeHolder> findAccountActivitiesCharge(AcAcademicSession academicSession, AcAccount account);
    
    // ==================================================================================================== //
    // ACCOUNT CHARGE TRANSACTIONS
    // ==================================================================================================== //
    List<AcAccountChargeTransaction> findAccountChargeTransactions(AcAccount account);

    List<AcAccountChargeTransaction> findAccountChargeTransactions(AcAccount account, Integer offset, Integer limit);
    
    List<AcAccountChargeTransaction> findAccountChargeTransactions(String filter, AcAccount account, Integer offset, Integer limit);
    
    Integer countAccountChargeTransaction(AcAccount account);

    Integer countAccountChargeTransaction(String filter, AcAccount account);
    
    void addAccountChargeTransaction(AcAccount account, AcAccountChargeTransaction transaction, AcUser user);
    
    void deleteAccountChargeTransaction(AcAccount account, AcAccountChargeTransaction transaction, AcUser user);
    
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
    
    BigDecimal sumChargeDebitAmount(AcAccount account);

    BigDecimal sumCreditAmount(AcAccount account);
    
    BigDecimal sumAdvancePayment(AcAccount account);
    
    BigDecimal sumInvoice(AcAccount account);
    
    BigDecimal sumReceipt(AcAccount account, AcFlowState flowstate);
    
    BigDecimal sumKnockoff(AcAccount account, AcFlowState flowstate);

    BigDecimal sumWaiverAmount(AcAccount account, AcFlowState flowstate);
    
    BigDecimal sumRefundPayment(AcAccount account, AcFlowState flowstate);
    
    BigDecimal sumCreditNote(AcAccount account, AcFlowState flowstate);
    
    BigDecimal sumDebitNote(AcAccount account, AcFlowState flowstate);

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
    
    void addTransaction(AcAccount account, AcAccountChargeTransaction transaction, AcUser user);

    void updateAccountTransaction(AcAccount account, AcAccountTransaction transaction, AcUser user);

    void deleteTransaction(AcAccount account, AcAccountTransaction transaction, AcUser user);

	BigDecimal sumChargeAmount(AcAccount account);

	BigDecimal sumSecurityChargeAmount(AcAccount account);

	AcAccountChargeTransaction findAccountChargeTransactionById(Long id);
}
