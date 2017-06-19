package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcCreditNoteDao extends GenericDao<Long, AcCreditNote> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcCreditNote findByReferenceNo(String referenceNo);

    AcCreditNoteItem findItemById(Long id);

    List<AcCreditNote> find(AcInvoice invoice);

    List<AcCreditNote> findByFlowState(AcFlowState flowState);

    List<AcCreditNote> findByFlowStates(AcFlowState... flowStates);

    List<AcCreditNoteItem> findItems(AcCreditNote creditNote);

    List<AcCreditNoteItem> findItems(AcCreditNote creditNote, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcInvoice invoice);

    Integer countItem(AcCreditNote creditNote);

    boolean hasCreditNote(AcInvoice invoice);

    // ====================================================================================================
    // CRUDS
    // ====================================================================================================

    void addItem(AcCreditNote creditNote, AcCreditNoteItem item, AcUser user);

    void updateItem(AcCreditNote creditNote, AcCreditNoteItem item, AcUser user);

    void removeItem(AcCreditNote creditNote, AcCreditNoteItem item, AcUser user);

    void deleteItem(AcCreditNote creditNote, AcCreditNoteItem item, AcUser user);


}
