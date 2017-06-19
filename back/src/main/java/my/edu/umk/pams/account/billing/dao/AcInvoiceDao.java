package my.edu.umk.pams.account.billing.dao;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.web.module.util.vo.CovalentDatatableQuery;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcInvoiceDao extends GenericDao<Long, AcInvoice> {

    AcInvoice findByReferenceNo(String referenceNo);

    AcInvoiceItem findItemById(Long id);

    List<AcInvoice> findBySourceNo(String sourceNo);

    List<AcInvoice> findManyInvoiceBySourceNo(String sourceNo);

    List<AcInvoice> find(AcAccount account);

    List<AcInvoice> find(boolean paid, AcAccount account);

    List<AcInvoice> find(String filter, Integer offset, Integer limit);

    List<AcInvoice> find(AcAccount account, Integer offset, Integer limit);

    List<AcInvoice> find(boolean paid, AcAccount account, Integer offset, Integer limit);

    List<AcInvoice> findFullText(CovalentDatatableQuery query);

    List<AcInvoiceItem> findItems(AcInvoice invoice);

    List<AcInvoiceItem> findItems(AcInvoice invoice, Integer offset, Integer limit);

    List<AcInvoiceItem> findSortedItems(AcInvoice invoice);

    List<AcInvoice> findByFlowState(AcFlowState acFlowState);

    List<AcInvoice> findByFlowStates(AcFlowState... acFlowState);

//    List<AcInvoiceTransaction> findTransactions(AcInvoice invoice);
//
//    List<AcInvoiceTransaction> findTransactions(AcInvoice invoice, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcAccount account);

    Integer countItem(AcInvoice invoice);

//    Integer countTransaction(AcInvoice invoice);

    Integer countPaid(AcAccount account);

    Integer countUnpaid(AcAccount account);

    boolean isOverdue(AcAccount account);

    boolean hasInvoice(AcAccount account);

    boolean hasInvoice(boolean paid, AcAccount account);

    boolean hasInvoice(boolean paid, Integer days, AcAccount account);

    // ====================================================================================================
    // CRUDS
    // ====================================================================================================

    void addCharge(AcInvoice invoice, AcAccountCharge charge, AcUser user);

    void updateCharge(AcInvoice invoice, AcAccountCharge charge, AcUser user);

    void removeCharge(AcInvoice invoice, AcAccountCharge charge, AcUser user);

    void addItem(AcInvoice invoice, AcInvoiceItem item, AcUser user);

    void updateItem(AcInvoice invoice, AcInvoiceItem item, AcUser user);

    void removeItem(AcInvoice invoice, AcInvoiceItem item, AcUser user);

    void deleteItem(AcInvoice invoice, AcInvoiceItem item, AcUser user);


//    void addTransaction(AcInvoice invoice, AcInvoiceTransaction transaction, AcUser user);
//
//    void updateTransaction(AcInvoice invoice, AcInvoiceTransaction transaction, AcUser user);
//
//    void removeTransaction(AcInvoice invoice, AcInvoiceTransaction transaction, AcUser user);

}
