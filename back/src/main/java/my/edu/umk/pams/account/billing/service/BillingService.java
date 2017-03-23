package my.edu.umk.pams.account.billing.service;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.identity.model.AcActor;
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

    void startInvoiceTask(AcInvoice invoice);

    void cancelInvoice(AcInvoice invoice);

    void saveInvoice(AcInvoice invoice);

    void updateInvoice(AcInvoice invoice);

    void addInvoiceItem(AcInvoice invoice, AcInvoiceItem invoiceItem);

    void updateInvoiceItem(AcInvoice invoice, AcInvoiceItem invoiceItem);

    void attach(AcInvoice invoice, AcAccountCharge charge) throws Exception;

    void attach(AcInvoice invoice, AcAccount account, AcAccountChargeType... type) throws Exception;

    void attach(AcInvoice invoice, AcAccount account) throws Exception;

    void detach(AcInvoice invoice, AcAccountCharge charge) throws Exception;

    void detach(AcInvoice invoice, AcAccount account, AcAccountChargeType... chargeTypes) throws Exception;

    // ==================================================================================================== //
    // INVOICE
    // ==================================================================================================== //

    AcInvoice findInvoiceById(Long id);

    AcInvoice findInvoiceByReferenceNo(String referenceNo);

    AcInvoiceItem findInvoiceItemById(Long id);

    List<AcInvoice> findInvoicesBySourceNo(String sourceNo);

    List<AcInvoice> findInvoices(String filter, Integer offset, Integer limit);

    List<AcInvoice> findInvoices(AcAccount account, Integer offset, Integer limit);

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


    // ==================================================================================================== //
    // RECEIPT
    // ==================================================================================================== //

    AcReceipt findReceiptByTaskId(String taskId);

    // todo(uda): find by record id

    List<Task> findAssignedReceiptTasks(Integer offset, Integer limit);

    List<Task> findPooledReceiptTasks(Integer offset, Integer limit);

    void startReceiptTask(AcReceipt intake);

    void updateReceipt(AcReceipt intake);

    void cancelReceipt(AcReceipt intake);

    void addReceiptItem(AcReceipt receipt, AcReceiptItem item);

    void updateReceiptItem(AcReceipt receipt, AcReceiptItem item);

    void deleteReceiptItem(AcReceipt receipt, AcReceiptItem item);

    // ==================================================================================================== //
    // RECEIPT
    // ==================================================================================================== //

    AcReceipt findReceiptById(Long id);

    AcReceipt findReceiptByReferenceNo(String referenceNo);

    AcReceipt findReceiptByReceiptNo(String receiptNo);

    AcReceiptItem findReceiptItemById(Long id);

    List<AcReceipt> findReceipts(AcReceiptType type, Integer offset, Integer limit);

    List<AcReceipt> findReceipts(AcReceiptType type, String filter, Integer offset, Integer limit);

    List<AcReceiptItem> findReceiptItems(AcReceipt receipt);

    Integer countReceipt(AcReceiptType type);

    Integer countReceipt(AcReceiptType type, String filter);

    Integer countReceiptItem(AcReceipt receipt);

}
