package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcDebitNoteDao extends GenericDao<Long, AcDebitNote>{

    // ====================================================================================================
    // FINDER
    // ====================================================================================================
    AcDebitNote findByReferenceNo(String referenceNo);

    List<AcDebitNote> find(AcInvoice invoice);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcInvoice invoice);

    boolean hasDebitNote(AcInvoice invoice);

    // ====================================================================================================
    // CRUDS
    // ====================================================================================================
}
