package my.edu.umk.pams.account.account.service;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcResidencyCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.web.module.account.vo.AccountActivityHolder;

import java.io.InputStream;
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

    void parseFeeSchedule(InputStream inputStream);
    // ==================================================================================================== //
    // ACCOUNT CHARGE TRANSACTIONS
    // ==================================================================================================== //
    
    List<AcAccountChargeTransaction> findAccountChargeTransactions(AcAccount account);

    List<AcAccountChargeTransaction> findAccountChargeTransactions(AcAccount account, Integer offset, Integer limit);
    
    List<AcAccountChargeTransaction> findAccountChargeTransactions(String filter, AcAccount account, Integer offset, Integer limit);
    
    Integer countAccountChargeTransaction(AcAccount account);

    Integer countAccountChargeTransaction(String filter, AcAccount account);
    
    void addAccountChargeTransaction(AcAccount acAccount, AcAccountChargeTransaction transaction);

    void deleteAccountChargeTransaction(AcAccount acAccount, AcAccountChargeTransaction transaction);

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

    //BigDecimal sumWaiverAmount(AcAccount account);

    BigDecimal sumEffectiveBalanceAmount(AcAccount account, AcAcademicSession academicSession);
    
    BigDecimal sumInvoiceBalanceAmount(AcAccount account, AcAcademicSession academicSession);

    List<AcAccountTransaction> findAccountTransactions(AcAccount account);

    List<AcAccountTransaction> findAccountTransactions(AcAccount account, Integer offset, Integer limit);

    List<AcAccountTransaction> findAccountTransactions(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit);

    List<AcAccountTransaction> findAccountTransactions(String filter, AcAccount account, Integer offset, Integer limit);

    List<AcAccountActivityHolder> findAccountActivities(AcAccount account);

    List<AcAccountActivityHolder> findAccountActivities(AcAcademicSession academicSession, AcAccount account);
    
    List<AcActivityChargeHolder> findAccountActivitiesCharge(AcAccount account);

    List<AcActivityChargeHolder> findAccountActivitiesCharge(AcAcademicSession academicSession, AcAccount account);

    Integer countAccountTransaction(AcAccount account);

    Integer countAccountTransaction(AcAcademicSession academicSession, AcAccount account);

    Integer countAccountTransaction(String filter, AcAccount account);

    boolean hasAccountTransaction(String sourceNo);

    void saveAccount(AcAccount account);

    void updateAccount(AcAccount account);

    void addAccountTransaction(AcAccount acAccount, AcAccountTransaction transaction);

    void deleteAccountTransaction(AcAccount acAccount, AcAccountTransaction transaction);

    void reviseAccount(AcAccount acAccount);
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
    
    void calculateNetAmount(AcAccountCharge charge);
    
    void calculateSecurityChargeNetAmount(AcAccountCharge accountCharge);

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
    
    void updateAccountWaiver(AcAccountWaiver waiver);

	BigDecimal sumChargeAmount(AcAccount account);

	BigDecimal sumSecurityChargeAmount(AcAccount account);

    // ====================================================================================================
    // SPONSORSHIP
    // ====================================================================================================

    AcSponsorship findSponsorshipById(Long id);
    
    AcSponsorship findSponsorshipByReferenceNo(String referenceNo);
    
    List<AcSponsorship> findSponsorships(AcSponsor sponsor);
    
    List<AcSponsorship> findSponsorships(Integer offset, Integer limit);
    
    void saveSponsorship(AcSponsorship sponsorship);

    boolean hasSponsorship(AcStudent student);
    
//    void updateSponsorship(AcSponsorship sponsorship);

	List<AcSponsorship> findSponsorships(AcAccount account);

//	void addSponsorship(AcSponsor sponsor, AcSponsorship sponsorship);

	void addSponsorship(AcAccount account, AcSponsorship sponsorship);

	void updateSponsorship(AcAccount account, AcSponsorship sponsorship);

	void removeSponsorship(AcAccount account, AcSponsorship sponsorship);

	AcAccountChargeTransaction findAccountChargeTransactionById(Long id);

}
