package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcReceiptType;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.util.List;

public interface AcReceiptDao extends GenericDao<Long, AcReceipt> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcReceiptItem findItemById(Long id);

    AcReceipt findByReferenceNo(String referenceNo);

    AcReceipt findBySourceNo(String sourceNo);

    AcReceipt findByReceiptNo(String receiptNo);

    List<AcReceipt> find(String filter, Integer offset, Integer limit);

    List<AcReceipt> find(AcReceiptType type, Integer offset, Integer limit);

    List<AcReceipt> find(AcReceiptType type, String filter, Integer offset, Integer limit);

    List<AcReceiptItem> findItems(AcReceipt receipt);

    List<AcReceiptItem> findItems(AcReceipt receipt, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcReceiptType type);

    Integer count(AcReceiptType type, String filter);

    Integer countItem(AcReceipt receipt);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addItem(AcReceipt receipt, AcReceiptItem item, AcUser user);

    void updateItem(AcReceipt receipt, AcReceiptItem item, AcUser user);

    void removeItem(AcReceipt receipt, AcReceiptItem item, AcUser user);

    void deleteItem(AcReceipt receipt, AcReceiptItem item, AcUser user);
}
