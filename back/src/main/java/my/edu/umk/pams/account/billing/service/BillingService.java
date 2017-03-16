package my.edu.umk.pams.account.billing.service;

import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcReceiptType;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @author PAMS
 */
public interface BillingService {
    // ==================================================================================================== //
    // INVOICE
    // ==================================================================================================== //



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
