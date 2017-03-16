package my.edu.umk.pams.account.billing.service;

import my.edu.umk.pams.account.account.dao.AcAcademicSessionDao;
import my.edu.umk.pams.account.account.dao.AcAccountChargeDao;
import my.edu.umk.pams.account.account.dao.AcAccountDao;
import my.edu.umk.pams.account.account.dao.AcChargeCodeDao;
import my.edu.umk.pams.account.account.service.AccountServiceImpl;
import my.edu.umk.pams.account.billing.dao.AcReceiptDao;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcReceiptType;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.workflow.service.WorkflowConstants;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.Validate;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.edu.umk.pams.account.AccountConstants.RECEIPT_ID;
import static my.edu.umk.pams.account.core.AcFlowState.DRAFTED;

/**
 * @author PAMS
 */
@Transactional
@Service("billingService")
public class BillingServiceImpl implements BillingService {

    private static final Logger LOG = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AcAccountDao accountDao;

    @Autowired
    private AcAccountChargeDao accountChargeDao;

    @Autowired
    private AcChargeCodeDao chargeCodeDao;

    @Autowired
    private AcAcademicSessionDao academicSessionDao;

    @Autowired
    private AcReceiptDao receiptDao;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private WorkflowService workflowService;

    // ==================================================================================================== //
    // RECEIPT
    // ==================================================================================================== //

    @Override
    public AcReceipt findReceiptByTaskId(String taskId) {
        Task task = workflowService.findTask(taskId);
        Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
        return receiptDao.findById((Long) map.get(RECEIPT_ID));
    }

    @Override
    public List<Task> findAssignedReceiptTasks(Integer offset, Integer limit) {
        return workflowService.findAssignedTasks(AcReceipt.class.getName(), offset, limit);
    }

    @Override
    public List<Task> findPooledReceiptTasks(Integer offset, Integer limit) {
        return workflowService.findPooledTasks(AcReceipt.class.getName(), offset, limit);
    }


    @Override
    public void startReceiptTask(AcReceipt receipt) {
        receiptDao.save(receipt, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(receipt);

        // trigger workflow
        workflowService.processWorkflow(receipt, prepareVariables(receipt));
    }

    @Override
    public void updateReceipt(AcReceipt receipt) {
        receiptDao.update(receipt, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void cancelReceipt(AcReceipt receipt) {
        Validate.notNull(receipt, "Receipt cannot be null");
        Validate.isTrue(DRAFTED.equals(receipt.getFlowdata().getState()), "Receipt can only be cancelled in DRAFTED state");
        receiptDao.update(receipt, securityService.getCurrentUser());
    }

    @Override
    public void addReceiptItem(AcReceipt receipt, AcReceiptItem item) {
        receiptDao.addItem(receipt, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateReceiptItem(AcReceipt receipt, AcReceiptItem item) {
        receiptDao.updateItem(receipt, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteReceiptItem(AcReceipt receipt, AcReceiptItem item) {
        receiptDao.deleteItem(receipt, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    // ==================================================================================================== //
    // RECEIPT
    // ==================================================================================================== //

    @Override
    public AcReceipt findReceiptById(Long id) {
        return receiptDao.findById(id);
    }

    @Override
    public AcReceipt findReceiptByReferenceNo(String referenceNo) {
        return receiptDao.findByReferenceNo(referenceNo);
    }

    @Override
    public AcReceipt findReceiptByReceiptNo(String receiptNo) {
        return receiptDao.findByReceiptNo(receiptNo);
    }

    @Override
    public AcReceiptItem findReceiptItemById(Long id) {
        return receiptDao.findItemById(id);
    }

    @Override
    public List<AcReceipt> findReceipts(AcReceiptType type, Integer offset, Integer limit) {
        return receiptDao.find(type, offset, limit);
    }

    @Override
    public List<AcReceipt> findReceipts(AcReceiptType type, String filter, Integer offset, Integer limit) {
        return receiptDao.find(type, filter, offset, limit);
    }

    @Override
    public List<AcReceiptItem> findReceiptItems(AcReceipt receipt) {
        return receiptDao.findItems(receipt);
    }

    @Override
    public Integer countReceipt(AcReceiptType type) {
        return receiptDao.count(type);
    }

    @Override
    public Integer countReceipt(AcReceiptType type, String filter) {
        return receiptDao.count(type, filter);
    }

    @Override
    public Integer countReceiptItem(AcReceipt receipt) {
        return receiptDao.countItem(receipt);
    }

    //====================================================================================================
    // PRIVATE METHODS
    //====================================================================================================

    private Map<String, Object> prepareVariables(AcReceipt receipt) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(RECEIPT_ID, receipt.getId());
        map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
        map.put(WorkflowConstants.REFERENCE_NO, receipt.getReferenceNo());
        map.put(WorkflowConstants.REMOVE_DECISION, false);
        map.put(WorkflowConstants.CANCEL_DECISION, false);
        return map;
    }


}
