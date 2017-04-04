package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcCreditNoteDao extends GenericDao<Long, AcCreditNote> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcCreditNote findByReferenceNo(String referenceNo);

    List<AcCreditNote> find(AcInvoice invoice);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcInvoice invoice);

    boolean hasCreditNote(AcInvoice invoice);

    // ====================================================================================================
    // CRUDS
    // ====================================================================================================


}
