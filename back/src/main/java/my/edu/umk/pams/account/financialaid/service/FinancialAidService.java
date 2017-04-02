package my.edu.umk.pams.account.financialaid.service;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;

import java.util.List;

/**
 * @author PAMS
 */
public interface FinancialAidService {

    // ==================================================================================================== //
    // SETTLEMENT
    // ==================================================================================================== //

    AcSettlement findSettlementById(Long id);

    AcSettlement findSettlementByReferenceNo(String referenceNo);

    AcSettlementItem findSettlementItemById(Long id);

    List<AcSettlement> findSettlementes(Integer offset, Integer limit);

    List<AcSettlement> findSettlementes(AcAcademicSession academicSession, Integer offset, Integer limit);

    List<AcSettlementItem> findSettlementItems(AcSettlement processBatch, Integer offset, Integer limit);

    Integer countSettlement();

    Integer countSettlement(AcAcademicSession academicSession);

    Integer countSettlementItem(AcSettlement processBatch);

    void initSettlement(AcSettlement settlement);

    void executeSettlement(AcSettlement settlement);

    void saveSettlement(AcSettlement settlement);

    void updateSettlement(AcSettlement settlement);

    void addSettlementItem(AcSettlement settlement, AcSettlementItem item);

    void updateSettlementItem(AcSettlement settlement, AcSettlementItem item);

    void deleteSettlementItem(AcSettlement settlement, AcSettlementItem item);
    
    void saveWaiverApplication(AcWaiverApplication waiverApplication);

}
