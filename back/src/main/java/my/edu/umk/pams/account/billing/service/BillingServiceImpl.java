package my.edu.umk.pams.account.billing.service;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.dao.AcAcademicSessionDao;
import my.edu.umk.pams.account.account.dao.AcAccountChargeDao;
import my.edu.umk.pams.account.account.dao.AcAccountDao;
import my.edu.umk.pams.account.account.dao.AcChargeCodeDao;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.service.AccountServiceImpl;
import my.edu.umk.pams.account.billing.chain.ChargeAttachProcessor;
import my.edu.umk.pams.account.billing.chain.ChargeContext;
import my.edu.umk.pams.account.billing.chain.ChargeDetachProcessor;
import my.edu.umk.pams.account.billing.dao.AcInvoiceDao;
import my.edu.umk.pams.account.billing.dao.AcReceiptDao;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
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

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.edu.umk.pams.account.AccountConstants.INVOICE_ID;
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
    private AcInvoiceDao invoiceDao;

    @Autowired
    private ChargeAttachProcessor attachProcessor;

    @Autowired
    private ChargeDetachProcessor detachProcessor;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private WorkflowService workflowService;

    // ==================================================================================================== //
    // INVOICE
    // workflow
    // ==================================================================================================== //
    @Override
    public AcInvoice findInvoiceByTaskId(String taskId) {
        Task task = workflowService.findTask(taskId);
        Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
        return invoiceDao.findById((Long) map.get(AccountConstants.INVOICE_ID));
    }

    @Override
    public List<Task> findAssignedInvoiceTasks(Integer offset, Integer limit) {
        return workflowService.findAssignedTasks(AcInvoice.class.getName(), offset, limit);
    }

    @Override
    public List<Task> findPooledInvoiceTasks(Integer offset, Integer limit) {
        return workflowService.findPooledTasks(AcInvoice.class.getName(), offset, limit);
    }

    @Override
    public void startInvoiceTask(AcInvoice invoice) {
        String refNo = systemService.generateReferenceNo(AccountConstants.INVOICE_REFERENCE_NO);
        invoice.setReferenceNo(refNo);
        LOG.debug("Processing invoice with refNo {}", new Object[]{refNo});

        invoiceDao.saveOrUpdate(invoice, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(invoice);

        workflowService.processWorkflow(invoice, prepareVariables(invoice));
    }

    @Override
    public void cancelInvoice(AcInvoice invoice) {
        invoice.getFlowdata().setState(AcFlowState.CANCELLED);
        invoice.getFlowdata().setCancelledDate(new Timestamp(System.currentTimeMillis()));
        invoice.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
        invoiceDao.update(invoice, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void saveInvoice(AcInvoice invoice) {
        invoiceDao.save(invoice, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateInvoice(AcInvoice invoice) {
        invoiceDao.update(invoice, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addInvoiceItem(AcInvoice invoice, AcInvoiceItem invoiceItem) {
        invoiceDao.addItem(invoice, invoiceItem, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateInvoiceItem(AcInvoice invoice, AcInvoiceItem invoiceItem) {
        invoiceDao.updateItem(invoice, invoiceItem, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void attach(AcInvoice invoice, AcAccountCharge charge) throws Exception {
        Validate.isTrue(null == charge.getInvoice(), "Charge must not already be attached to invoice");
        attachProcessor.process(new ChargeContext(invoice, charge));
    }

    @Override
    public void attach(AcInvoice invoice, AcAccount account, AcAccountChargeType... chargeTypes) throws Exception {
        List<AcAccountCharge> charges = accountChargeDao.find(account, chargeTypes);
        for (AcAccountCharge charge : charges) {
            if (null == charge.getInvoice()) {
                LOG.debug("Attaching charge refno : {} to invoice refno: {}", new Object[]{charge.getReferenceNo(), invoice.getReferenceNo()});
                attachProcessor.process(new ChargeContext(invoice, charge));
            } else {
                LOG.debug("Charge refno : {} already being attached to invoice refno: {}", new Object[]{charge.getReferenceNo(), charge.getInvoice().getReferenceNo()});
            }
        }
    }

    @Override
    public void attach(AcInvoice invoice, AcAccount account) throws Exception {
        List<AcAccountCharge> charges = accountChargeDao.find(account);
        for (AcAccountCharge charge : charges) {
            if (null == charge.getInvoice()) {
                LOG.debug("Attaching charge refno : {} to invoice refno: {}", new Object[]{charge.getReferenceNo(), invoice.getReferenceNo()});
                attachProcessor.process(new ChargeContext(invoice, charge));
            } else {
                LOG.debug("Charge refno : {} already being attached to invoice refno: {}", new Object[]{charge.getReferenceNo(), charge.getInvoice().getReferenceNo()});
            }
        }
    }

    @Override
    public void detach(AcInvoice invoice, AcAccountCharge charge) throws Exception {
        Validate.isTrue(charge.getInvoice().equals(invoice), "Charge must already be attached to invoice");
        detachProcessor.process(new ChargeContext(invoice, charge));
    }

    @Override
    public void detach(AcInvoice invoice, AcAccount account, AcAccountChargeType... chargeTypes) throws Exception {
        List<AcAccountCharge> charges = accountChargeDao.find(account, chargeTypes);
        for (AcAccountCharge charge : charges) {
            if (null != charge.getInvoice()) {
                // TODO: optimize to find INVOICED/ATTACHED
                detachProcessor.process(new ChargeContext(invoice, charge));
            }
        }
    }

    // ==================================================================================================== //
    // INVOICE
    // ==================================================================================================== //

    @Override
    public AcInvoice findInvoiceById(Long id) {
        return invoiceDao.findById(id);
    }

    @Override
    public AcInvoice findInvoiceByReferenceNo(String referenceNo) {
        return invoiceDao.findByReferenceNo(referenceNo);
    }

    @Override
    public List<AcInvoice> findInvoicesBySourceNo(String sourceNo) {
        return invoiceDao.findBySourceNo(sourceNo);
    }


    @Override
    public List<AcInvoice> findInvoices(AcAccount account, Integer offset, Integer limit) {
        return invoiceDao.find(account, offset, limit);
    }

    @Override
    public List<AcInvoice> findUnpaidInvoices(AcAccount account, Integer offset, Integer limit) {
        return invoiceDao.find(false, account, offset, limit);
    }

    @Override
    public List<AcInvoice> findPaidInvoices(AcAccount account, Integer offset, Integer limit) {
        return invoiceDao.find(true, account, offset, limit);
    }


    @Override
    public List<AcInvoiceItem> findInvoiceItems(AcInvoice invoice) {
        return invoiceDao.findItems(invoice);
    }

    @Override
    public List<AcInvoiceItem> findInvoiceItems(AcInvoice invoice, Integer offset, Integer limit) {
        return invoiceDao.findItems(invoice, offset, limit);
    }

//    @Override
//    public List<AcInvoiceTransaction> findInvoiceTransactions(AcInvoice invoice) {
//        return invoiceDao.findTransactions(invoice);
//    }
//
//    @Override
//    public List<AcInvoiceTransaction> findInvoiceTransactions(AcInvoice invoice, Integer offset, Integer limit) {
//        return invoiceDao.findTransactions(invoice, offset, limit);
//    }

    @Override
    public boolean hasInvoice(AcAccount account) {
        return invoiceDao.hasInvoice(account);
    }

    @Override
    public boolean hasUnpaidInvoice(AcAccount account) {
        return invoiceDao.hasInvoice(false, account);
    }

    @Override
    public boolean hasUnpaidInvoice(AcAccount account, Integer days) {
        return invoiceDao.hasInvoice(false, days, account);
    }

    @Override
    public boolean isInvoiceOverdue(AcAccountCharge charge) {
        return !charge.getInvoice().isPaid();
    }

    @Override
    public BigDecimal sumUnpaidInvoice(AcAccountCharge charge) {
        return null; // TODO: ???
    }

    @Override
    public boolean hasBalance(AcAcademicSession academicSession, AcActor actor) {
        return accountDao.hasBalance(academicSession, actor);
    }

    @Override
    public Integer countInvoiceItem(AcInvoice invoice) {
        return invoiceDao.countItem(invoice);
    }

//    @Override
//    public Integer countInvoiceTransaction(AcInvoice invoice) {
//        return invoiceDao.countTransaction(invoice);
//    }

    @Override
    public Integer countInvoice(AcAccount account) {
        return invoiceDao.count(account);
    }

    @Override
    public Integer countPaidInvoice(AcAccount account) {
        return invoiceDao.countPaid(account);
    }

    @Override
    public Integer countUnpaidInvoice(AcAccount account) {
        return invoiceDao.countUnpaid(account);
    }

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

    private Map<String, Object> prepareVariables(AcInvoice invoice) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(INVOICE_ID, invoice.getId());
        map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
        map.put(WorkflowConstants.REFERENCE_NO, invoice.getReferenceNo());
        map.put(WorkflowConstants.REMOVE_DECISION, false);
        map.put(WorkflowConstants.CANCEL_DECISION, false);
        return map;
    }


}
