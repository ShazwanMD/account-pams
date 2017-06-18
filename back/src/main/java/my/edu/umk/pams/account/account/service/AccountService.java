package my.edu.umk.pams.account.account.service;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcResidencyCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
public interface AccountService {

    //====================================================================================================
    // ADMISSION SESSION
    //====================================================================================================

    AcAcademicSession findAcademicSessionById(Long id);

    AcAcademicSession findAcademicSessionByCode(String code);

    AcAcademicSession findCurrentAcademicSession();

    List<AcAcademicSession> findAcademicSessions(Integer offset, Integer limit);

    List<AcAcademicSession> findAcademicSessions(String filter, Integer offset, Integer limit);

    Integer countAcademicSession();

    Integer countAcademicSession(String filter);

    boolean isAcademicSessionCodeExists(String code);

    void saveAcademicSession(AcAcademicSession academicSession);

    void updateAcademicSession(AcAcademicSession academicSession);

    void removeAcademicSession(AcAcademicSession academicSession);

    //====================================================================================================
    // CHARGE CODE
    //====================================================================================================

    AcChargeCode findChargeCodeById(Long id);

    AcChargeCode findChargeCodeByCode(String code);

    AcChargeCode findChargeCodeByDescription(String description);

    List<AcChargeCode> findChargeCodes(String filter);

    List<AcChargeCode> findChargeCodes(String filter, Integer offset, Integer limit);

    Integer countChargeCode();

    Integer countChargeCode(String filter);

    void saveChargeCode(AcChargeCode code);

    void updateChargeCode(AcChargeCode code);

    void removeChargeCode(AcChargeCode code);

    //====================================================================================================
    // FEE SCHEDULE
    //====================================================================================================

    AcFeeSchedule findFeeScheduleById(Long id);

    AcFeeSchedule findFeeScheduleByCode(String code);

    AcFeeSchedule findFeeScheduleByCohortCodeAndResidencyCodeAndStudyMode(AcCohortCode cohortCode, AcResidencyCode residencyCode, AcStudyMode studyMode);

    AcFeeScheduleItem findFeeScheduleItemById(Long id);

    List<AcFeeSchedule> findFeeSchedules(Integer offset, Integer limit);

    List<AcFeeSchedule> findFeeSchedules(String filter, Integer offset, Integer limit);

    List<AcFeeScheduleItem> findFeeScheduleItems(AcFeeSchedule schedule);

    Integer countFeeSchedule(String filter);

    Integer countFeeSchedule(AcCohortCode cohortCode);

    Integer countFeeScheduleItem(AcFeeSchedule schedule);

    boolean hasFeeSchedule(AcCohortCode cohortCode);

    void saveFeeSchedule(AcFeeSchedule schedule);

    void updateFeeSchedule(AcFeeSchedule schedule);

    void deleteFeeSchedule(AcFeeSchedule schedule);

    void addFeeScheduleItem(AcFeeSchedule schedule, AcFeeScheduleItem item);

    void updateFeeScheduleItem(AcFeeSchedule schedule, AcFeeScheduleItem item);

    void deleteFeeScheduleItem(AcFeeSchedule schedule, AcFeeScheduleItem item);

    // ==================================================================================================== //
    // ACCOUNT
    // ==================================================================================================== //

    AcAccount findAccountById(Long id);

    AcAccount findAccountByCode(String code);

    AcAccount findAccountByActor(AcActor actor);

    List<AcAccount> findAccounts(Integer offset, Integer limit);

    List<AcAccount> findAccounts(String filter, Integer offset, Integer limit);

    List<AcAccount> findAccounts(String filter, AcActorType actorType, Integer offset, Integer limit);

    Integer countAccount(String filter);

    Integer countAccount(String filter, AcActorType actorType);

    boolean hasAccount(AcActor actor);

    boolean hasSurplus(AcAccount account);

    boolean hasBalance(AcAccount account);

    BigDecimal sumSurplusAmount(AcAccount account);

    BigDecimal sumBalanceAmount(AcAccount account);

    BigDecimal sumWaiverAmount(AcAccount account, AcAcademicSession academicSession);

    BigDecimal sumEffectiveBalanceAmount(AcAccount account, AcAcademicSession academicSession);

    List<AcAccountTransaction> findAccountTransactions(AcAccount account);

    List<AcAccountTransaction> findAccountTransactions(AcAccount account, Integer offset, Integer limit);

    List<AcAccountTransaction> findAccountTransactions(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit);

    List<AcAccountTransaction> findAccountTransactions(String filter, AcAccount account, Integer offset, Integer limit);

//    List<AcAccountActivity> findAccountActivities(AcAccount account);
//
//    List<AcAccountActivity> findAccountActivities(AcAcademicSession academicSession, AcAccount account);

    Integer countAccountTransaction(AcAccount account);

    Integer countAccountTransaction(AcAcademicSession academicSession, AcAccount account);

    Integer countAccountTransaction(String filter, AcAccount account);

    boolean hasAccountTransaction(String sourceNo);

    void saveAccount(AcAccount account);

    void updateAccount(AcAccount account);

    void addAccountTransaction(AcAccount acAccount, AcAccountTransaction transaction);

    void deleteAccountTransaction(AcAccount acAccount, AcAccountTransaction transaction);

    // business
    // activate
    // deactivate

    // ==================================================================================================== //
    //  ACCOUNT CHARGE
    // ==================================================================================================== //

    AcAccountCharge findAccountChargeById(Long id);

    AcAccountCharge findAccountChargeByReferenceNo(String referenceNo);

    List<AcAccountCharge> findAttachedAccountCharges(AcAccount account);

    List<AcAccountCharge> findAttachedAccountCharges(AcAcademicSession academicSession, AcAccount account);

    List<AcAccountCharge> findDetachedAccountCharges(AcAccount account);

    List<AcAccountCharge> findDetachedAccountCharges(AcAcademicSession academicSession, AcAccount account);

    List<AcAccountCharge> findAccountCharges(AcAccount account);

    List<AcAccountCharge> findAccountCharges(AcAccount account, AcAccountChargeType... chargeTypes);

    List<AcAccountCharge> findAccountCharges(AcAcademicSession academicSession, AcAccount account);

    List<AcAccountCharge> findAccountCharges(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit);

    List<AcAccountCharge> findAccountCharges(AcAccountChargeType... chargeType);

    List<AcAccountCharge> findAccountCharges(AcAcademicSession academicSession, AcAccountChargeType... chargeType);

    List<AcAccountCharge> findUnpaidAccountCharges(AcAccount account);

    List<AcAccountCharge> findPaidAccountCharges(AcAccount account);

    List<AcAccountCharge> findAccountCharges(AcAccount account, Integer offset, Integer limit);

    List<AcAccountCharge> findUnpaidAccountCharges(AcAccount account, Integer offset, Integer limit);

    List<AcAccountCharge> findPaidAccountCharges(AcAccount account, Integer offset, Integer limit);

    List<AcAccountCharge> findAccountCharges(String filter, AcAccount account, Integer offset, Integer limit);

    List<AcAccountCharge> findUnpaidAccountCharges(String filter, AcAccount account, Integer offset, Integer limit);

    List<AcAccountCharge> findPaidAccountCharges(String filter, AcAccount account, Integer offset, Integer limit);

    Integer countAccountCharge(AcAccount account);

    Integer countAccountCharge(AcAcademicSession academicSession, AcAccount account);

    Integer countAccountCharge(String filter, AcAccount account);

    boolean isAccountChargeExists(AcAccount account, String sourceNo);

    boolean isAccountChargeExists(AcAccount account, AcAccountChargeType chargeType, AcAcademicSession academicSession);

    boolean hasBalance(AcAcademicSession academicSession, AcActor actor);

    Integer countAttachedAccountCharge(AcAccount account);

    Integer countAttachedAccountCharge(AcAcademicSession academicSession, AcAccount account);

    Integer countDetachedAccountCharge(AcAccount account);

    Integer countDetachedAccountCharge(AcAcademicSession academicSession, AcAccount account);

    // todo: return refno
    void addAccountCharge(AcAccount acAccount, AcAccountCharge charge);
    
    void updateAccountCharge(AcAccount account, AcAccountCharge charge);

    void deleteAccountCharge(AcAccount acAccount, AcAccountCharge charge);

    // ==================================================================================================== //
    //  ACCOUNT WAIVER
    // ==================================================================================================== //

    AcAccountWaiver findAccountWaiverById(Long id);

    AcAccountWaiver findAccountWaiverByReferenceNo(String referenceNo);

    List<AcAccountWaiver> findAccountWaivers(AcAccount account);

    List<AcAccountWaiver> findAccountWaivers(AcAcademicSession academicSession, AcAccount account);

    Integer countAccountWaiver(AcAcademicSession academicSession, AcAccount account);

    boolean isAccountWaiverExists(AcAccount account, String sourceNo);

    void addAccountWaiver(AcAccount acAccount, AcAcademicSession academicSession, AcAccountWaiver waiver);

    void removeAccountWaiver(AcAccount acAccount, AcAcademicSession academicSession, AcAccountWaiver waiver);

}
