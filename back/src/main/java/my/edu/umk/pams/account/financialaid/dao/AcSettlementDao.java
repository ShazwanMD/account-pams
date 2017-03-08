package my.edu.umk.pams.account.financialaid.dao;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcSettlementDao extends GenericDao<Long, AcSettlement> {

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    AcSettlement findByReferenceNo(String referenceNo);

    AcSettlementItem findItemById(Long id);

    List<AcSettlement> find(AcAcademicSession academicSession, Integer offset, Integer limit);

    List<AcSettlementItem> findItems(AcSettlement batch);

    List<AcSettlementItem> findItems(AcSettlement batch, Integer offset, Integer limit);

    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    Integer count(AcAcademicSession academicSession);

    Integer countItem(AcSettlement batch);

    Integer countItem(String filter, AcSettlement account);

    Integer countSource(AcSettlement batch);

    Integer countSource(String filter, AcSettlement account);

    boolean isSettlementSourceExists(String credentialNo);

    boolean isSettlementItemExists(AcAccount account);

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    void addItem(AcSettlement batch, AcSettlementItem detail, AcUser user);

    void updateItem(AcSettlement batch, AcSettlementItem detail, AcUser user);

    void deleteItem(AcSettlement batch, AcSettlementItem detail, AcUser user);

}
