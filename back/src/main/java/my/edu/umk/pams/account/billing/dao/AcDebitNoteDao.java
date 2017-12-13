package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcDebitNoteDao extends GenericDao<Long, AcDebitNote> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcDebitNote findByReferenceNo(String referenceNo);

    AcDebitNoteItem findItemById(Long id);

    List<AcDebitNote> find(AcInvoice invoice);

    List<AcDebitNote> findByFlowState(AcFlowState flowState);

    List<AcDebitNote> findByFlowStates(AcFlowState... flowStates);

    List<AcDebitNoteItem> findItems(AcDebitNote debitNote);

    List<AcDebitNoteItem> findItems(AcDebitNote debitNote, Integer offset, Integer limit);
    
	List<AcDebitNote> find(boolean paid, AcAccount account, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcInvoice invoice);

    Integer countItem(AcDebitNote debitNote);

    boolean hasDebitNote(AcInvoice invoice);

    // ====================================================================================================
    // CRUDS
    // ====================================================================================================

    void addItem(AcDebitNote debitNote, AcDebitNoteItem item, AcUser user);

    void updateItem(AcDebitNote debitNote, AcDebitNoteItem item, AcUser user);

    void removeItem(AcDebitNote debitNote, AcDebitNoteItem item, AcUser user);

    void deleteItem(AcDebitNote debitNote, AcDebitNoteItem item, AcUser user);

    BigDecimal sumTotalAmount(AcDebitNote debitNote, AcUser user);
    
    BigDecimal sumBalanceAmount(AcDebitNote debitNote, AcUser user);


}
