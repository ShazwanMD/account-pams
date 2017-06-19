package my.edu.umk.pams.account.account.service;

import my.edu.umk.pams.account.account.dao.*;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcResidencyCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
@Transactional
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AcAccountDao accountDao;

    @Autowired
    private AcAccountChargeDao chargeDao;

    @Autowired
    private AcAccountWaiverDao waiverDao;

    @Autowired
    private AcChargeCodeDao chargeCodeDao;

    @Autowired
    private AcFeeScheduleDao feeScheduleDao;
    
    @Autowired
    private AcAccountSTLDao shortTermLoanDao;

    @Autowired
    private AcAcademicSessionDao academicSessionDao;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionFactory sessionFactory;

    //====================================================================================================
    // ACADEMIC SESSION
    //====================================================================================================

    @Override
    public AcAcademicSession findCurrentAcademicSession() {
        return academicSessionDao.findCurrentSession();
    }

    @Override
    public AcAcademicSession findAcademicSessionById(Long id) {
        return academicSessionDao.findById(id);
    }

    @Override
    public AcAcademicSession findAcademicSessionByCode(String code) {
        return academicSessionDao.findByCode(code);
    }

    @Override
    public List<AcAcademicSession> findAcademicSessions(Integer offset, Integer limit) {
        return academicSessionDao.find(offset, limit);
    }

    @Override
    public List<AcAcademicSession> findAcademicSessions(String filter, Integer offset, Integer limit) {
        return academicSessionDao.find(filter, offset, limit);
    }

    @Override
    public Integer countAcademicSession() {
        return academicSessionDao.count();
    }

    @Override
    public Integer countAcademicSession(String filter) {
        return academicSessionDao.count(filter);
    }

    @Override
    public boolean isAcademicSessionCodeExists(String code) {
        return academicSessionDao.isCodeExists(code);
    }

    @Override
    public void saveAcademicSession(AcAcademicSession academicSession) {
        academicSessionDao.save(academicSession, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateAcademicSession(AcAcademicSession academicSession) {
        academicSessionDao.update(academicSession, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeAcademicSession(AcAcademicSession academicSession) {
        academicSessionDao.remove(academicSession, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }


    //====================================================================================================
    // CHARGE CODE
    //====================================================================================================

    @Override
    public AcChargeCode findChargeCodeById(Long id) {
        return chargeCodeDao.findById(id);
    }

    @Override
    public AcChargeCode findChargeCodeByCode(String code) {
        return chargeCodeDao.findByCode(code);
    }

    @Override
    public AcChargeCode findChargeCodeByDescription(String description) {
        return chargeCodeDao.findByDescription(description);
    }

    @Override
    public List<AcChargeCode> findChargeCodes(String filter) {
        return findChargeCodes(filter, 0, Integer.MAX_VALUE);
    }

    @Override
    public List<AcChargeCode> findChargeCodes(String filter, Integer offset, Integer limit) {
        return chargeCodeDao.find(filter, offset, limit);
    }

    @Override
    public Integer countChargeCode() {
        return chargeCodeDao.count();
    }

    @Override
    public Integer countChargeCode(String filter) {
        return chargeCodeDao.count(filter);
    }

    @Override
    public void saveChargeCode(AcChargeCode chargeCode) {
        chargeCodeDao.save(chargeCode, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateChargeCode(AcChargeCode chargeCode) {
        chargeCodeDao.update(chargeCode, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeChargeCode(AcChargeCode chargeCode) {
        chargeCodeDao.remove(chargeCode, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    // ==================================================================================================== //
    // FEE SCHEDULE
    // ==================================================================================================== //

    @Override
    public AcFeeSchedule findFeeScheduleById(Long id) {
        return feeScheduleDao.findById(id);
    }

    @Override
    public AcFeeSchedule findFeeScheduleByCode(String code) {
        return feeScheduleDao.findByCode(code);
    }

    @Override
    public AcFeeSchedule findFeeScheduleByCohortCodeAndResidencyCodeAndStudyMode(AcCohortCode cohortCode,AcResidencyCode residencyCode, AcStudyMode studyMode) {
        return feeScheduleDao.findByCohortCodeAndResidencyCodeAndStudyMode(cohortCode, residencyCode, studyMode);
    }

    @Override
    public AcFeeScheduleItem findFeeScheduleItemById(Long id) {
        return feeScheduleDao.findItemById(id);
    }

    @Override
    public List<AcFeeSchedule> findFeeSchedules(Integer offset, Integer limit) {
        return feeScheduleDao.find(offset, limit);
    }

    @Override
    public List<AcFeeSchedule> findFeeSchedules(String filter, Integer offset, Integer limit) {
        return feeScheduleDao.find(filter, offset, limit);
    }

    @Override
    public List<AcFeeScheduleItem> findFeeScheduleItems(AcFeeSchedule schedule) {
        return feeScheduleDao.findItems(schedule);
    }

    @Override
    public Integer countFeeSchedule(String filter) {
        return feeScheduleDao.count(filter);
    }

    @Override
    public Integer countFeeSchedule(AcCohortCode cohortCode) {
        return feeScheduleDao.count(cohortCode);
    }

    @Override
    public Integer countFeeScheduleItem(AcFeeSchedule schedule) {
        return feeScheduleDao.countItem(schedule);
    }

    @Override
    public boolean hasFeeSchedule(AcCohortCode cohortCode) {
        return feeScheduleDao.hasSchedule(cohortCode);
    }

    @Override
    public void saveFeeSchedule(AcFeeSchedule schedule) {
        feeScheduleDao.save(schedule,securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateFeeSchedule(AcFeeSchedule schedule) {
        feeScheduleDao.update(schedule,securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteFeeSchedule(AcFeeSchedule schedule) {
        feeScheduleDao.delete(schedule, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addFeeScheduleItem(AcFeeSchedule schedule, AcFeeScheduleItem item) {
        feeScheduleDao.addItem(schedule, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateFeeScheduleItem(AcFeeSchedule schedule, AcFeeScheduleItem item) {
        feeScheduleDao.updateItem(schedule, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteFeeScheduleItem(AcFeeSchedule schedule, AcFeeScheduleItem item) {
        feeScheduleDao.deleteItem(schedule, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    // ==================================================================================================== //
    // ACCOUNT
    // ==================================================================================================== //

    @Override
    public AcAccount findAccountById(Long id) {
        return accountDao.findById(id);
    }

    @Override
    public AcAccount findAccountByCode(String code) {
        return accountDao.findByCode(code);
    }

    @Override
    public AcAccount findAccountByActor(AcActor actor) {
        return accountDao.findByActor(actor);
    }

    // todo(uda): decorate
    @Override
    public List<AcAccount> findAccounts(Integer offset, Integer limit) {
        return accountDao.find(offset, limit);
    }

    // todo(uda): decorate
    @Override
    public List<AcAccount> findAccounts(String filter, Integer offset, Integer limit) {
        return accountDao.find(filter, offset, limit);
    }

    // todo(uda): decorate
    @Override
    public List<AcAccount> findAccounts(String filter, AcActorType actorType, Integer offset, Integer limit) {
        return accountDao.find(filter, actorType, offset, limit);
    }

    @Override
    public Integer countAccount(String filter) {
        return accountDao.count(filter);
    }

    @Override
    public Integer countAccount(String filter, AcActorType actorType) {
        return accountDao.count(filter, actorType);
    }

    @Override
    public boolean hasAccount(AcActor actor) {
        return accountDao.hasAccount(actor);
    }

    @Override
    public boolean hasSurplus(AcAccount account) {
        return accountDao.hasSurplus(account);
    }

    @Override
    public boolean hasBalance(AcAccount account) {
        return false;
    }

    @Override
    public boolean hasBalance(AcAcademicSession academicSession, AcActor actor) {
        return accountDao.hasBalance(academicSession, actor);
    }

    @Override
    public BigDecimal sumSurplusAmount(AcAccount account) {
        return accountDao.sumDebitAmount(account);
    }

    @Override
    public BigDecimal sumBalanceAmount(AcAccount account) {
        return accountDao.sumBalanceAmount(account);
    }

    @Override
    public BigDecimal sumWaiverAmount(AcAccount account, AcAcademicSession academicSession) {
        return accountDao.sumWaiverAmount(account, academicSession);
    }

    @Override
    public BigDecimal sumEffectiveBalanceAmount(AcAccount account, AcAcademicSession academicSession) {
        return accountDao.sumBalanceAmount(account)
                .subtract(accountDao.sumWaiverAmount(account, academicSession));
    }

    @Override
    public List<AcAccountTransaction> findAccountTransactions(AcAccount account) {
        return accountDao.findAccountTransactions(account);
    }

    @Override
    public List<AcAccountTransaction> findAccountTransactions(AcAccount account, Integer offset, Integer limit) {
        return accountDao.findAccountTransactions(account, offset, limit);
    }

    @Override
    public List<AcAccountTransaction> findAccountTransactions(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit) {
        return accountDao.findAccountTransactions(academicSession, account, offset, limit);
    }

    @Override
    public List<AcAccountTransaction> findAccountTransactions(String filter, AcAccount account, Integer offset, Integer limit) {
        return accountDao.findAccountTransactions(filter, account, offset, limit);
    }

    // todo(uda): vo
//    @Override
//    public List<AcAccountActivity> findAccountActivities(AcAccount account) {
//        return accountDao.findAccountActivities(account);
//    }
//
//    @Override
//    public List<AcAccountActivity> findAccountActivities(AcAcademicSession academicSession, AcAccount account) {
//        return accountDao.findAccountActivities(academicSession, account);
//    }

    @Override
    public Integer countAccountTransaction(AcAccount account) {
        return accountDao.countAccountTransaction(account);
    }

    @Override
    public Integer countAccountTransaction(AcAcademicSession academicSession, AcAccount account) {
        return accountDao.countAccountTransaction(academicSession, account);
    }

    @Override
    public Integer countAccountTransaction(String filter, AcAccount account) {
        return accountDao.countAccountTransaction(filter, account);
    }

    @Override
    public boolean hasAccountTransaction(String sourceNo) {
        return accountDao.hasAccountTransaction(sourceNo);
    }

    @Override
    public void saveAccount(AcAccount account) {
        accountDao.save(account, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateAccount(AcAccount account) {
        accountDao.update(account, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addAccountTransaction(AcAccount account, AcAccountTransaction transaction) {
        accountDao.addTransaction(account, transaction, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteAccountTransaction(AcAccount account, AcAccountTransaction transaction) {
        accountDao.deleteTransaction(account, transaction, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
    
    // ==================================================================================================== //
    // ACCOUNT CHARGE
    // TODO: refactored per new  account
    // ==================================================================================================== //

    @Override
    public AcAccountCharge findAccountChargeById(Long id) {
        return chargeDao.findById(id);
    }

    @Override
    public AcAccountCharge findAccountChargeByReferenceNo(String referenceNo) {
        return chargeDao.findByReferenceNo(referenceNo);
    }

    @Override
    public List<AcAccountCharge> findAttachedAccountCharges(AcAccount account) {
        return chargeDao.findAttached(account);
    }

    @Override
    public List<AcAccountCharge> findAttachedAccountCharges(AcAcademicSession academicSession, AcAccount account) {
        return chargeDao.findAttached(academicSession, account);
    }

    @Override
    public List<AcAccountCharge> findDetachedAccountCharges(AcAccount account) {
        return chargeDao.findDetached(account);
    }

    @Override
    public List<AcAccountCharge> findDetachedAccountCharges(AcAcademicSession academicSession, AcAccount account) {
        return chargeDao.findDetached(academicSession, account);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAccount account) {
        return chargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAccount account, AcAccountChargeType... chargeTypes) {
        return chargeDao.find(account, chargeTypes);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAcademicSession academicSession, AcAccount account) {
        return chargeDao.find(academicSession, account);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit) {
        return chargeDao.find(academicSession, account, offset, limit);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAccountChargeType... chargeType) {
        return chargeDao.find(chargeType);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAcademicSession academicSession, AcAccountChargeType... chargeType) {
        return chargeDao.find(academicSession, chargeType);
    }

    @Override
    public List<AcAccountCharge> findUnpaidAccountCharges(AcAccount account) {
        return chargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findPaidAccountCharges(AcAccount account) {
        return chargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAccount account, Integer offset, Integer limit) {
        return chargeDao.find(account, offset, limit);
    }

    @Override
    public List<AcAccountCharge> findUnpaidAccountCharges(AcAccount account, Integer offset, Integer limit) {
        return chargeDao.find(account, offset, limit); // TODO unpaid
    }

    @Override
    public List<AcAccountCharge> findPaidAccountCharges(AcAccount account, Integer offset, Integer limit) {
        return chargeDao.find(account, offset, limit); // TODO paid
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(String filter, AcAccount account, Integer offset, Integer limit) {
        return chargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findUnpaidAccountCharges(String filter, AcAccount account, Integer offset, Integer limit) {
        return chargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findPaidAccountCharges(String filter, AcAccount account, Integer offset, Integer limit) {
        return chargeDao.find(account);
    }

    @Override
    public Integer countAccountCharge(AcAccount account) {
        return chargeDao.count(account);
    }

    @Override
    public Integer countAccountCharge(AcAcademicSession academicSession, AcAccount account) {
        return chargeDao.count(academicSession, account);
    }

    @Override
    public Integer countAccountCharge(String filter, AcAccount account) {
        return chargeDao.count(account);
    }

    @Override
    public Integer countAttachedAccountCharge(AcAccount account) {
        return chargeDao.countAttached(account);
    }

    @Override
    public Integer countAttachedAccountCharge(AcAcademicSession academicSession, AcAccount account) {
        return chargeDao.countAttached(academicSession, account);
    }

    @Override
    public Integer countDetachedAccountCharge(AcAccount account) {
        return chargeDao.countDetached(account);
    }

    @Override
    public Integer countDetachedAccountCharge(AcAcademicSession academicSession, AcAccount account) {
        return chargeDao.countDetached(academicSession, account);
    }

    @Override
    public boolean isAccountChargeExists(AcAccount account, String sourceNo) {
        return chargeDao.isChargeExists(account, sourceNo);
    }

    @Override
    public boolean isAccountChargeExists(AcAccount account, AcAccountChargeType chargeType, AcAcademicSession academicSession) {
        return chargeDao.isChargeExists(account, academicSession, chargeType);
    }

    @Override
    public void addAccountCharge(AcAccount account, AcAccountCharge charge) {
        accountDao.addCharge(account, charge, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateAccountCharge(AcAccount account, AcAccountCharge charge) {
    accountDao.updateCharge(account, charge, securityService.getCurrentUser());
    sessionFactory.getCurrentSession().flush();
    }
    
    @Override
    public void deleteAccountCharge(AcAccount account, AcAccountCharge charge) {
        accountDao.deleteCharge(account, charge, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    // ==================================================================================================== //
    //  ACCOUNT WAIVER
    // ==================================================================================================== //

    @Override
    public AcAccountWaiver findAccountWaiverById(Long id) {
        return waiverDao.findById(id);
    }

    @Override
    public AcAccountWaiver findAccountWaiverByReferenceNo(String referenceNo) {
        return waiverDao.findByReferenceNo(referenceNo);
    }

    @Override
    public List<AcAccountWaiver> findAccountWaivers(AcAccount account) {
        return waiverDao.find(account);
    }

    @Override
    public List<AcAccountWaiver> findAccountWaivers(AcAcademicSession academicSession, AcAccount account) {
        return waiverDao.find(academicSession, account);
    }

    @Override
    public Integer countAccountWaiver(AcAcademicSession academicSession, AcAccount account) {
        return waiverDao.count(academicSession, account);
    }

    @Override
    public boolean isAccountWaiverExists(AcAccount account, String sourceNo) {
        return waiverDao.isWaiverExists(account, sourceNo);
    }

    @Override
    public void addAccountWaiver(AcAccount account, AcAcademicSession academicSession, AcAccountWaiver waiver) {
        accountDao.addWaiver(account, academicSession, waiver, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeAccountWaiver(AcAccount account, AcAcademicSession academicSession, AcAccountWaiver waiver) {
        accountDao.addWaiver(account, academicSession, waiver, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
    
    // ==================================================================================================== //
    //  ACCOUNT SHORT TERM LOAN
    // ==================================================================================================== //

    @Override
    public AcAccountSTL findAccountShortTermLoanById(Long id) {
        return shortTermLoanDao.findById(id);
    }

    @Override
    public AcAccountSTL findAccountShortTermLoanByReferenceNo(String referenceNo) {
        return shortTermLoanDao.findByReferenceNo(referenceNo);
    }

    @Override
    public List<AcAccountSTL> findAccountShortTermLoans(AcAccount account) {
        return shortTermLoanDao.find(account);
    }

    @Override
    public List<AcAccountSTL> findAccountShortTermLoans(AcAcademicSession academicSession, AcAccount account) {
        return shortTermLoanDao.find(academicSession, account);
    }

    @Override
    public Integer countAccountShortTermLoan(AcAcademicSession academicSession, AcAccount account) {
        return shortTermLoanDao.count(academicSession, account);
    }

    @Override
    public boolean isAccountShortTermLoanExists(AcAccount account, String sourceNo) {
        return shortTermLoanDao.isShortTermlLoanExists(account, sourceNo);
    }

    @Override
    public void addShortTermLoan(AcAccount account, AcAcademicSession academicSession, AcAccountSTL shortTermLoan) {
    	accountDao.addShortTermLoan(account, academicSession, shortTermLoan, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void removeShortTermLoan(AcAccount account, AcAcademicSession academicSession, AcAccountSTL shortTermLoan) {
    	accountDao.addShortTermLoan(account, academicSession, shortTermLoan, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

}
