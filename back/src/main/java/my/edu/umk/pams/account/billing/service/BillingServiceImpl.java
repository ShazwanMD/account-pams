package my.edu.umk.pams.account.billing.service;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.dao.AcAcademicSessionDao;
import my.edu.umk.pams.account.account.dao.AcAccountChargeDao;
import my.edu.umk.pams.account.account.dao.AcAccountDao;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.account.service.AccountServiceImpl;
import my.edu.umk.pams.account.billing.chain.ChargeAttachProcessor;
import my.edu.umk.pams.account.billing.chain.ChargeContext;
import my.edu.umk.pams.account.billing.chain.ChargeDetachProcessor;
import my.edu.umk.pams.account.billing.dao.AcAdvancePaymentDao;
import my.edu.umk.pams.account.billing.dao.AcCreditNoteDao;
import my.edu.umk.pams.account.billing.dao.AcDebitNoteDao;
import my.edu.umk.pams.account.billing.dao.AcInvoiceDao;
import my.edu.umk.pams.account.billing.dao.AcKnockoffDao;
import my.edu.umk.pams.account.billing.dao.AcReceiptDao;
import my.edu.umk.pams.account.billing.dao.AcRefundPaymentDao;
import my.edu.umk.pams.account.billing.dao.AcWaiverFinanceApplicationDao;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.common.dao.AcCohortCodeDao;
import my.edu.umk.pams.account.common.dao.AcTaxCodeDao;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.dao.AcWaiverApplicationDao;
import my.edu.umk.pams.account.financialaid.model.*;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.model.AcConfiguration;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.workflow.service.WorkflowConstants;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.apache.commons.lang.Validate;
import org.hibernate.SessionFactory;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.edu.umk.pams.account.AccountConstants.*;
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
	private AcTaxCodeDao taxCodeDao;

	@Autowired
	private AcAdvancePaymentDao advancePaymentDao;

	@Autowired
	private AcAcademicSessionDao academicSessionDao;

	@Autowired
	private AcWaiverFinanceApplicationDao waiverFinanceApplicationDao;

	@Autowired
	private AcReceiptDao receiptDao;

	@Autowired
	private AcInvoiceDao invoiceDao;

	@Autowired
	private AcDebitNoteDao debitNoteDao;

	@Autowired
	private AcCreditNoteDao creditNoteDao;

	@Autowired
	private AcCohortCodeDao cohortCodeDao;

	@Autowired
	private AcKnockoffDao knockoffDao;

	@Autowired
	private AcRefundPaymentDao refundPaymentDao;

	@Autowired
	private ChargeAttachProcessor attachProcessor;

	@Autowired
	private ChargeDetachProcessor detachProcessor;

	@Autowired
	private AccountService accountService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private WorkflowService workflowService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private BillingService billingService;

	@Autowired
	private FinancialAidService financialAidService;

	// ====================================================================================================
	// INVOICE
	// workflow
	// ====================================================================================================
	@Override
	public AcInvoice findInvoiceByTaskId(String taskId) {
		Task task = workflowService.findTask(taskId);
		Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
		return invoiceDao.findById((Long) map.get(AccountConstants.INVOICE_ID));
	}

	@Override
	public Task findInvoiceTaskByTaskId(String taskId) {
		return workflowService.findTask(taskId);
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
	public String startInvoiceTask(AcInvoice invoice) {
		String refNo = systemService.generateReferenceNo(AccountConstants.INVOICE_REFERENCE_NO);
		invoice.setReferenceNo(refNo);
		LOG.debug("Processing invoice with refNo {}", new Object[] { refNo });

		invoiceDao.saveOrUpdate(invoice, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(invoice);

		workflowService.processWorkflow(invoice, prepareVariables(invoice));
		return refNo;
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
	public void deleteInvoiceItem(AcInvoice invoice, AcInvoiceItem invoiceItem) {
		invoiceDao.deleteItem(invoice, invoiceItem, securityService.getCurrentUser());
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
				LOG.debug("Attaching charge refno : {} to invoice refno: {}",
						new Object[] { charge.getReferenceNo(), invoice.getReferenceNo() });
				attachProcessor.process(new ChargeContext(invoice, charge));
			} else {
				LOG.debug("Charge refno : {} already being attached to invoice refno: {}",
						new Object[] { charge.getReferenceNo(), charge.getInvoice().getReferenceNo() });
			}
		}
	}

	@Override
	public void attach(AcInvoice invoice, AcAccount account) throws Exception {
		List<AcAccountCharge> charges = accountChargeDao.find(account);
		LOG.debug("found {} charges for account {}", charges.size(), account.getCode());
		for (AcAccountCharge charge : charges) {
			if (null == charge.getInvoice()) {
				LOG.debug("Attaching charge refno : {} to invoice refno: {}",
						new Object[] { charge.getReferenceNo(), invoice.getReferenceNo() });
				attachProcessor.process(new ChargeContext(invoice, charge));
			} else {
				LOG.debug("Charge refno : {} already being attached to invoice refno: {}",
						new Object[] { charge.getReferenceNo(), charge.getInvoice().getReferenceNo() });
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

	@Override
	public void itemToReceiptItem(AcInvoice invoice, AcReceipt receipt) {
		List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
		for (AcInvoiceItem invoiceItem : invoiceItems) {
			
			if (invoice.getBalanceAmount().compareTo(receipt.getTotalPayment()) <= 0) {
				AcReceiptItem receiptItem = new AcReceiptItemImpl();
				receiptItem.setChargeCode(invoiceItem.getChargeCode());
				receiptItem.setDueAmount(invoiceItem.getBalanceAmount());
				receiptItem.setDescription(invoiceItem.getChargeCode().getDescription());
				receiptItem.setInvoice(invoice);
				receiptItem.setAdjustedAmount(BigDecimal.ZERO);
				receiptItem.setAppliedAmount(invoiceItem.getBalanceAmount());
				receiptItem.setPrice(BigDecimal.ZERO);
				receiptItem.setReceipt(receipt);
				receiptItem.setTotalAmount(BigDecimal.ZERO);
				receiptItem.setUnit(0);

				billingService.addReceiptItem(receipt, receiptItem);
			}

			else if (invoice.getBalanceAmount().compareTo(receipt.getTotalPayment()) > 0) {
				
				if (receipt.getTotalPayment().compareTo(invoiceItem.getBalanceAmount()) > 0) {
					AcReceiptItem receiptItem = new AcReceiptItemImpl();
					receiptItem.setChargeCode(invoiceItem.getChargeCode());
					receiptItem.setDueAmount(invoiceItem.getBalanceAmount());
					receiptItem.setDescription(invoiceItem.getChargeCode().getDescription());
					receiptItem.setInvoice(invoice);
					receiptItem.setAdjustedAmount(BigDecimal.ZERO);
					receiptItem.setAppliedAmount(invoiceItem.getBalanceAmount());
					receiptItem.setPrice(BigDecimal.ZERO);
					receiptItem.setReceipt(receipt);
					receiptItem.setTotalAmount(BigDecimal.ZERO);
					receiptItem.setUnit(0);
					billingService.addReceiptItem(receipt, receiptItem);
				}

				else if (receipt.getTotalPayment().compareTo(invoiceItem.getBalanceAmount()) < 0) {
					AcReceiptItem receiptItem = new AcReceiptItemImpl();
					receiptItem.setChargeCode(invoiceItem.getChargeCode());
					receiptItem.setDueAmount(invoiceItem.getBalanceAmount());
					receiptItem.setDescription(invoiceItem.getChargeCode().getDescription());
					receiptItem.setInvoice(invoice);
					receiptItem.setAdjustedAmount(BigDecimal.ZERO);
					receiptItem.setAppliedAmount(receipt.getTotalPayment());
					receiptItem.setPrice(BigDecimal.ZERO);
					receiptItem.setReceipt(receipt);
					receiptItem.setTotalAmount(invoiceItem.getBalanceAmount().subtract(receiptItem.getAppliedAmount()));
					receiptItem.setUnit(0);
					billingService.addReceiptItem(receipt, receiptItem);
				
					if (receipt.getTotalPayment().compareTo(BigDecimal.ZERO) <= 0 ) {
						receiptItem.setChargeCode(invoiceItem.getChargeCode());
						receiptItem.setDueAmount(invoiceItem.getBalanceAmount());
						receiptItem.setDescription(invoiceItem.getChargeCode().getDescription());
						receiptItem.setInvoice(invoice);
						receiptItem.setAdjustedAmount(BigDecimal.ZERO);
						receiptItem.setAppliedAmount(BigDecimal.ZERO);
						receiptItem.setPrice(BigDecimal.ZERO);
						receiptItem.setReceipt(receipt);
						receiptItem.setTotalAmount(invoiceItem.getBalanceAmount());
						receiptItem.setUnit(0);
						billingService.addReceiptItem(receipt, receiptItem);
					}
				}
				
				receipt.setTotalPayment(receipt.getTotalPayment().subtract(invoiceItem.getBalanceAmount()));
				LOG.debug("value invoiceItem after looping {}", receipt.getTotalReceived());
			}
			
			}
		
		receipt.setTotalPayment(receipt.getTotalReceived().subtract(receiptDao.sumAppliedAmount(receipt, securityService.getCurrentUser())));

	}
	
	@Override
	public void updateItemToReceipt(AcReceipt receipt) {
		List<AcReceiptItem> receiptItems = billingService.findReceiptItems(receipt);
		for (AcReceiptItem receiptItem : receiptItems) {
			receiptItem.setTotalAmount(receiptItem.getDueAmount().subtract(receiptItem.getAppliedAmount()));
			billingService.updateReceiptItem(receipt, receiptItem);
		}
		
		receipt.setTotalPayment(receiptDao.sumAppliedAmount(receipt, securityService.getCurrentUser()));
		billingService.updateReceipt(receipt);

	}

	// ====================================================================================================
	// INVOICE
	// ====================================================================================================

	@Override
	public AcInvoice findInvoiceById(Long id) {
		return invoiceDao.findById(id);
	}

	@Override
	public AcInvoice findInvoiceByReferenceNo(String referenceNo) {
		return invoiceDao.findByReferenceNo(referenceNo);
	}

	@Override
	public AcInvoiceItem findInvoiceItemById(Long id) {
		return invoiceDao.findItemById(id);
	}

	@Override
	public List<AcInvoice> findInvoicesBySourceNo(String sourceNo) {
		return invoiceDao.findBySourceNo(sourceNo);
	}

	@Override
	public List<AcInvoice> findInvoices(String filter, Integer offset, Integer limit) {
		return invoiceDao.find(filter, offset, limit);
	}

	@Override
	public List<AcInvoice> findInvoices(AcAccount account, Integer offset, Integer limit) {
		return invoiceDao.find(account, offset, limit);
	}

	@Override
	public List<AcInvoice> findInvoices(String term, Integer offset, Integer limit, List<String> columns) {
		return invoiceDao.find(term, offset, limit, columns);
	}

	@Override
	public List<AcInvoice> findInvoicesByFlowState(AcFlowState acFlowState) {
		return invoiceDao.findByFlowState(acFlowState);
	}

	@Override
	public List<AcInvoice> findInvoicesByFlowStates(AcFlowState... flowStates) {
		return invoiceDao.findByFlowStates(flowStates);
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

	// @Override
	// public List<AcInvoiceTransaction> findInvoiceTransactions(AcInvoice
	// invoice) {
	// return invoiceDao.findTransactions(invoice);
	// }
	//
	// @Override
	// public List<AcInvoiceTransaction> findInvoiceTransactions(AcInvoice
	// invoice, Integer offset, Integer limit) {
	// return invoiceDao.findTransactions(invoice, offset, limit);
	// }

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
	public AcInvoice executeInvoice() {
		return null;
	}

	@Override
	public Integer countInvoiceItem(AcInvoice invoice) {
		return invoiceDao.countItem(invoice);
	}

	// @Override
	// public Integer countInvoiceTransaction(AcInvoice invoice) {
	// return invoiceDao.countTransaction(invoice);
	// }

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

	// every midnite: "0 0 12 * * *"
	// every 30 seconds: "0/20 * * * * *"
	// check AC_CNFG.sql in /data
	@Scheduled(cron = "0 0 1 * * *")
	public void executeScheduler() {
		LOG.debug("executing scheduler");
		LocalDate now = new LocalDate(System.currentTimeMillis());
		AcConfiguration lastEnrollmentDateStr = systemService.findConfigurationByKey(LAST_ENROLLMENT_DATE);
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		// generate when date past last enrollment date
		try {
			Date lastEnrollment = formatter.parse(lastEnrollmentDateStr.getValue());
			if (now.isAfter(new LocalDate(lastEnrollment))) {
				// todo(hajar): generate invoice for student
				// todo(hajar): for this semester
				LOG.debug("session " + academicSessionDao.findCurrentSession());

				List<AcCohortCode> cohortCode = cohortCodeDao.find();

				for (AcCohortCode cohortCodes : cohortCode) {

					AcSettlement settlement = new AcSettlementImpl();
					settlement.setDescription(cohortCodes.getCode());
					settlement.setSession(academicSessionDao.findCurrentSession());

					financialAidService.initSettlementByCohortCode(settlement, cohortCodes);

					List<AcStudent> students = identityService.findStudentByCohortCode(cohortCodes);
					for (AcStudent student : students) {
						AcSettlementItem item = new AcSettlementItemImpl();
						item.setSettlement(settlement);
						item.setAccount(accountDao.findByActor(student));
						item.setStatus(AcSettlementStatus.NEW);
						financialAidService.addSettlementItem(settlement, item);
					}

					AcInvoice invoice = new AcInvoiceImpl();
					invoice.setDescription(settlement.getId() + " " + settlement.getSession());
					invoice.setSession(academicSessionDao.findCurrentSession());

					financialAidService.executeSettlement(settlement);
				}

			}
		} catch (ParseException e) {
			LOG.error("error parsing");
		}
	}

	// posting to student account
	@Override
	public void post(AcInvoice invoice) {
		/*
		 * List<AcInvoiceItem> items = findInvoiceItems(invoice); for
		 * (AcInvoiceItem item : items) {
		 */
		AcAccountTransaction tx = new AcAccountTransactionImpl();
		tx.setSession(invoice.getSession());
		// tx.setChargeCode(item.getChargeCode());
		tx.setDescription(invoice.getDescription());
		tx.setPostedDate(new Date());
		tx.setSourceNo(invoice.getReferenceNo());
		tx.setTransactionCode(AcAccountTransactionCode.INVOICE);
		tx.setAccount(invoice.getAccount());
		tx.setAmount(invoice.getBalanceAmount());
		accountService.addAccountTransaction(invoice.getAccount(), tx);
		// }
	}

	// ====================================================================================================
	// DEBIT NOTE
	// ====================================================================================================

	@Override
	public AcDebitNote findDebitNoteByTaskId(String taskId) {
		Task task = workflowService.findTask(taskId);
		Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
		return debitNoteDao.findById((Long) map.get(AccountConstants.DEBIT_NOTE_ID));
	}

	@Override
	public Task findDebitNoteTaskByTaskId(String taskId) {
		return workflowService.findTask(taskId);
	}

	@Override
	public List<Task> findAssignedDebitNoteTasks(Integer offset, Integer limit) {
		return workflowService.findAssignedTasks(AcDebitNote.class.getName(), offset, limit);
	}

	@Override
	public List<Task> findPooledDebitNoteTasks(Integer offset, Integer limit) {
		return workflowService.findPooledTasks(AcDebitNote.class.getName(), offset, limit);
	}

	@Override
	public String startDebitNoteTask(AcDebitNote debitNote) {
		String refNo = systemService.generateReferenceNo(AccountConstants.DEBIT_NOTE_REFERENCE_NO);
		debitNote.setReferenceNo(refNo);
		LOG.debug("Processing debitNote with refNo {}", new Object[] { refNo });

		debitNoteDao.saveOrUpdate(debitNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(debitNote);

		workflowService.processWorkflow(debitNote, prepareVariables(debitNote));
		return refNo;
	}

	@Override
	public void cancelDebitNote(AcDebitNote debitNote) {
		debitNote.getFlowdata().setState(AcFlowState.CANCELLED);
		debitNote.getFlowdata().setCancelledDate(new Timestamp(System.currentTimeMillis()));
		debitNote.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
		debitNoteDao.update(debitNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void saveDebitNote(AcDebitNote debitNote) {
		debitNoteDao.save(debitNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateDebitNote(AcDebitNote debitNote) {
		debitNoteDao.update(debitNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addDebitNoteItem(AcDebitNote debitNote, AcDebitNoteItem debitNoteItem) {
		debitNoteDao.addItem(debitNote, debitNoteItem, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateDebitNoteItem(AcDebitNote debitNote, AcDebitNoteItem debitNoteItem) {
		debitNoteDao.updateItem(debitNote, debitNoteItem, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteDebitNoteItem(AcDebitNote debitNote, AcDebitNoteItem debitNoteItem) {
		debitNoteDao.deleteItem(debitNote, debitNoteItem, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// finder

	@Override
	public AcDebitNote findDebitNoteById(Long id) {
		return debitNoteDao.findById(id);
	}

	@Override
	public AcDebitNoteItem findDebitNoteItemById(Long id) {
		return debitNoteDao.findItemById(id);
	}

	@Override
	public AcDebitNote findDebitNoteByReferenceNo(String referenceNo) {
		return debitNoteDao.findByReferenceNo(referenceNo);
	}

	@Override
	public List<AcDebitNote> findDebitNotes(AcInvoice invoice) {
		return debitNoteDao.find(invoice);
	}

	@Override
	public List<AcDebitNote> findDebitNotes(AcInvoice invoice, String filter, Integer offset, Integer limit) {
		return debitNoteDao.find(invoice);
	}

	@Override
	public List<AcDebitNote> findDebitNotesByFlowState(AcFlowState flowState) {
		return debitNoteDao.findByFlowState(flowState);
	}
	
	@Override
	public List<AcDebitNote> findDebitNotesByFlowStates(AcFlowState... flowStates ) {
		return debitNoteDao.findByFlowStates(flowStates);
	}

	@Override
	public List<AcDebitNoteItem> findDebitNoteItems(AcDebitNote debitNote) {
		return debitNoteDao.findItems(debitNote);
	}

	@Override
	public boolean hasDebitNote(AcInvoice invoice) {
		return debitNoteDao.hasDebitNote(invoice);
	}

	@Override
	public void post(AcDebitNote debitNote) {
		AcAccountTransaction tx = new AcAccountTransactionImpl();
		tx.setSession(debitNote.getInvoice().getSession());
		tx.setChargeCode(debitNote.getChargeCode());
		tx.setDescription(debitNote.getDescription());
		tx.setPostedDate(new Date());
		tx.setSourceNo(debitNote.getReferenceNo());
		tx.setTransactionCode(AcAccountTransactionCode.DEBIT_NOTE);
		tx.setAccount(debitNote.getInvoice().getAccount());
		tx.setAmount(debitNote.getTotalAmount());
		accountService.addAccountTransaction(debitNote.getInvoice().getAccount(), tx);
	}

	@Override
	public Integer countDebitNote(AcInvoice invoice) {
		return debitNoteDao.count(invoice);
	}
	
	@Override
	public List<AcDebitNote> findUnpaidDebitNotes(AcAccount account, Integer offset, Integer limit) {
		return debitNoteDao.find(false, account, offset, limit);
	}

	// ====================================================================================================
	// CREDIT NOTE
	// ====================================================================================================

	@Override
	public AcCreditNote findCreditNoteByTaskId(String taskId) {
		Task task = workflowService.findTask(taskId);
		Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
		return creditNoteDao.findById((Long) map.get(AccountConstants.CREDIT_NOTE_ID));
	}

	@Override
	public Task findCreditNoteTaskByTaskId(String taskId) {
		return workflowService.findTask(taskId);
	}

	@Override
	public List<Task> findAssignedCreditNoteTasks(Integer offset, Integer limit) {
		return workflowService.findAssignedTasks(AcCreditNote.class.getName(), offset, limit);
	}

	@Override
	public List<Task> findPooledCreditNoteTasks(Integer offset, Integer limit) {
		return workflowService.findPooledTasks(AcCreditNote.class.getName(), offset, limit);
	}

	@Override
	public String startCreditNoteTask(AcCreditNote creditNote) {
		String refNo = systemService.generateReferenceNo(AccountConstants.CREDIT_NOTE_REFERENCE_NO);
		creditNote.setReferenceNo(refNo);
		LOG.debug("Processing creditNote with refNo {}", new Object[] { refNo });

		creditNoteDao.saveOrUpdate(creditNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(creditNote);

		workflowService.processWorkflow(creditNote, prepareVariables(creditNote));
		return refNo;
	}

	@Override
	public void cancelCreditNote(AcCreditNote creditNote) {
		creditNote.getFlowdata().setState(AcFlowState.CANCELLED);
		creditNote.getFlowdata().setCancelledDate(new Timestamp(System.currentTimeMillis()));
		creditNote.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
		creditNoteDao.update(creditNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void saveCreditNote(AcCreditNote creditNote) {
		creditNoteDao.save(creditNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateCreditNote(AcCreditNote creditNote) {
		creditNoteDao.update(creditNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addCreditNoteItem(AcCreditNote creditNote, AcCreditNoteItem creditNoteItem) {
		creditNoteDao.addItem(creditNote, creditNoteItem, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateCreditNoteItem(AcCreditNote creditNote, AcCreditNoteItem creditNoteItem) {
		creditNoteDao.updateItem(creditNote, creditNoteItem, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteCreditNoteItem(AcCreditNote creditNote, AcCreditNoteItem creditNoteItem) {
		creditNoteDao.deleteItem(creditNote, creditNoteItem, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// finder

	@Override
	public AcCreditNote findCreditNoteById(Long id) {
		return creditNoteDao.findById(id);
	}

	@Override
	public AcCreditNoteItem findCreditNoteItemById(Long id) {
		return creditNoteDao.findItemById(id);
	}

	@Override
	public AcCreditNote findCreditNoteByReferenceNo(String referenceNo) {
		return creditNoteDao.findByReferenceNo(referenceNo);
	}

	@Override
	public List<AcCreditNote> findCreditNotes(AcInvoice invoice) {
		return creditNoteDao.find(invoice);
	}

	@Override
	public List<AcCreditNote> findCreditNotesByFlowState(AcFlowState flowState) {
		return creditNoteDao.findByFlowState(flowState);
	}
	
	@Override
	public List<AcCreditNote> findCreditNotesByFlowStates(AcFlowState... flowStates ) {
		return creditNoteDao.findByFlowStates(flowStates);
	}

	@Override
	public List<AcCreditNoteItem> findCreditNoteItems(AcCreditNote creditNote) {
		return creditNoteDao.findItems(creditNote);
	}

	@Override
	public boolean hasCreditNote(AcInvoice invoice) {
		return creditNoteDao.hasCreditNote(invoice);
	}

	@Override
	public void post(AcCreditNote creditNote) {
		AcAccountTransaction tx = new AcAccountTransactionImpl();
		tx.setSession(creditNote.getInvoice().getSession());
		tx.setChargeCode(creditNote.getChargeCode());
		tx.setDescription(creditNote.getDescription());
		tx.setPostedDate(new Date());
		tx.setSourceNo(creditNote.getReferenceNo());
		tx.setTransactionCode(AcAccountTransactionCode.CREDIT_NOTE);
		tx.setAccount(creditNote.getInvoice().getAccount());
		tx.setAmount(creditNote.getTotalAmount().negate());
		accountService.addAccountTransaction(creditNote.getInvoice().getAccount(), tx);
	}

	@Override
	public Integer countCreditNote(AcInvoice invoice) {
		return creditNoteDao.count(invoice);
	}

	// ====================================================================================================
	// RECEIPT
	// ====================================================================================================

	@Override
	public AcReceipt findReceiptByTaskId(String taskId) {
		Task task = workflowService.findTask(taskId);
		Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
		return receiptDao.findById((Long) map.get(RECEIPT_ID));
	}

	@Override
	public Task findReceiptTaskByTaskId(String taskId) {
		return workflowService.findTask(taskId);
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
	public String startReceiptTask(AcReceipt receipt) {
		String refNo = systemService.generateReferenceNo(AccountConstants.RECEIPT_REFERENCE_NO);
		receipt.setReferenceNo(refNo);
		LOG.debug("Processing receipt with refNo {}", new Object[] { refNo });

		receiptDao.saveOrUpdate(receipt, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(receipt);

		workflowService.processWorkflow(receipt, prepareVariables(receipt));
		return refNo;
	}

	@Override
	public void updateReceipt(AcReceipt receipt) {
		receiptDao.update(receipt, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void cancelReceipt(AcReceipt receipt) {
		Validate.notNull(receipt, "Receipt cannot be null");
		Validate.isTrue(DRAFTED.equals(receipt.getFlowdata().getState()),
				"Receipt can only be cancelled in DRAFTED state");
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
		
		item.setTotalAmount(item.getDueAmount().subtract(item.getAppliedAmount()));
		receiptDao.updateItem(receipt, item, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		
		receipt.setTotalPayment(receipt.getTotalReceived().subtract(receiptDao.sumAppliedAmount(receipt, securityService.getCurrentUser())));
		receiptDao.update(receipt, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteReceiptItem(AcReceipt receipt, AcReceiptItem item) {
		receiptDao.deleteItem(receipt, item, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void addReceiptInvoice(AcReceipt receipt, AcInvoice invoice) {
		receiptDao.addReceiptInvoice(receipt, invoice, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void deleteReceiptInvoice(AcReceipt receipt, AcInvoice invoice) {
		receiptDao.delete(receipt, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		
		List<AcReceiptItem> receiptItems = billingService.findReceiptItems(receipt);
		for(AcReceiptItem receiptItem: receiptItems) {
			billingService.deleteReceiptItem(receipt, receiptItem);
		}
	}
	
	@Override
	public void addReceiptCharge(AcReceipt receipt, AcAccountCharge accountCharge) {
		receiptDao.addReceiptCharge(receipt, accountCharge, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void addReceiptDebitNote(AcReceipt receipt, AcDebitNote debitNote) {
		receiptDao.addReceiptDebitNote(receipt, debitNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// //
	// RECEIPT
	// ====================================================================================================
	// //

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
	public AcReceiptAccountChargeItem findReceiptChargeItemById(Long id) {
		return receiptDao.findChargeItemById(id);
	}

	@Override
	public AcReceiptItem findReceiptItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcReceipt receipt) {
		return receiptDao.findReceiptItemByChargeCode(chargeCode, invoice, receipt);
	}
	
	@Override
	public AcReceiptItem findReceiptItemByCharge(AcAccountCharge charge, AcReceipt receipt) {
		return receiptDao.findReceiptItemByCharge(charge, receipt);
	}
	
	@Override
	public AcReceiptItem findReceiptItemByDebitNote(AcDebitNote debitNote, AcReceipt receipt) {
		return receiptDao.findReceiptItemByDebitNote(debitNote, receipt);
	}
	
	@Override
	public AcReceiptItem findReceiptItem(AcInvoice invoice, AcReceipt receipt) {
		return receiptDao.findReceiptItem(invoice, receipt);
	}
	
	@Override
	public AcReceiptInvoice findReceiptInvoice(AcInvoice invoice, AcReceipt receipt) {
		return receiptDao.findReceiptInvoice(invoice, receipt);
	}

	@Override
	public List<AcReceipt> findReceipts(String filter, Integer offset, Integer limit) {
		return receiptDao.find(filter, offset, limit);
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
	public List<AcReceipt> findReceiptsByFlowState(AcFlowState flowState) {
		return receiptDao.findByFlowState(flowState);
	}

	@Override
	public List<AcReceipt> findReceiptsByFlowState(AcFlowState... flowStates) {
		return receiptDao.findByFlowStates(flowStates);
	}
	
	@Override
	public List<AcReceiptAccountChargeItem> findReceiptChargeItems(AcReceipt receipt) {
		return receiptDao.findChargeItems(receipt);
	}
	
	@Override
	public List<AcReceiptAccountChargeItem> findReceiptChargeItems(AcReceipt receipt, AcAccountCharge charge) {
		return receiptDao.findChargeItems(receipt, charge);
	}

	@Override
	public List<AcReceiptItem> findReceiptItems(AcReceipt receipt) {
		return receiptDao.findItems(receipt);
	}
	
	@Override
	public List<AcReceiptItem> findReceiptItems(AcReceipt receipt, AcInvoice invoice) {
		return receiptDao.findItems(receipt, invoice);
	}

	@Override
	public List<AcReceiptInvoice> findReceipts(AcReceipt receipt) {
		return receiptDao.find(receipt);
	}
	
	@Override
	public List<AcReceiptAccountCharge> findReceiptsAccountCharge(AcReceipt receipt) {
		return receiptDao.findReceiptAccountCharge(receipt);
	}
	
	@Override
	public List<AcReceiptDebitNote> findReceiptsDebitNote(AcReceipt receipt) {
		return receiptDao.findReceiptDebitNote(receipt);
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
	
	@Override
	public Integer countReceiptChargeItem(AcReceipt receipt) {
		return receiptDao.countItem(receipt);
	}

	@Override
	public void post(AcReceipt receipt) {

		// List<AcReceiptItem> items = findReceiptItems(receipt);
		// for (AcReceiptItem item : items) {
		AcAccountTransaction tx = new AcAccountTransactionImpl();
		tx.setSession(accountService.findCurrentAcademicSession());
		// tx.setChargeCode(item.getChargeCode());
		tx.setDescription(receipt.getDescription());
		tx.setPostedDate(new Date());
		tx.setSourceNo(receipt.getReferenceNo());
		tx.setTransactionCode(AcAccountTransactionCode.RECEIPT);
		tx.setAccount(receipt.getAccount());
		tx.setAmount(receipt.getTotalReceived().negate());
		accountService.addAccountTransaction(receipt.getAccount(), tx);
		// }
	}

	@Override
	public void calculateChargeInvoice(AcReceipt receipt, AcAccount account) {

		List<AcInvoice> invoices = invoiceDao.find(account);
		for (AcInvoice invoice : invoices) {

			List<AcInvoiceItem> invoiceItems = invoiceDao.findItems(invoice);

			for (AcInvoiceItem invoiceItem : invoiceItems) {
				AcReceiptItem item = new AcReceiptItemImpl();
				item.setAppliedAmount(BigDecimal.ZERO);
				item.setChargeCode(invoiceItem.getChargeCode());
				item.setInvoice(invoice);
				item.setReceipt(receipt);
				item.setTotalAmount(invoiceItem.getAmount());
				addReceiptItem(receipt, item);
			}
		}

		sessionFactory.getCurrentSession().flush();

	}

	@Override
	public void calculateCharge(AcReceipt receipt, AcAccount account) {
		List<AcAccountCharge> charges = accountChargeDao.find(account);

		for (AcAccountCharge charge : charges) {
			AcReceiptItem item = new AcReceiptItemImpl();
			item.setAppliedAmount(BigDecimal.ZERO);
			// item.setChargeCode(charge.get);
			// item.setInvoice(invoice);
			item.setReceipt(receipt);
			item.setTotalAmount(charge.getAmount());
			addReceiptItem(receipt, item);
		}

		sessionFactory.getCurrentSession().flush();

	}

	@Override
	public BigDecimal sumAdvancePayment(AcReceipt receipt) {
		return receipt.getTotalReceived()
				.subtract(receiptDao.sumAppliedAmount(receipt, securityService.getCurrentUser()));
	}

	@Override
	public BigDecimal sumAppliedAmount(AcInvoice invoice, AcReceipt receipt) {
		return receiptDao.sumAmount(invoice, receipt, securityService.getCurrentUser());
	}
	
	@Override
	public boolean hasReceiptItem(AcInvoice invoice, AcReceipt receipt) {
		return receiptDao.hasReceiptItem(receipt, invoice);
	}

	// ====================================================================================================
	// PRIVATE METHODS
	// ====================================================================================================

	private Map<String, Object> prepareVariables(AcReceipt receipt) {
		LOG.debug("receiptid: " + receipt.getId());
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

	private Map<String, Object> prepareVariables(AcDebitNote debitNote) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(DEBIT_NOTE_ID, debitNote.getId());
		map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
		map.put(WorkflowConstants.REFERENCE_NO, debitNote.getReferenceNo());
		map.put(WorkflowConstants.REMOVE_DECISION, false);
		map.put(WorkflowConstants.CANCEL_DECISION, false);
		return map;
	}

	private Map<String, Object> prepareVariables(AcCreditNote creditNote) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(CREDIT_NOTE_ID, creditNote.getId());
		map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
		map.put(WorkflowConstants.REFERENCE_NO, creditNote.getReferenceNo());
		map.put(WorkflowConstants.REMOVE_DECISION, false);
		map.put(WorkflowConstants.CANCEL_DECISION, false);
		return map;
	}

	private Map<String, Object> prepareVariables(AcRefundPayment refundPayment) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(REFUND_ID, refundPayment.getId());
		map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
		map.put(WorkflowConstants.REFERENCE_NO, refundPayment.getReferenceNo());
		map.put(WorkflowConstants.REMOVE_DECISION, false);
		map.put(WorkflowConstants.CANCEL_DECISION, false);
		return map;
	}

	private Map<String, Object> prepareVariables(AcKnockoff knockoff) {
		LOG.debug("knockoffid: " + knockoff.getId());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(KNOCKOFF_ID, knockoff.getId());
		map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
		map.put(WorkflowConstants.REFERENCE_NO, knockoff.getReferenceNo());
		map.put(WorkflowConstants.REMOVE_DECISION, false);
		map.put(WorkflowConstants.CANCEL_DECISION, false);

		LOG.debug("workflow variables {}", map);
		return map;
	}

	private Map<String, Object> prepareVariables(AcWaiverFinanceApplication application) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put(WAIVER_FINANCE_APPLICATION_ID, application.getId());
		map.put(WorkflowConstants.USER_CREATOR, securityService.getCurrentUser().getName());
		map.put(WorkflowConstants.REFERENCE_NO, application.getReferenceNo());
		map.put(WorkflowConstants.REMOVE_DECISION, false);
		map.put(WorkflowConstants.CANCEL_DECISION, false);
		return map;
	}
	// ====================================================================================================
	// //
	// ADVANCE PAYMENT
	// ====================================================================================================
	// //

	@Override
	public AcAdvancePayment findAdvancePaymentById(Long id) {
		return advancePaymentDao.findById(id);
	}

	@Override
	public AcAdvancePayment findAdvancePaymentByReferenceNo(String referenceNo) {
		return advancePaymentDao.findByReferenceNo(referenceNo);
	}

	@Override
	public void addAdvancePayment(AcAdvancePayment payment, AcUser user) {
		advancePaymentDao.save(payment, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateAdvancePayment(AcAdvancePayment payment) {
		advancePaymentDao.update(payment, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public List<AcAdvancePayment> findAdvancePayments(boolean status, String filter, Integer offset, Integer limit) {
		return advancePaymentDao.find(false, filter, offset, limit);
	}

	@Override
	public List<AcAdvancePayment> findUnpaidAdvancePayments(AcAccount account, Integer offset, Integer limit) {
		return advancePaymentDao.find(false, account, offset, limit);
	}

	// ====================================================================================================
	// //
	// KNOCKOFF
	// ====================================================================================================
	// //

	@Override
	public AcKnockoff findKnockoffById(Long id) {
		return knockoffDao.findById(id);
	}

	@Override
	public AcKnockoff findKnockoffByReferenceNo(String referenceNo) {
		return knockoffDao.findByReferenceNo(referenceNo);
	}
	
	@Override
	public AcKnockoffItem findKnockoffItemById(Long id) {
		return knockoffDao.findItemById(id);
	}
	
	@Override
	public AcKnockoffItem findKnockoffItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcKnockoff knockoff) {
		return knockoffDao.findKnockoffItemByChargeCode(chargeCode, invoice, knockoff);
	}
	
	@Override
	public AcKnockoffItem findKnockoffItemByCharge(AcAccountCharge charge, AcKnockoff knockoff) {
		return knockoffDao.findKnockoffItemByChare(charge, knockoff);
	}
	
	@Override
	public AcKnockoffItem findKnockoffReceiptItemByDebitNote(AcDebitNote debitNote, AcKnockoff knockoff) {
		return knockoffDao.findKnockoffItemByDebitNote(debitNote, knockoff);
	}
	
	@Override
	public List<AcKnockoffItem> findInvoiceKnockoffItem(AcInvoice invoice, AcKnockoff knockoff) {
		return knockoffDao.findInvoiceKnockoffItem(invoice, knockoff);
	}

	@Override
	public List<AcKnockoff> findKnockoffs(String filter, Integer offset, Integer limit) {
		return knockoffDao.find(filter, offset, limit);
	}

	@Override
	public List<AcKnockoff> findKnockoffsByFlowState(AcFlowState acFlowState) {
		return knockoffDao.findByFlowState(acFlowState);
	}

	@Override
	public List<AcKnockoff> findKnockoffsByFlowStates(AcFlowState... flowStates) {
		return knockoffDao.findByFlowStates(flowStates);
	}
	
	@Override
	public List<AcKnockoffItem> findAcKnockoffs(AcKnockoff knockoff) {
		return knockoffDao.findItems(knockoff);
	}
	
	@Override
	public List<AcKnockoffItem> findAcKnockoffs(AcKnockoff knockoff, AcInvoice invoice) {
		return knockoffDao.findItems(knockoff, invoice);
	}
	
	@Override
	public List<AcKnockoffItem> findAcKnockoffs(AcKnockoff knockoff, AcDebitNote debitNote) {
		return knockoffDao.findItems(knockoff, debitNote);
	}
	
	@Override
	public List<AcKnockoffInvoice> findKnockoffs(AcKnockoff knockoff) {
		return knockoffDao.find(knockoff);
	}
	
	@Override
	public List<AcKnockoffAccountCharge> findKnockoffAccountCharges(AcKnockoff knockoff) {
		return knockoffDao.findAccountCharge(knockoff);
	}
	
	@Override
	public List<AcKnockoffDebitNote> findKnockoffDebitNotes(AcKnockoff knockoff) {
		return knockoffDao.findAccountDebitNote(knockoff);
	}

	@Override
	public boolean hasKnockoff(AcKnockoff knockoff) {
		return knockoffDao.hasKnockoff(knockoff);
	}
	
	@Override
	public boolean hasKnockoff(AcKnockoff knockoff, AcInvoice invoice) {
		return knockoffDao.hasKnockoff(knockoff, invoice);
	}

	@Override
	public void addKnockoff(AcKnockoff knockoff) {
		knockoffDao.save(knockoff, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateKnockoff(AcKnockoff knockoff) {
		knockoffDao.update(knockoff, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeKnockoff(AcKnockoff knockoff, AcUser user) {
		knockoffDao.remove(knockoff, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void itemToKnockoffItem(AcInvoice invoice, AcKnockoff knockoff) {
		List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
		for (AcInvoiceItem invoiceItem : invoiceItems) {
			
			if (invoice.getBalanceAmount().compareTo(knockoff.getTotalAmount()) <= 0) {
				AcKnockoffItem knockoffItem = new AcKnockoffItemImpl();
				knockoffItem.setChargeCode(invoiceItem.getChargeCode());
				knockoffItem.setDescription(invoiceItem.getChargeCode().getDescription());
				knockoffItem.setInvoice(invoice);
				knockoffItem.setDueAmount(invoiceItem.getBalanceAmount());
				knockoffItem.setAppliedAmount(invoiceItem.getBalanceAmount());
				knockoffItem.setKnockoff(knockoff);
				knockoffItem.setTotalAmount(BigDecimal.ZERO);
				billingService.addKnockoffItem(knockoff, knockoffItem);
			}

			else if (invoice.getBalanceAmount().compareTo(knockoff.getTotalAmount()) > 0) {
				
				if (knockoff.getTotalAmount().compareTo(invoiceItem.getBalanceAmount()) > 0) {
					AcKnockoffItem knockoffItem = new AcKnockoffItemImpl();
					knockoffItem.setChargeCode(invoiceItem.getChargeCode());
					knockoffItem.setDescription(invoiceItem.getChargeCode().getDescription());
					knockoffItem.setInvoice(invoice);
					knockoffItem.setDueAmount(invoiceItem.getBalanceAmount());
					knockoffItem.setAppliedAmount(invoiceItem.getBalanceAmount());
					knockoffItem.setKnockoff(knockoff);
					knockoffItem.setTotalAmount(BigDecimal.ZERO);
					billingService.addKnockoffItem(knockoff, knockoffItem);
				}

				else if (knockoff.getTotalAmount().compareTo(invoiceItem.getBalanceAmount()) < 0) {
					AcKnockoffItem knockoffItem = new AcKnockoffItemImpl();
					knockoffItem.setChargeCode(invoiceItem.getChargeCode());
					knockoffItem.setDescription(invoiceItem.getChargeCode().getDescription());
					knockoffItem.setInvoice(invoice);
					knockoffItem.setDueAmount(invoiceItem.getBalanceAmount());
					knockoffItem.setAppliedAmount(knockoff.getTotalAmount());
					knockoffItem.setKnockoff(knockoff);
					knockoffItem.setTotalAmount(invoiceItem.getBalanceAmount().subtract(knockoffItem.getAppliedAmount()));
					billingService.addKnockoffItem(knockoff, knockoffItem);
				
					if (knockoff.getTotalAmount().compareTo(BigDecimal.ZERO) <= 0 ) {
						knockoffItem.setChargeCode(invoiceItem.getChargeCode());
						knockoffItem.setDescription(invoiceItem.getChargeCode().getDescription());
						knockoffItem.setInvoice(invoice);
						knockoffItem.setDueAmount(invoiceItem.getBalanceAmount());
						knockoffItem.setAppliedAmount(BigDecimal.ZERO);
						knockoffItem.setKnockoff(knockoff);
						knockoffItem.setTotalAmount(invoiceItem.getBalanceAmount());
						billingService.addKnockoffItem(knockoff, knockoffItem);
					}
				}
				
				knockoff.setTotalAmount(knockoff.getTotalAmount().subtract(invoiceItem.getBalanceAmount()));
			}
			
		}
		
		knockoff.setTotalAmount(knockoff.getAmount().subtract(knockoffDao.sumAppliedAmount(knockoff, securityService.getCurrentUser())));

	}
	
	@Override
	public void updateitemToKnockoff(AcKnockoff knockoff) {
		List<AcKnockoffItem> knockoffItems = billingService.findAcKnockoffs(knockoff);
		for (AcKnockoffItem knockoffItem : knockoffItems) {
			knockoffItem.setTotalAmount(knockoffItem.getDueAmount().subtract(knockoffItem.getAppliedAmount()));
			billingService.updateKnockoffItem(knockoff, knockoffItem);
		}
		
		knockoff.setTotalAmount(knockoffDao.sumAppliedAmount(knockoff, securityService.getCurrentUser()));
		billingService.updateKnockoff(knockoff);

	}
	
	@Override
	public void addKnockoffInvoice(AcKnockoff knockoff, AcInvoice invoice) {
		knockoffDao.addKnockoffInvoice(knockoff, invoice, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void addKnockoffAccountCharge(AcKnockoff knockoff, AcAccountCharge accountCharge) {
		knockoffDao.addKnockoffAccountCharge(knockoff, accountCharge, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void addKnockoffDebitNote(AcKnockoff knockoff, AcDebitNote debitNote) {
		knockoffDao.addKnockoffDebitNote(knockoff, debitNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void addKnockoffItem(AcKnockoff knockoff, AcKnockoffItem item) {
		knockoffDao.addItem(knockoff, item, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void updateKnockoffItem(AcKnockoff knockoff, AcKnockoffItem item) {
/*		knockoffDao.updateItem(knockoff, item, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();*/
		
		item.setTotalAmount(item.getDueAmount().subtract(item.getAppliedAmount()));
		knockoffDao.updateItem(knockoff, item, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		
		knockoff.setTotalAmount(knockoff.getBalanceAmount().subtract(knockoffDao.sumAppliedAmount(knockoff, securityService.getCurrentUser())));
		knockoffDao.update(knockoff, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void post(AcKnockoff knockoff)  {
		AcAccountTransaction tx = new AcAccountTransactionImpl();
		tx.setSession(knockoff.getPayments().getSession());
		//tx.setChargeCode(creditNote.getChargeCode());
		tx.setDescription(knockoff.getDescription());
		tx.setPostedDate(new Date());
		tx.setSourceNo(knockoff.getReferenceNo());
		tx.setTransactionCode(AcAccountTransactionCode.KNOCKOFF);
		tx.setAccount(knockoff.getPayments().getAccount());
		tx.setAmount(knockoff.getBalanceAmount().negate());
		accountService.addAccountTransaction(knockoff.getPayments().getAccount(), tx);
	}
	
	@Override
	public BigDecimal sumAppliedAmount(AcInvoice invoice, AcKnockoff knockoff) {
		return knockoffDao.sumAmount(invoice, knockoff, securityService.getCurrentUser());
	}

	// TASK

	@Override
	public AcKnockoff findKnockoffByTaskId(String taskId) {
		Task task = workflowService.findTask(taskId);
		Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
		return knockoffDao.findById((Long) map.get(AccountConstants.KNOCKOFF_ID));
	}

	@Override
	public Task findKnockoffTaskByTaskId(String taskId) {
		return workflowService.findTask(taskId);
	}

	@Override
	public List<Task> findAssignedKnockoffTasks(Integer offset, Integer limit) {
		return workflowService.findAssignedTasks(AcKnockoff.class.getName(), offset, limit);
	}

	@Override
	public List<Task> findPooledKnockoffTasks(Integer offset, Integer limit) {
		return workflowService.findPooledTasks(AcKnockoff.class.getName(), offset, limit);
	}

	@Override
	public String startKnockoffTask(AcKnockoff knockoff) {
		String refNo = systemService.generateReferenceNo(AccountConstants.KNOCKOFF_REFRENCE_NO);
		knockoff.setReferenceNo(refNo);
		LOG.debug("Processing knockoff with refNo {}", new Object[] { refNo });

		knockoffDao.saveOrUpdate(knockoff, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(knockoff);

		LOG.debug("Knockoff Description {}", knockoff.getDescription());

		workflowService.processWorkflow(knockoff, prepareVariables(knockoff));
		return refNo;
	}

	// ====================================================================================================
	// //
	// REFUND PAYMENT
	// ====================================================================================================
	// //

	@Override
	public List<AcRefundPayment> findRefundPayments(String filter, Integer offset, Integer limit) {
		return refundPaymentDao.find(filter, offset, limit);
	}

	@Override
	public List<AcRefundPayment> findRefundPaymentsByFlowState(AcFlowState acFlowState) {
		return refundPaymentDao.findByFlowState(acFlowState);
	}

	@Override
	public List<AcRefundPayment> findRefundPaymentsByFlowStates(AcFlowState... flowStates) {
		return refundPaymentDao.findByFlowStates(flowStates);
	}

	@Override
	public AcRefundPayment findRefundPaymentById(Long id) {
		return refundPaymentDao.findById(id);
	}

	@Override
	public AcRefundPayment findRefundPaymentByReferenceNo(String referenceNo) {
		return refundPaymentDao.findByReferenceNo(referenceNo);
	}

	@Override
	public boolean hasRefundPayment(AcRefundPayment refund) {
		return refundPaymentDao.hasRefundPayment(refund);
	}

	@Override
	public void addRefundPayment(AcRefundPayment refund, AcUser user) {
		refundPaymentDao.save(refund, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateRefundPayment(AcRefundPayment refund) {
		refundPaymentDao.update(refund, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void removeRefundPayment(AcRefundPayment refund, AcUser user) {
		refundPaymentDao.remove(refund, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void post(AcRefundPayment refund) {
		AcAccountTransaction tx = new AcAccountTransactionImpl();
		tx.setSession(refund.getPayments().getSession());
		tx.setDescription(refund.getDescription());
		tx.setPostedDate(new Date());
		tx.setSourceNo(refund.getReferenceNo());
		tx.setTransactionCode(AcAccountTransactionCode.REFUND);
		tx.setAccount(refund.getPayments().getAccount());
		tx.setAmount(refund.getAmount().negate());
		accountService.addAccountTransaction(refund.getPayments().getAccount(), tx);
	}

	// TASK REFUND

	@Override
	public AcRefundPayment findRefundPaymentByTaskId(String taskId) {
		Task task = workflowService.findTask(taskId);
		Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
		return refundPaymentDao.findById((Long) map.get(AccountConstants.REFUND_ID));
	}

	@Override
	public Task findRefundPaymentTaskByTaskId(String taskId) {
		return workflowService.findTask(taskId);
	}

	@Override
	public List<Task> findAssignedRefundPaymentTasks(Integer offset, Integer limit) {
		return workflowService.findAssignedTasks(AcRefundPayment.class.getName(), offset, limit);
	}

	@Override
	public List<Task> findPooledRefundPaymentTasks(Integer offset, Integer limit) {
		return workflowService.findPooledTasks(AcRefundPayment.class.getName(), offset, limit);
	}

	@Override
	public String startRefundPaymentTask(AcRefundPayment refundPayment) {
		String refNo = systemService.generateReferenceNo(AccountConstants.REFUND_REFRENCE_NO);
		refundPayment.setReferenceNo(refNo);
		LOG.debug("Processing knockoff with refNo {}", new Object[] { refNo });

		refundPaymentDao.saveOrUpdate(refundPayment, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(refundPayment);

		workflowService.processWorkflow(refundPayment, prepareVariables(refundPayment));
		return refNo;
	}

	// ====================================================================================================
	// //
	// WAIVER FINANCE APPLICATION
	// ====================================================================================================
	// //

	@Override
	public AcWaiverFinanceApplication findWaiverfinanceApplicationByTaskId(String taskId) {
		Task task = workflowService.findTask(taskId);
		Map<String, Object> map = workflowService.getVariables(task.getExecutionId());
		return waiverFinanceApplicationDao.findById((Long) map.get(WAIVER_FINANCE_APPLICATION_ID));
	}

	@Override
	public Task findWaiverFinanceApplicationTaskByTaskId(String taskId) {
		return workflowService.findTask(taskId);
	}

	@Override
	public List<Task> findAssignedWaiverFinanceApplicationTasks(Integer offset, Integer limit) {
		return workflowService.findAssignedTasks(AcWaiverFinanceApplication.class.getName(), offset, limit);
	}

	@Override
	public List<Task> findPooledWaiverFinanceApplicationTasks(Integer offset, Integer limit) {
		return workflowService.findPooledTasks(AcWaiverFinanceApplication.class.getName(), offset, limit);
	}

	@Override
	public String startWaiverFinanceApplicationTask(AcWaiverFinanceApplication application) {
		String referenceNo = systemService
				.generateReferenceNo(AccountConstants.WAIVER_FINANCE_APPLICATION_REFERENCE_NO);
		application.setReferenceNo(referenceNo);
		LOG.debug("Processing application with refNo {}", referenceNo);

		waiverFinanceApplicationDao.saveOrUpdate(application, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		sessionFactory.getCurrentSession().refresh(application);

		workflowService.processWorkflow(application, prepareVariables(application));
		return referenceNo;
	}

	@Override
	public void updateWaiverFinanceApplication(AcWaiverFinanceApplication application) {
		waiverFinanceApplicationDao.update(application, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void cancelWaiverFinanceApplication(AcWaiverFinanceApplication application) {
		application.getFlowdata().setState(AcFlowState.CANCELLED);
		application.getFlowdata().setCancelledDate(new Timestamp(System.currentTimeMillis()));
		application.getFlowdata().setCancelerId(securityService.getCurrentUser().getId());
		waiverFinanceApplicationDao.update(application, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public AcWaiverFinanceApplication findWaiverFinanceApplicationById(Long id) {
		return waiverFinanceApplicationDao.findById(id);
	}

	@Override
	public AcWaiverFinanceApplication findWaiverFinanceApplicationByReferenceNo(String referenceNo) {
		return waiverFinanceApplicationDao.findByReferenceNo(referenceNo);
	}
	
	@Override
	public AcWaiverItem findWaiverItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcWaiverFinanceApplication waiver) {
		return waiverFinanceApplicationDao.findWaiverItemByChargeCode(chargeCode, invoice, waiver);
	}

	@Override
	public AcWaiverItem findWaiverItemByCharge(AcAccountCharge charge, AcWaiverFinanceApplication waiver) {
		return waiverFinanceApplicationDao.findWaiverItemByCharge(charge, waiver);
	}
	
	@Override
	public AcWaiverItem findWaiverItemByDebitNote(AcDebitNote debitNote, AcWaiverFinanceApplication waiver) {
		return waiverFinanceApplicationDao.findWaiverItemByDebitNote(debitNote, waiver);
	}

	@Override
	public List<AcWaiverFinanceApplication> findWaiverFinanceApplicationsByFlowState(AcFlowState acFlowState) {
		return waiverFinanceApplicationDao.findByFlowState(acFlowState);
	}

	@Override
	public List<AcWaiverFinanceApplication> findWaiverFinanceApplicationsByFlowStates(AcFlowState... acFlowState) {
		return waiverFinanceApplicationDao.findByFlowStates(acFlowState);
	}

	@Override
	public List<AcWaiverFinanceApplication> findWaiverFinanceApplications(String filter, Integer offset,
			Integer limit) {
		return waiverFinanceApplicationDao.find(offset, limit);
	}

	@Override
	public List<AcWaiverFinanceApplication> findWaiverFinanceApplications(AcAcademicSession academicSession,
			Integer offset, Integer limit) {
		return waiverFinanceApplicationDao.find(academicSession, offset, limit);
	}

	@Override
	public Integer countWaiverFinanceApplication(String filter) {
		return waiverFinanceApplicationDao.count();
	}

	@Override
	public Integer countWaiverFinanceApplication(AcAcademicSession academicSession) {
		return waiverFinanceApplicationDao.count(academicSession);
	}
	
    @Override
    public void addWaiverInvoice(AcWaiverFinanceApplication waiver, AcInvoice invoice) {
    	waiverFinanceApplicationDao.addWaiverInvoice(waiver, invoice, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
    }
    
    @Override
    public void addWaiverAccountCharge(AcWaiverFinanceApplication waiver, AcAccountCharge accountCharge) {
    	waiverFinanceApplicationDao.addWaiverAccountCharge(waiver, accountCharge, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
    }
    
    @Override
    public void addWaiverDebitNote(AcWaiverFinanceApplication waiver, AcDebitNote debitNote) {
    	waiverFinanceApplicationDao.addWaiverDebitNote(waiver, debitNote, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
    }
    
    @Override
    public void addWaiverItem(AcWaiverFinanceApplication waiver, AcWaiverItem item) {
    	waiverFinanceApplicationDao.addWaiverItem(waiver, item, securityService.getCurrentUser());;
		sessionFactory.getCurrentSession().flush();
    }
    
	@Override
	public void updateWaiver(AcWaiverFinanceApplication waiver) {
		waiverFinanceApplicationDao.update(waiver, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void updateWaiverItem(AcWaiverFinanceApplication waiver, AcWaiverItem waiverItem) {
		waiverFinanceApplicationDao.updateItem(waiver, waiverItem, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}
	
	@Override
	public void itemToWaiverItem(AcWaiverFinanceApplication waiver, AcInvoice invoice) {
		List<AcInvoiceItem> invoiceItems = billingService.findInvoiceItems(invoice);
		for (AcInvoiceItem invoiceItem : invoiceItems) {
			
			if (invoice.getBalanceAmount().compareTo(waiver.getGracedAmount()) <= 0) {
				AcWaiverItem waiverItem = new AcWaiverItemImpl();
				waiverItem.setAppliedAmount(invoiceItem.getBalanceAmount());
				waiverItem.setDueAmount(invoiceItem.getBalanceAmount());
				waiverItem.setChargeCode(invoiceItem.getChargeCode());
				waiverItem.setDescription(invoiceItem.getChargeCode().getDescription());
				waiverItem.setInvoice(invoice);
				waiverItem.setTotalAmount(BigDecimal.ZERO);
				waiverItem.setWaiverFinanceApplication(waiver);
				billingService.addWaiverItem(waiver, waiverItem);
			}

			else if (invoice.getBalanceAmount().compareTo(waiver.getGracedAmount()) > 0) {
				
				if (waiver.getGracedAmount().compareTo(invoiceItem.getBalanceAmount()) > 0) {
					AcWaiverItem waiverItem = new AcWaiverItemImpl();
					waiverItem.setAppliedAmount(invoiceItem.getBalanceAmount());
					waiverItem.setDueAmount(invoiceItem.getBalanceAmount());
					waiverItem.setChargeCode(invoiceItem.getChargeCode());
					waiverItem.setDescription(invoiceItem.getChargeCode().getDescription());
					waiverItem.setInvoice(invoice);
					waiverItem.setTotalAmount(BigDecimal.ZERO);
					waiverItem.setWaiverFinanceApplication(waiver);
					billingService.addWaiverItem(waiver, waiverItem);
				}

				else if (waiver.getGracedAmount().compareTo(invoiceItem.getBalanceAmount()) < 0) {
					AcWaiverItem waiverItem = new AcWaiverItemImpl();
					waiverItem.setAppliedAmount(waiver.getGracedAmount());
					waiverItem.setDueAmount(invoiceItem.getBalanceAmount());
					waiverItem.setChargeCode(invoiceItem.getChargeCode());
					waiverItem.setDescription(invoiceItem.getChargeCode().getDescription());
					waiverItem.setInvoice(invoice);
					waiverItem.setTotalAmount(invoiceItem.getBalanceAmount().subtract(waiverItem.getAppliedAmount()));
					waiverItem.setWaiverFinanceApplication(waiver);
					billingService.addWaiverItem(waiver, waiverItem);

				
					if (waiver.getGracedAmount().compareTo(BigDecimal.ZERO) <= 0 ) {
						waiverItem.setAppliedAmount(BigDecimal.ZERO);
						waiverItem.setDueAmount(invoiceItem.getBalanceAmount());
						waiverItem.setChargeCode(invoiceItem.getChargeCode());
						waiverItem.setDescription(invoiceItem.getChargeCode().getDescription());
						waiverItem.setInvoice(invoice);
						waiverItem.setTotalAmount(invoiceItem.getBalanceAmount());
						waiverItem.setWaiverFinanceApplication(waiver);
						billingService.addWaiverItem(waiver, waiverItem);

					}
				}
				
				waiver.setGracedAmount(waiver.getGracedAmount().subtract(invoiceItem.getBalanceAmount()));
			}
			
		}
		
		waiver.setGracedAmount(waiver.getGracedAmount().subtract(waiverFinanceApplicationDao.sumAppliedAmount(waiver, securityService.getCurrentUser())));

	}
	
    @Override
    public List<AcWaiverItem> findWaiverItems(AcWaiverFinanceApplication waiver) {
    	return waiverFinanceApplicationDao.findItems(waiver);
    }
    
    @Override
    public List<AcWaiverItem> findWaiverItems(AcWaiverFinanceApplication waiver, AcInvoice invoice) {
    	return waiverFinanceApplicationDao.findItems(waiver, invoice);
    }
    
    @Override
    public List<AcWaiverInvoice> findWaivers(AcWaiverFinanceApplication waiver) {
    	return waiverFinanceApplicationDao.findWaivers(waiver);
    }
    
    @Override
    public List<AcWaiverAccountCharge> findWaiverAccountCharge(AcWaiverFinanceApplication waiver) {
    	return waiverFinanceApplicationDao.findWaiverAccountCharge(waiver);
    }

    @Override
    public List<AcWaiverDebitNote> findDebitNote(AcWaiverFinanceApplication waiver) {
    	return waiverFinanceApplicationDao.findWaiverDebitNote(waiver);
    }
    
    @Override
    public BigDecimal sumAppliedAmount(AcWaiverFinanceApplication waiver) {
    	return waiverFinanceApplicationDao.sumAppliedAmount(waiver, securityService.getCurrentUser());
    }
    
    @Override
    public BigDecimal sumAppliedAmount(AcInvoice invoice, AcWaiverFinanceApplication waiver) {
    	return waiverFinanceApplicationDao.sumAppliedAmount(invoice, waiver, securityService.getCurrentUser());
    }
    
    @Override
    public boolean hasWaiver(AcWaiverFinanceApplication waiver, AcInvoice invoice) {
    	return waiverFinanceApplicationDao.hasWaiver(waiver, invoice);
    }
    
	// ====================================================================================================
	// ACCOUNT CHARGE
	// ====================================================================================================
	// 

	@Override
	public void itemToReceiptChargeItem(AcAccountCharge charge, String code, AcReceipt receipt) {
		
		
		AcAccount account = accountService.findAccountByCode(code);
		List<AcAccountCharge> accountCharges = accountService.findAccountCharges(account);
		for (AcAccountCharge accountCharge : accountCharges) {
			
				
			if (accountCharge.getBalanceAmount().compareTo(receipt.getTotalPayment()) <= 0) {
				AcReceiptAccountChargeItem receiptItem = new AcReceiptAccountChargeItemImpl();
				receiptItem.setDueAmount(accountCharge.getBalanceAmount());
				receiptItem.setDescription(accountCharge.getDescription());
				receiptItem.setAdjustedAmount(BigDecimal.ZERO);
				receiptItem.setAppliedAmount(accountCharge.getBalanceAmount());
				receiptItem.setPrice(BigDecimal.ZERO);
				receiptItem.setReceipt(receipt);
				receiptItem.setTotalAmount(BigDecimal.ZERO);
				receiptItem.setUnit(0);

				billingService.addReceiptChargeItem(receipt, receiptItem);
			}

			else if (accountCharge.getBalanceAmount().compareTo(receipt.getTotalPayment()) > 0) {
				
				if (receipt.getTotalPayment().compareTo(accountCharge.getBalanceAmount()) > 0) {
					AcReceiptAccountChargeItem receiptItem = new AcReceiptAccountChargeItemImpl();
					receiptItem.setDueAmount(accountCharge.getBalanceAmount());
					receiptItem.setAdjustedAmount(BigDecimal.ZERO);
					receiptItem.setAppliedAmount(accountCharge.getBalanceAmount());
					receiptItem.setPrice(BigDecimal.ZERO);
					receiptItem.setReceipt(receipt);
					receiptItem.setTotalAmount(BigDecimal.ZERO);
					receiptItem.setUnit(0);
					
					billingService.addReceiptChargeItem(receipt, receiptItem);
				}

				else if (receipt.getTotalPayment().compareTo(accountCharge.getBalanceAmount()) < 0) {
					AcReceiptAccountChargeItem receiptItem = new AcReceiptAccountChargeItemImpl();
					receiptItem.setDueAmount(accountCharge.getBalanceAmount());
					receiptItem.setAdjustedAmount(BigDecimal.ZERO);
					receiptItem.setAppliedAmount(receipt.getTotalPayment());
					receiptItem.setPrice(BigDecimal.ZERO);
					receiptItem.setReceipt(receipt);
					receiptItem.setTotalAmount(accountCharge.getBalanceAmount().subtract(receiptItem.getAppliedAmount()));
					receiptItem.setUnit(0);
					billingService.addReceiptChargeItem(receipt, receiptItem);
				
					if (receipt.getTotalPayment().compareTo(BigDecimal.ZERO) <= 0 ) {
						receiptItem.setDueAmount(accountCharge.getBalanceAmount());
						receiptItem.setAdjustedAmount(BigDecimal.ZERO);
						receiptItem.setAppliedAmount(BigDecimal.ZERO);
						receiptItem.setPrice(BigDecimal.ZERO);
						receiptItem.setReceipt(receipt);
						receiptItem.setTotalAmount(accountCharge.getBalanceAmount());
						receiptItem.setUnit(0);
						billingService.addReceiptChargeItem(receipt, receiptItem);
					}
				}
				
				receipt.setTotalPayment(receipt.getTotalPayment().subtract(accountCharge.getBalanceAmount()));
				LOG.debug("value invoiceItem after looping {}", receipt.getTotalReceived());
			}
			
		}
		
		receipt.setTotalPayment(receipt.getTotalReceived().subtract(receiptDao.sumAppliedAmount(receipt, securityService.getCurrentUser())));

	}
    
    @Override
	public void addReceiptChargeItem(AcReceipt receipt, AcReceiptAccountChargeItem item) {
		receiptDao.addChargeItem(receipt, item, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void updateReceiptChargeItem(AcReceipt receipt, AcReceiptAccountChargeItem item) {
		receiptDao.updateChargeItem(receipt, item, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		
		item.setTotalAmount(item.getDueAmount().subtract(item.getAppliedAmount()));
		receiptDao.updateChargeItem(receipt, item, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
		
		receipt.setTotalPayment(receipt.getTotalReceived().subtract(receiptDao.sumAppliedAmount(receipt, securityService.getCurrentUser())));
		receiptDao.update(receipt, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void deleteReceiptChargeItem(AcReceipt receipt, AcReceiptAccountChargeItem item) {
		receiptDao.deleteChargeItem(receipt, item, securityService.getCurrentUser());
		sessionFactory.getCurrentSession().flush();
	}

	// ====================================================================================================
	// //
	// TAX
	// ====================================================================================================
	// //

	public void calculateNetAmount(AcInvoiceItem invoiceItem) {
		BigDecimal taxRate = invoiceItem.getChargeCode().getTaxCode().getTaxRate();
		BigDecimal amount = invoiceItem.getAmount();
		BigDecimal taxAmount = amount.multiply(taxRate);
		BigDecimal netAmount = amount.add(taxAmount);
		if (invoiceItem.getChargeCode().getInclusive() == false) {
			invoiceItem.setNetAmount(netAmount);
			invoiceItem.setTaxAmount(taxAmount);
		} else if (invoiceItem.getChargeCode().getInclusive() == true) {
			invoiceItem.setTaxAmount(taxAmount);
			invoiceItem.setNetAmount(amount);
		}
	}
}