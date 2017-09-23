package my.edu.umk.pams.account.billing.service;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcUser;

import org.activiti.engine.task.Task;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
public interface BillingService {

    // ==================================================================================================== //
    // INVOICE
    // workflow
    // ==================================================================================================== //
    AcInvoice findInvoiceByTaskId(String taskId);

    Task findInvoiceTaskByTaskId(String taskId);

    List<Task> findAssignedInvoiceTasks(Integer offset, Integer limit);

    List<Task> findPooledInvoiceTasks(Integer offset, Integer limit);

    String startInvoiceTask(AcInvoice invoice);

    void cancelInvoice(AcInvoice invoice);

    void saveInvoice(AcInvoice invoice);

    void updateInvoice(AcInvoice invoice);

    void addInvoiceItem(AcInvoice invoice, AcInvoiceItem invoiceItem);

    void updateInvoiceItem(AcInvoice invoice, AcInvoiceItem invoiceItem);

    void deleteInvoiceItem(AcInvoice invoice, AcInvoiceItem invoiceItem);

    void attach(AcInvoice invoice, AcAccountCharge charge) throws Exception;

    void attach(AcInvoice invoice, AcAccount account, AcAccountChargeType... type) throws Exception;

    void attach(AcInvoice invoice, AcAccount account) throws Exception;

    void detach(AcInvoice invoice, AcAccountCharge charge) throws Exception;

    void detach(AcInvoice invoice, AcAccount account, AcAccountChargeType... chargeTypes) throws Exception;
    
    void itemToReceiptItem(AcInvoice invoice, AcReceipt receipt);
    
    void updateItemToReceipt(AcReceipt receipt);

    // ==================================================================================================== //
    // INVOICE
    // ==================================================================================================== //

    AcInvoice findInvoiceById(Long id);

    AcInvoice findInvoiceByReferenceNo(String referenceNo);

    AcInvoiceItem findInvoiceItemById(Long id);

    List<AcInvoice> findInvoicesBySourceNo(String sourceNo);

    List<AcInvoice> findInvoices(String filter, Integer offset, Integer limit);

    List<AcInvoice> findInvoices(AcAccount account, Integer offset, Integer limit);
    
    List<AcInvoice> findInvoices(String term, Integer offset, Integer limit, List<String> columns);

	List<AcInvoice> findInvoicesByFlowState(AcFlowState acFlowState);
	
	List<AcInvoice> findInvoicesByFlowStates(AcFlowState... flowStates );

    List<AcInvoice> findUnpaidInvoices(AcAccount account, Integer offset, Integer limit);

    List<AcInvoice> findPaidInvoices(AcAccount account, Integer offset, Integer limit);

    List<AcInvoiceItem> findInvoiceItems(AcInvoice invoice);

    List<AcInvoiceItem> findInvoiceItems(AcInvoice invoice, Integer offset, Integer limit);

//    List<AcInvoiceTransaction> findInvoiceTransactions(AcInvoice invoice);
//
//    List<AcInvoiceTransaction> findInvoiceTransactions(AcInvoice invoice, Integer offset, Integer limit);

    Integer countInvoiceItem(AcInvoice invoice);

//    Integer countInvoiceTransaction(AcInvoice invoice);

    Integer countInvoice(AcAccount account);

    Integer countPaidInvoice(AcAccount account);

    Integer countUnpaidInvoice(AcAccount account);

    boolean hasInvoice(AcAccount account);

    boolean hasUnpaidInvoice(AcAccount account);

    boolean hasUnpaidInvoice(AcAccount account, Integer days);

    boolean isInvoiceOverdue(AcAccountCharge charge);

    BigDecimal sumUnpaidInvoice(AcAccountCharge charge);

    boolean hasBalance(AcAcademicSession academicSession, AcActor actor);

    AcInvoice executeInvoice();

    void executeScheduler();

    void post(AcInvoice invoice);

    // ==================================================================================================== //
    // DEBIT NOTE
    // workflow
    // ==================================================================================================== //

    AcDebitNote findDebitNoteByTaskId(String taskId);

    Task findDebitNoteTaskByTaskId(String taskId);

    List<Task> findAssignedDebitNoteTasks(Integer offset, Integer limit);

    List<Task> findPooledDebitNoteTasks(Integer offset, Integer limit);

    String startDebitNoteTask(AcDebitNote debitNote);

    void cancelDebitNote(AcDebitNote debitNote);

    void saveDebitNote(AcDebitNote debitNote);

    void updateDebitNote(AcDebitNote debitNote);

    void addDebitNoteItem(AcDebitNote debitNote, AcDebitNoteItem debitNoteItem);

    void updateDebitNoteItem(AcDebitNote debitNote, AcDebitNoteItem debitNoteItem);

    void deleteDebitNoteItem(AcDebitNote debitNote, AcDebitNoteItem debitNoteItem);


    // ==================================================================================================== //
    // DEBIT NOTE
    // ==================================================================================================== //

    AcDebitNote findDebitNoteById(Long id);

    AcDebitNoteItem findDebitNoteItemById(Long id);

    AcDebitNote findDebitNoteByReferenceNo(String referenceNo);

    List<AcDebitNote> findDebitNotes(AcInvoice invoice);

    List<AcDebitNote> findDebitNotes(AcInvoice invoice, String filter, Integer offset, Integer limit);

    List<AcDebitNote> findDebitNotesByFlowState(AcFlowState flowState);

    List<AcDebitNoteItem> findDebitNoteItems(AcDebitNote debitNote);

    Integer countDebitNote(AcInvoice invoice);

    boolean hasDebitNote(AcInvoice invoice);

    void post(AcDebitNote debitNote);
    
    List<AcDebitNote> findUnpaidDebitNotes(AcAccount account, Integer offset, Integer limit);


    // ==================================================================================================== //
    // CREDIT NOTE
    // workflow
    // ==================================================================================================== //

    AcCreditNote findCreditNoteByTaskId(String taskId);

    Task findCreditNoteTaskByTaskId(String taskId);

    List<Task> findAssignedCreditNoteTasks(Integer offset, Integer limit);

    List<Task> findPooledCreditNoteTasks(Integer offset, Integer limit);

    String startCreditNoteTask(AcCreditNote creditNote);

    void cancelCreditNote(AcCreditNote creditNote);

    void saveCreditNote(AcCreditNote creditNote);

    void updateCreditNote(AcCreditNote creditNote);

    void addCreditNoteItem(AcCreditNote creditNote, AcCreditNoteItem creditNoteItem);

    void updateCreditNoteItem(AcCreditNote creditNote, AcCreditNoteItem creditNoteItem);

    void deleteCreditNoteItem(AcCreditNote creditNote, AcCreditNoteItem creditNoteItem);

    // ==================================================================================================== //
    // CREDIT NOTE
    // ==================================================================================================== //

    AcCreditNote findCreditNoteById(Long id);

    AcCreditNoteItem findCreditNoteItemById(Long id);

    AcCreditNote findCreditNoteByReferenceNo(String referenceNo);

    List<AcCreditNote> findCreditNotes(AcInvoice invoice);

    List<AcCreditNote> findCreditNotesByFlowState(AcFlowState flowState);

    List<AcCreditNoteItem> findCreditNoteItems(AcCreditNote creditNote);

    Integer countCreditNote(AcInvoice invoice);

    boolean hasCreditNote(AcInvoice invoice);
    
    void post(AcCreditNote creditNote);

    // ==================================================================================================== //
    // RECEIPT
    // ==================================================================================================== //
    // workflow
    AcReceipt findReceiptByTaskId(String taskId);

    Task findReceiptTaskByTaskId(String taskId);

    List<Task> findAssignedReceiptTasks(Integer offset, Integer limit);

    List<Task> findPooledReceiptTasks(Integer offset, Integer limit);

    String startReceiptTask(AcReceipt receipt);

    void updateReceipt(AcReceipt receipt);

    void cancelReceipt(AcReceipt receipt);

    void addReceiptItem(AcReceipt receipt, AcReceiptItem item);

    void updateReceiptItem(AcReceipt receipt, AcReceiptItem item);

    void deleteReceiptItem(AcReceipt receipt, AcReceiptItem item);
    
    void addReceiptInvoice(AcReceipt receipt, AcInvoice invoice);
    
    void deleteReceiptInvoice(AcReceipt receipt, AcInvoice invoice);
    
    void addReceiptCharge(AcReceipt receipt, AcAccountCharge accountCharge);

    // ==================================================================================================== //
    // RECEIPT
    // ==================================================================================================== //

    AcReceipt findReceiptById(Long id);

    AcReceipt findReceiptByReferenceNo(String referenceNo);

    AcReceipt findReceiptByReceiptNo(String receiptNo);

    AcReceiptItem findReceiptItemById(Long id);

	AcReceiptItem findReceiptItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcReceipt receipt);

    List<AcReceipt> findReceipts(String filter, Integer offset, Integer limit);

    List<AcReceipt> findReceipts(AcReceiptType type, Integer offset, Integer limit);

    List<AcReceipt> findReceipts(AcReceiptType type, String filter, Integer offset, Integer limit);

    List<AcReceipt> findReceiptsByFlowState(AcFlowState flowState);
    
    List<AcReceipt> findReceiptsByFlowState(AcFlowState... flowStates);

    List<AcReceiptItem> findReceiptItems(AcReceipt receipt);
    
    List<AcReceiptItem> findReceiptItems(AcReceipt receipt, AcInvoice invoice);
    
    List<AcReceiptInvoice> findReceipts(AcReceipt receipt);
    
    List<AcReceiptAccountCharge> findReceiptsAccountCharge(AcReceipt receipt);
    
    List<AcReceiptDebitNote> findReceiptsDebitNote(AcReceipt receipt);

    Integer countReceipt(AcReceiptType type);

    Integer countReceipt(AcReceiptType type, String filter);

    Integer countReceiptItem(AcReceipt receipt);

    void post(AcReceipt receipt);
    
    void calculateChargeInvoice(AcReceipt receipt, AcAccount account);
    
    void calculateCharge(AcReceipt receipt, AcAccount account);
    
    BigDecimal sumAdvancePayment(AcReceipt receipt);
    
    BigDecimal sumAppliedAmount(AcInvoice invoice, AcReceipt receipt);
    
    // ==================================================================================================== //
    // ADVANCE PAYMENT
    // ==================================================================================================== //
    
    AcAdvancePayment findAdvancePaymentById(Long id);
    
    AcAdvancePayment findAdvancePaymentByReferenceNo(String referenceNo);
    
    List<AcAdvancePayment> findAdvancePayments(boolean status, String filter, Integer offset, Integer limit);
    
    List<AcAdvancePayment> findUnpaidAdvancePayments(AcAccount account, Integer offset, Integer limit);
    
    void addAdvancePayment(AcAdvancePayment payment, AcUser user);
    
    void updateAdvancePayment(AcAdvancePayment payment);
    
    // ==================================================================================================== //
    // KNOCKOFF
    // ==================================================================================================== //

    AcKnockoff findKnockoffById(Long id);
    
    AcKnockoff findKnockoffByReferenceNo(String referenceNo);
    
    AcKnockoffItem findKnockoffItemById(Long id);
    
    List<AcKnockoff> findKnockoffs(String filter, Integer offset, Integer limit);
    
	List<AcKnockoff> findKnockoffsByFlowState(AcFlowState acFlowState);
	
	List<AcKnockoff> findKnockoffsByFlowStates(AcFlowState... flowStates );
	
	List<AcKnockoffItem> findAcKnockoffs(AcKnockoff knockoff);
	
	List<AcKnockoffItem> findAcKnockoffs(AcKnockoff knockoff, AcInvoice invoice);
	
	List<AcKnockoffInvoice> findKnockoffs(AcKnockoff knockoff);

    boolean hasKnockoff(AcKnockoff knockoff);
    
    void addKnockoff(AcKnockoff knockoff);

    void updateKnockoff(AcKnockoff knockoff);

    void removeKnockoff(AcKnockoff knockoff, AcUser user);
    
    void itemToKnockoffItem(AcInvoice invoice, AcKnockoff knockoff);
    
    void updateitemToKnockoff(AcKnockoff knockoff);
    
    void addKnockoffInvoice(AcKnockoff knockoff, AcInvoice invoice);
    
    void addKnockoffItem(AcKnockoff knockoff, AcKnockoffItem item);
    
    void updateKnockoffItem(AcKnockoff knockoff, AcKnockoffItem item);
    
    void post(AcKnockoff knockoff);
    
    //TASK
    
    AcKnockoff findKnockoffByTaskId(String taskId);
    
    Task findKnockoffTaskByTaskId(String taskId);

    List<Task> findAssignedKnockoffTasks(Integer offset, Integer limit);

    List<Task> findPooledKnockoffTasks(Integer offset, Integer limit);
    
    String startKnockoffTask(AcKnockoff knockoff);
        
    // ==================================================================================================== //
    // REFUND PAYMENT
    // ==================================================================================================== //
    
    List<AcRefundPayment> findRefundPayments(String filter, Integer offset, Integer limit);
    
	List<AcRefundPayment> findRefundPaymentsByFlowState(AcFlowState acFlowState);
	
	List<AcRefundPayment> findRefundPaymentsByFlowStates(AcFlowState... flowStates );
    
    AcRefundPayment findRefundPaymentById (Long id);
    
    AcRefundPayment findRefundPaymentByReferenceNo(String referenceNo);

    boolean hasRefundPayment(AcRefundPayment refund);
    
    void addRefundPayment(AcRefundPayment refund, AcUser user);

    void updateRefundPayment(AcRefundPayment refund);

    void removeRefundPayment(AcRefundPayment refund, AcUser user);
    
    void calculateNetAmount(AcInvoiceItem invoiceItem);
    
    //TASK
    
    AcRefundPayment findRefundPaymentByTaskId(String taskId);
    
    Task findRefundPaymentTaskByTaskId(String taskId);

    List<Task> findAssignedRefundPaymentTasks(Integer offset, Integer limit);

    List<Task> findPooledRefundPaymentTasks(Integer offset, Integer limit);
    
    String startRefundPaymentTask(AcRefundPayment refundPayment);
    // ==================================================================================================== //
    // WAIVER FINANCE APPLICATION
    // ==================================================================================================== //

    // workflow

	AcWaiverFinanceApplication findWaiverfinanceApplicationByTaskId(String taskId);

	Task findWaiverFinanceApplicationTaskByTaskId(String taskId);

	List<Task> findAssignedWaiverFinanceApplicationTasks(Integer offset, Integer limit);

	List<Task> findPooledWaiverFinanceApplicationTasks(Integer offset, Integer limit);

	String startWaiverFinanceApplicationTask(AcWaiverFinanceApplication application);

	void updateWaiverFinanceApplication(AcWaiverFinanceApplication application);

	void cancelWaiverFinanceApplication(AcWaiverFinanceApplication application);

    // ==================================================================================================== //
    // WAIVER APPLICATION
    // ==================================================================================================== //
	
	AcWaiverFinanceApplication findWaiverFinanceApplicationById(Long id);

	AcWaiverFinanceApplication findWaiverFinanceApplicationByReferenceNo(String referenceNo);

	List<AcWaiverFinanceApplication> findWaiverFinanceApplicationsByFlowState(AcFlowState acFlowState);

	List<AcWaiverFinanceApplication> findWaiverFinanceApplicationsByFlowStates(AcFlowState... acFlowState);

	List<AcWaiverFinanceApplication> findWaiverFinanceApplications(String filter, Integer offset, Integer limit);

	Integer countWaiverFinanceApplication(String filter);

	Integer countWaiverFinanceApplication(AcAcademicSession academicSession);

	List<AcWaiverFinanceApplication> findWaiverFinanceApplications(AcAcademicSession academicSession, Integer offset, Integer limit);
	
    void addWaiverInvoice(AcWaiverFinanceApplication waiver, AcInvoice invoice);
    
    void addWaiverItem(AcWaiverFinanceApplication waiver, AcWaiverItem item);
    
	void itemToWaiverItem(AcWaiverFinanceApplication waiver, AcInvoice invoice);
	
    List<AcWaiverItem> findWaiverItems(AcWaiverFinanceApplication waiver);
    
    List<AcWaiverItem> findWaiverItems(AcWaiverFinanceApplication waiver, AcInvoice invoice);
    
    List<AcWaiverInvoice> findWaivers(AcWaiverFinanceApplication waiver);

	

	

}
