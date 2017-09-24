package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptAccountCharge;
import my.edu.umk.pams.account.billing.model.AcReceiptDebitNote;
import my.edu.umk.pams.account.billing.model.AcReceiptInvoice;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcReceiptType;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.math.BigDecimal;
import java.util.List;

public interface AcReceiptDao extends GenericDao<Long, AcReceipt> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcReceiptItem findItemById(Long id);

    AcReceipt findByReferenceNo(String referenceNo);

    AcReceipt findBySourceNo(String sourceNo);

    AcReceipt findByReceiptNo(String receiptNo);
    
    AcReceiptItem findReceiptItemByChargeCode(AcChargeCode chargeCode, AcInvoice invoice, AcReceipt receipt);

    List<AcReceipt> find(String filter, Integer offset, Integer limit);

    List<AcReceipt> find(AcReceiptType type, Integer offset, Integer limit);

    List<AcReceipt> find(AcReceiptType type, String filter, Integer offset, Integer limit);

    List<AcReceipt> findByFlowState(AcFlowState flowState);
    
    List<AcReceipt> findByFlowStates(AcFlowState... flowStates);

    List<AcReceiptItem> findItems(AcReceipt receipt);
    
    List<AcReceiptItem> findItems(AcReceipt receipt, AcInvoice invoice);

    List<AcReceiptItem> findItems(AcReceipt receipt, Integer offset, Integer limit);
    
    List<AcReceiptInvoice> find(AcReceipt receipt);
    
    List<AcReceiptAccountCharge> findReceiptAccountCharge(AcReceipt receipt);
    
	List<AcReceiptDebitNote> findReceiptDebitNote(AcReceipt receipt);

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
    
    void addReceiptInvoice(AcReceipt receipt, AcInvoice invoice, AcUser user);
    
    void addReceiptCharge(AcReceipt receipt, AcAccountCharge accountCharge, AcUser user);
    
    BigDecimal sumAppliedAmount(AcReceipt receipt, AcUser user);
    
    BigDecimal sumAmount(AcInvoice invoice, AcReceipt receipt, AcUser user);

	void addReceiptDebitNote(AcReceipt receipt, AcDebitNote debitNote, AcUser currentUser);


    
    

}
