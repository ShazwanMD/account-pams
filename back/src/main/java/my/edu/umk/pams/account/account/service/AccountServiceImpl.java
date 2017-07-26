package my.edu.umk.pams.account.account.service;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.dao.*;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcResidencyCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcSettlementStatus;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.security.service.SecurityService;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private AccountService accountService;

    @Autowired
    private CommonService commonService;

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

    @Override
    public void parseFeeSchedule(InputStream inputStream) {
        try {
            Workbook workbook = WorkbookFactory.create(inputStream);
            int numberOfSheets = workbook.getNumberOfSheets();
            LOG.debug("number of sheets: " + numberOfSheets);

            for (int i = 0; i < numberOfSheets; i++) {
                Sheet sheet = workbook.getSheetAt(i);
//                writer.write("INSERT INTO AC_FEE_SCDL (ID,RESIDENCY_CODE_ID, COHORT_CODE_ID, STUDY_MODE_ID, CODE, DESCRIPTION, TOTAL_AMOUNT, M_ST, C_TS,C_ID) VALUES (\n"
//                        + "nextval('SQ_AC_FEE_SCDL'),"
//                        + "(SELECT ID from AC_RSCY_CODE where code ='" + getCell(sheet, 3, 1) + "' ),"
//                        + "(SELECT ID FROM AC_CHRT_CODE WHERE CODE = '" + getCell(sheet, 1, 1) + "' ),"
//                        + "(SELECT ID FROM AC_STDY_MODE WHERE CODE = '" + getCell(sheet, 2, 1) + "' ),"
//                        + "'YB-" + getCell(sheet, 1, 1) + "',"
//                        + "'" + getCell(sheet, 0, 1) + "',"
//                        + "0.00,"
//                        + "1,"
//                        + "CURRENT_TIMESTAMP,"
//                        + "1); \n\n\n\n\n");
                AcFeeSchedule schedule = new AcFeeScheduleImpl();
                schedule.setResidencyCode(commonService.findResidencyCodeByCode(getCell(sheet, 3, 1)));
                schedule.setCohortCode(commonService.findCohortCodeByCode(getCell(sheet, 1, 1)));
                schedule.setStudyMode(commonService.findStudyModeByCode(getCell(sheet, 2, 1)));
                schedule.setCode(getCell(sheet, 1, 1));
                schedule.setDescription(getCell(sheet, 0, 1));
                schedule.setTotalAmount(BigDecimal.ZERO);
                feeScheduleDao.save(schedule, securityService.getCurrentUser());
                sessionFactory.getCurrentSession().flush();
                sessionFactory.getCurrentSession().refresh(schedule);

                int lastRowNum = sheet.getLastRowNum();
                for (int j = 7; j < lastRowNum; j++) {
                    Row row = sheet.getRow(j);
                    if (row != null) {
                        LOG.debug(toString(row.getCell(0)));
                        LOG.debug(toString(row.getCell(1)));

                        if (!toString(row.getCell(0)).startsWith("YURAN SEMESTER")
                                || !toString(row.getCell(0)).startsWith("Jumlah")) {
                            AcFeeScheduleItem item = new AcFeeScheduleItemImpl();
                            item.setDescription(toString(row.getCell(0)));
                            item.setOrdinal(Integer.valueOf(toString(row.getCell(4), true)));
                            item.setChargeCode(findChargeCodeByCode(toString(row.getCell(3))));
                            item.setAmount(new BigDecimal(toString(row.getCell(1))));
                            item.setSchedule(schedule);
                            feeScheduleDao.addItem(schedule, item, securityService.getCurrentUser());
                            sessionFactory.getCurrentSession().flush();

//                            writer.write("INSERT INTO AC_FEE_SCDL_ITEM (ID,DESCRIPTION,ORDINAL,SCHEDULE_ID,CHARGE_CODE_ID, AMOUNT,C_TS,C_ID,M_ST) \n" +
//                                    " VALUES ("
//                                    + "nextval('SQ_AC_FEE_SCDL_ITEM'),"
//                                    + "'" + toString(row.getCell(0)) + "',"
//                                    + toString(row.getCell(4), true) + ","
//                                    + "currval('SQ_AC_FEE_SCDL'),"
//                                    + "(SELECT ID FROM AC_CHRG_CODE WHERE CODE = '" + row.getCell(3) + "'),"
//                                    + "'" + toString(row.getCell(1)) + "',"
//                                    + "CURRENT_TIMESTAMP,"
//                                    + "1,"
//                                    + "1);"
//                                    + "\n\n");
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOG.error(e.getMessage());
        } catch (InvalidFormatException e) {
            LOG.error(e.getMessage());
        }

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
    public BigDecimal sumWaiverAmount(AcAccount account) {
        return accountDao.sumWaiverAmount(account);
    }

    @Override
    public BigDecimal sumEffectiveBalanceAmount(AcAccount account, AcAcademicSession academicSession) {
        return accountDao.sumBalanceAmount(account)
                .subtract(accountDao.sumWaiverAmount(account));
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

    @Override
    public List<AcAccountActivityImpl> findAccountActivities(AcAccount account) {
        return accountDao.findAccountActivities(account);
    }

    @Override
    public List<AcAccountActivityImpl> findAccountActivities(AcAcademicSession academicSession, AcAccount account) {
        return accountDao.findAccountActivities(academicSession, account);
    }

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




    private String toString(Cell cell) {
        if (cell.getCellType() == 1)
            return cell.getStringCellValue();
        if (cell.getCellType() == 0)
            return Double.toString(cell.getNumericCellValue());
        if (cell.getCellType() == 2)
            return "";
        return "";
    }

    private String toString(Cell cell, boolean removeDecimal) {
        if (cell.getCellType() == 1)
            return cell.getStringCellValue();
        if (cell.getCellType() == 0)
            return Integer.toString((int) cell.getNumericCellValue());
        if (cell.getCellType() == 2)
            return "";
        return "";
    }

    private String getCell(Sheet sheet, int rowIndex, int colIndex) {
        Row row = sheet.getRow(rowIndex);
        Cell cell = row.getCell(colIndex);
        // LOG.debug("cell: " + cell.getCellType());
        //  LOG.debug("row :"+row.getRowNum());

        return toString(cell);
    }
}
