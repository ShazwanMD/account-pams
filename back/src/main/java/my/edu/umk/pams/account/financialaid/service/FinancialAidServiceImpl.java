package my.edu.umk.pams.account.financialaid.service;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.dao.AcAcademicSessionDao;
import my.edu.umk.pams.account.account.dao.AcAccountDao;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.dao.AcInvoiceDao;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.dao.AcSettlementDao;
import my.edu.umk.pams.account.financialaid.dao.AcShortTermLoanDao;
import my.edu.umk.pams.account.financialaid.dao.AcWaiverApplicationDao;
import my.edu.umk.pams.account.financialaid.model.*;
import my.edu.umk.pams.account.identity.dao.AcStudentDao;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.workflow.service.WorkflowConstants;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.edu.umk.pams.account.AccountConstants.SETTLEMENT_REFERENCE_NO;
import static my.edu.umk.pams.account.AccountConstants.WAIVER_APPLICATION_ID;
import static my.edu.umk.pams.account.AccountConstants.SHORT_TERM_LOAN_ID;

/**
 * @author PAMS
 */
@Transactional
@Service("financialaidService")
public class FinancialAidServiceImpl implements FinancialAidService {

    private static final Logger LOG = LoggerFactory.getLogger(FinancialAidServiceImpl.class);

    @Autowired
    private AcAccountDao accountDao;

    @Autowired
    private AcStudentDao studentDao;

    @Autowired
    private AcSettlementDao settlementDao;

    @Autowired
    private AcWaiverApplicationDao waiverApplicationDao;

    @Autowired
    private AcInvoiceDao invoiceDao;

    @Autowired
    private AcAcademicSessionDao academicSessionDao;
    
    @Autowired
    private AcShortTermLoanDao shortTermLoanDao;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private BillingService billingService;

    // ====================================================================================================
    // SETTLEMENT
    // ====================================================================================================

    @Override
    public AcSettlement findSettlementById(Long id) {
        return settlementDao.findById(id);
    }

    @Override
    public AcSettlement findSettlementByReferenceNo(String referenceNo) {
        return settlementDao.findByReferenceNo(referenceNo);
    }

    @Override
    public AcSettlementItem findSettlementItemById(Long id) {
        return settlementDao.findItemById(id);
    }

    @Override
    public List<AcSettlement> findSettlements(Integer offset, Integer limit) {
        return settlementDao.find(offset, limit);
    }

    @Override
    public List<AcSettlement> findSettlements(AcAcademicSession academicSession, Integer offset, Integer limit) {
        return settlementDao.find(academicSession, offset, limit);
    }

    @Override
    public List<AcSettlementItem> findSettlementItems(AcSettlement settlement) {
        return settlementDao.findItems(settlement);
    }

    @Override
    public List<AcSettlementItem> findSettlementItems(AcSettlement settlement, Integer offset, Integer limit) {
        return settlementDao.findItems(settlement, offset, limit);
    }

    @Override
    public Integer countSettlement() {
        return settlementDao.count();
    }

    @Override
    public Integer countSettlement(AcAcademicSession academicSession) {
        return settlementDao.count(academicSession);
    }

    @Override
    public Integer countSettlementItem(AcSettlement settlement) {
        return settlementDao.countItem(settlement);
    }

    @Deprecated
    @Override
    public String initSettlement(AcSettlement settlement) {
        // prepare reference no generator
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("academicSession", academicSessionDao.findCurrentSession());
        String referenceNo = systemService.generateFormattedReferenceNo(SETTLEMENT_REFERENCE_NO, map);
        settlement.setReferenceNo(referenceNo);
        LOG.debug("Processing process settlement with refNo {}", referenceNo);

        // save
        settlementDao.saveOrUpdate(settlement, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();

        // generate item
        AcSponsor sponsor = null; // todo: remove this
        List<AcStudent> students = studentDao.find(sponsor);
        for (AcStudent student : students) {
            AcSettlementItem item = new AcSettlementItemImpl();
            item.setSettlement(settlement);
            item.setAccount(accountDao.findByActor(student));
            item.setStatus(AcSettlementStatus.NEW);
            item.setSettlement(settlement);
            addSettlementItem(settlement, item);
        }
        sessionFactory.getCurrentSession().flush();
        return referenceNo;
    }

    @Override
    public String initSettlementByFacultyCode(AcSettlement settlement, AcFacultyCode facultyCode) {
        List<AcStudent> students = identityService.findStudentByFacultyCode(facultyCode);
        return initSettlement(settlement, students);
    }

    @Override
    public String initSettlementBySponsor(AcSettlement settlement, AcSponsor sponsor) {
        List<AcStudent> students = identityService.findStudentBySponsor(sponsor);
        return initSettlement(settlement, students);
    }

    @Override
    public String initSettlementByCohortCode(AcSettlement settlement, AcCohortCode cohortCode) {
        List<AcStudent> students = identityService.findStudentByCohortCode(cohortCode);
        LOG.debug("student size: {}", students.size());
        return initSettlement(settlement, students);
    }

    @Override
    public void executeSettlement(AcSettlement settlement) {
        LOG.debug("settlement {}", settlement.getReferenceNo());
        // find all items for settlement
        // then iterate all, skip AcSettlementStatus.DISQUALIFIED
        List<AcSettlementItem> settlementItems = settlementDao.findItems(settlement);
        for (AcSettlementItem item : settlementItems) {
            if (item.getStatus() != AcSettlementStatus.DISQUALIFIED) {
                // generate reference no
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("academicSession", settlement.getSession());
                String referenceNo = systemService.generateFormattedReferenceNo(AccountConstants.INVOICE_REFERENCE_NO,
                        map);

                // generate reference no
                Map<String, Object> mapInvoice = new HashMap<String, Object>();
                mapInvoice.put("academicSession", settlement.getSession());
                String invoiceNo = systemService.generateFormattedReferenceNo(AccountConstants.INVOICE_REFERENCE_NO,
                        mapInvoice);

                // draft invoice
                AcInvoice invoice = new AcInvoiceImpl();
                invoice.setReferenceNo(referenceNo);
                invoice.setAccount(item.getAccount());
                invoice.setSession(settlement.getSession());
                invoice.setIssuedDate(settlement.getIssuedDate());
                invoice.setDescription(settlement.getDescription());
                invoice.setTotalAmount(BigDecimal.ZERO);
                invoice.setBalanceAmount(BigDecimal.ZERO);
                invoice.setPaid(false);
                invoice.setInvoiceNo(invoiceNo);

                // serialize to invoice DRAFT
                String invoiceReferenceNo = billingService.startInvoiceTask(invoice);
                invoice = billingService.findInvoiceByReferenceNo(invoiceReferenceNo);
                item.setInvoice(invoice);
                updateSettlementItem(settlement, item);
                sessionFactory.getCurrentSession().flush();
            }
        }
        settlement.setExecuted(true);
        updateSettlement(settlement);
    }

    @Override
    public void saveSettlement(AcSettlement settlement) {
        settlementDao.save(settlement, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateSettlement(AcSettlement settlement) {
        settlementDao.update(settlement, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addSettlementItem(AcSettlement settlement, AcSettlementItem item) {
        settlementDao.addItem(settlement, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateSettlementItem(AcSettlement settlement, AcSettlementItem item) {
        settlementDao.updateItem(settlement, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteSettlementItem(AcSettlement settlement, AcSettlementItem item) {
        settlementDao.deleteItem(settlement, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    // ====================================================================================================
    // //
    // WAIVER APPLICATION
    // ====================================================================================================
    // //

    // workflow

    @Override
    public AcWaiverApplication findWaiverApplicationByTaskId(String taskId) {
        Task task = workflowService.findTask(taskId);
        Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
        return waiverApplicationDao.findById((Long) map.get(WAIVER_APPLICATION_ID));
    }

    @Override
    public Task findWaiverApplicationTaskByTaskId(String taskId) {
        return workflowService.findTask(taskId);
    }

    @Override
    public List<Task> findAssignedWaiverApplicationTasks(Integer offset, Integer limit) {
        return workflowService.findAssignedTasks(AcWaiverApplication.class.getName(), offset, limit);
    }

    @Override
    public List<Task> findPooledWaiverApplicationTasks(Integer offset, Integer limit) {
        return workflowService.findPooledTasks(AcWaiverApplication.class.getName(), offset, limit);
    }

    @Override
    public String startWaiverApplicationTask(AcWaiverApplication application) {
        String referenceNo = systemService.generateReferenceNo(AccountConstants.WAIVER_APPLICATION_REFERENCE_NO);
        application.setReferenceNo(referenceNo);
        LOG.debug("Processing application with refNo {}", referenceNo);

        waiverApplicationDao.saveOrUpdate(application, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(application);

        workflowService.processWorkflow(application, prepareVariables(application));
        return referenceNo;
    }

    @Override
    public void updateWaiverApplication(AcWaiverApplication application) {
        waiverApplicationDao.update(application, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void cancelWaiverApplication(AcWaiverApplication application) {
        application.getFlowdata().setState(AcFlowState.CANCELLED);
        application.getFlowdata().setCancelledDate(new Timestamp(System.currentTimeMillis()));
        application.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
        waiverApplicationDao.update(application, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public AcWaiverApplication findWaiverApplicationById(Long id) {
        return waiverApplicationDao.findById(id);
    }

    @Override
    public AcWaiverApplication findWaiverApplicationByReferenceNo(String referenceNo) {
        return waiverApplicationDao.findByReferenceNo(referenceNo);
    }
    
    @Override
    public List<AcWaiverApplication> findWaiverApplicationsByFlowState(AcFlowState acFlowState) {
    	return waiverApplicationDao.findByFlowState(acFlowState);
    }

    @Override
    public List<AcWaiverApplication> findWaiverApplicationsByFlowStates(AcGraduateCenterType graduateCenterType, AcFlowState... acFlowState) {
    	return waiverApplicationDao.findByFlowStates(graduateCenterType, acFlowState);
    }

    @Override
    public List<AcWaiverApplication> findWaiverApplications(String filter, Integer offset, Integer limit) {
        return waiverApplicationDao.find(offset, limit);
    }

    @Override
    public List<AcWaiverApplication> findWaiverApplications(AcAcademicSession academicSession, Integer offset,
                                                            Integer limit) {
        return waiverApplicationDao.find(academicSession, offset, limit);
    }

    @Override
    public Integer countWaiverApplication(String filter) {
        return waiverApplicationDao.count();
    }

    @Override
    public Integer countWaiverApplication(AcAcademicSession academicSession) {
        return waiverApplicationDao.count(academicSession);
    }

    // ====================================================================================================
    // SHORT TERM LOAN
    // ====================================================================================================
    
    @Override
    public AcShortTermLoan findShortTermLoanByTaskId(String taskId) {
        Task task = workflowService.findTask(taskId);
        Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
        return shortTermLoanDao.findById((Long) map.get(SHORT_TERM_LOAN_ID));
    }

    @Override
    public Task findShortTermLoanTaskByTaskId(String taskId) {
        return workflowService.findTask(taskId);
    }

    @Override
    public List<Task> findAssignedShortTermLoanTasks(Integer offset, Integer limit) {
        return workflowService.findAssignedTasks(AcShortTermLoan.class.getName(), offset, limit);
    }

    @Override
    public List<Task> findPooledShortTermLoanTasks(Integer offset, Integer limit) {
        return workflowService.findPooledTasks(AcShortTermLoan.class.getName(), offset, limit);
    }

    @Override
    public String startShortTermLoanTask(AcShortTermLoan shortTermLoan) {
        String referenceNo = systemService.generateReferenceNo(AccountConstants.SHORT_TERM_LOAN_REFERENCE_NO);
        shortTermLoan.setReferenceNo(referenceNo);
        LOG.debug("Processing application with refNo {}", referenceNo);

        shortTermLoanDao.saveOrUpdate(shortTermLoan, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(shortTermLoan);

        workflowService.processWorkflow(shortTermLoan, prepareVariables(shortTermLoan));
        return referenceNo;
    }

    @Override
    public void updateShortTermLoanTask(AcShortTermLoan shortTermLoan) {
    	shortTermLoanDao.update(shortTermLoan, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void cancelShortTermLoan(AcShortTermLoan shortTermLoan) {
    	shortTermLoan.getFlowdata().setState(AcFlowState.CANCELLED);
    	shortTermLoan.getFlowdata().setCancelledDate(new Timestamp(System.currentTimeMillis()));
    	shortTermLoan.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
        shortTermLoanDao.update(shortTermLoan, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
    
    @Override
    public AcShortTermLoan findShortTermLoanById(Long id) {
        return shortTermLoanDao.findById(id);
    }
    
    @Override
    public AcShortTermLoan findShortTermLoanByReferenceNo(String referenceNo) {
        return shortTermLoanDao.findByReferenceNo(referenceNo);
    }
    
    @Override
    public List<AcShortTermLoan> findShortTermLoans(AcAcademicSession academicSession, Integer offset, Integer limit) {
        return shortTermLoanDao.find(academicSession, offset, limit);
    }
    
    @Override
    public void addShortTermLoan(AcShortTermLoan shortTermLoan) {
    	shortTermLoanDao.addShortTermLoan(shortTermLoan, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateShortTermLoan(AcShortTermLoan shortTermLoan) {
    	shortTermLoanDao.updateShortTermLoan(shortTermLoan, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteShortTermLoan(AcShortTermLoan shortTermLoan) {
    	shortTermLoanDao.deleteShortTermLoan(shortTermLoan, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
    
    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================

    private String initSettlement(AcSettlement settlement, List<AcStudent> students) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("academicSession", academicSessionDao.findCurrentSession());
        String referenceNo = systemService.generateFormattedReferenceNo(SETTLEMENT_REFERENCE_NO, map);
        settlement.setReferenceNo(referenceNo);
        settlement.setExecuted(false);
        LOG.debug("Processing process settlement with refNo {}", referenceNo);

        // save
        settlementDao.saveOrUpdate(settlement, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();

        // generate item
        for (AcStudent student : students) {
            AcSettlementItem item = new AcSettlementItemImpl();
            item.setSettlement(settlement);
            item.setAccount(accountDao.findByActor(student));
            item.setStatus(AcSettlementStatus.NEW);
            item.setSettlement(settlement);
            addSettlementItem(settlement, item);
        }
        sessionFactory.getCurrentSession().flush();
        return referenceNo;
    }

    private Map<String, Object> prepareVariables(AcWaiverApplication application) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(WAIVER_APPLICATION_ID, application.getId());
        map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
        map.put(WorkflowConstants.REFERENCE_NO, application.getReferenceNo());
        map.put(WorkflowConstants.REMOVE_DECISION, false);
        map.put(WorkflowConstants.CANCEL_DECISION, false);
        return map;
    }
    
    private Map<String, Object> prepareVariables(AcShortTermLoan shortTermLoan) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(SHORT_TERM_LOAN_ID, shortTermLoan.getId());
        map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
        map.put(WorkflowConstants.REFERENCE_NO, shortTermLoan.getReferenceNo());
        map.put(WorkflowConstants.REMOVE_DECISION, false);
        map.put(WorkflowConstants.CANCEL_DECISION, false);
        return map;
    }

}
