package my.edu.umk.pams.account.account.service;

import my.edu.umk.pams.account.account.dao.AcAccountChargeDao;
import my.edu.umk.pams.account.account.dao.AcAccountDao;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;
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
    private AcAccountChargeDao accountChargeDao;




    // ==================================================================================================== //
    // STUDENT ACCOUNT
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
        return accountDao.sumCreditAmount(account);
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

    // ==================================================================================================== //
    // ACCOUNT CHARGE
    // TODO: refactored per new  account
    // ==================================================================================================== //

    @Override
    public AcAccountCharge findAccountChargeById(Long id) {
        return accountChargeDao.findById(id);
    }

    @Override
    public AcAccountCharge findAccountChargeByReferenceNo(String referenceNo) {
        return accountChargeDao.findByReferenceNo(referenceNo);
    }

    @Override
    public List<AcAccountCharge> findAttachedAccountCharges(AcAccount account) {
        return accountChargeDao.findAttached(account);
    }

    @Override
    public List<AcAccountCharge> findAttachedAccountCharges(AcAcademicSession academicSession, AcAccount account) {
        return accountChargeDao.findAttached(academicSession, account);
    }

    @Override
    public List<AcAccountCharge> findDetachedAccountCharges(AcAccount account) {
        return accountChargeDao.findDetached(account);
    }

    @Override
    public List<AcAccountCharge> findDetachedAccountCharges(AcAcademicSession academicSession, AcAccount account) {
        return accountChargeDao.findDetached(academicSession, account);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAccount account) {
        return accountChargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAccount account, AcAccountChargeType... chargeTypes) {
        return accountChargeDao.find(account, chargeTypes);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAcademicSession academicSession, AcAccount account) {
        return accountChargeDao.find(academicSession, account);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit) {
        return accountChargeDao.find(academicSession, account, offset, limit);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAccountChargeType... chargeType) {
        return accountChargeDao.find(chargeType);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAcademicSession academicSession, AcAccountChargeType... chargeType) {
        return accountChargeDao.find(academicSession, chargeType);
    }

    @Override
    public List<AcAccountCharge> findUnpaidAccountCharges(AcAccount account) {
        return accountChargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findPaidAccountCharges(AcAccount account) {
        return accountChargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(AcAccount account, Integer offset, Integer limit) {
        return accountChargeDao.find(account, offset, limit);
    }

    @Override
    public List<AcAccountCharge> findUnpaidAccountCharges(AcAccount account, Integer offset, Integer limit) {
        return accountChargeDao.find(account, offset, limit); // TODO unpaid
    }

    @Override
    public List<AcAccountCharge> findPaidAccountCharges(AcAccount account, Integer offset, Integer limit) {
        return accountChargeDao.find(account, offset, limit); // TODO paid
    }

    @Override
    public List<AcAccountCharge> findAccountCharges(String filter, AcAccount account, Integer offset, Integer limit) {
        return accountChargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findUnpaidAccountCharges(String filter, AcAccount account, Integer offset, Integer limit) {
        return accountChargeDao.find(account);
    }

    @Override
    public List<AcAccountCharge> findPaidAccountCharges(String filter, AcAccount account, Integer offset, Integer limit) {
        return accountChargeDao.find(account);
    }

    @Override
    public Integer countAccountCharge(AcAccount account) {
        return accountChargeDao.count(account);
    }

    @Override
    public Integer countAccountCharge(AcAcademicSession academicSession, AcAccount account) {
        return accountChargeDao.count(academicSession, account);
    }

    @Override
    public Integer countAccountCharge(String filter, AcAccount account) {
        return accountChargeDao.count(account);
    }

    @Override
    public Integer countAttachedAccountCharge(AcAccount account) {
        return accountChargeDao.countAttached(account);
    }

    @Override
    public Integer countAttachedAccountCharge(AcAcademicSession academicSession, AcAccount account) {
        return accountChargeDao.countAttached(academicSession, account);
    }

    @Override
    public Integer countDetachedAccountCharge(AcAccount account) {
        return accountChargeDao.countDetached(account);
    }

    @Override
    public Integer countDetachedAccountCharge(AcAcademicSession academicSession, AcAccount account) {
        return accountChargeDao.countDetached(academicSession, account);
    }

    @Override
    public boolean isAccountChargeExists(String sourceNo) {
        return false; 
    }

    @Override
    public boolean isAccountChargeExists(AcAccount account, AcAccountChargeType chargeType, AcAcademicSession academicSession) {
        return accountChargeDao.isChargeExists(account, academicSession, chargeType);
    }




}
