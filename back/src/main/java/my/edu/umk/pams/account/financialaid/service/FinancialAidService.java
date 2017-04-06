package my.edu.umk.pams.account.financialaid.service;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import org.activiti.engine.task.Task;

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

    List<AcSettlementItem> findSettlementItems(AcSettlement settlement);

    List<AcSettlementItem> findSettlementItems(AcSettlement settlement, Integer offset, Integer limit);

    Integer countSettlement();

    Integer countSettlement(AcAcademicSession academicSession);

    Integer countSettlementItem(AcSettlement settlement);

    void initSettlement(AcSettlement settlement);

    void executeSettlement(AcSettlement settlement);

    void saveSettlement(AcSettlement settlement);

    void updateSettlement(AcSettlement settlement);

    void addSettlementItem(AcSettlement settlement, AcSettlementItem item);

    void updateSettlementItem(AcSettlement settlement, AcSettlementItem item);

    void deleteSettlementItem(AcSettlement settlement, AcSettlementItem item);


    // ==================================================================================================== //
    // WAIVER APPLICATION
    // ==================================================================================================== //

    // workflow
    AcWaiverApplication findWaiverApplicationByTaskId(String taskId);

    Task findWaiverApplicationTaskByTaskId(String taskId);

    List<Task> findAssignedWaiverApplicationTasks(Integer offset, Integer limit);

    List<Task> findPooledWaiverApplicationTasks(Integer offset, Integer limit);

    void startWaiverApplicationTask(AcWaiverApplication application);

    void updateWaiverApplication(AcWaiverApplication application);

    void cancelWaiverApplication(AcWaiverApplication application);

    // ==================================================================================================== //
    // WAIVER APPLICATION
    // ==================================================================================================== //

    AcWaiverApplication findWaiverApplicationById(Long id);

    AcWaiverApplication findWaiverApplicationByReferenceNo(String referenceNo);

    List<AcWaiverApplication> findWaiverApplications(String filter, Integer offset, Integer limit);

    List<AcWaiverApplication> findWaiverApplications(AcAcademicSession academicSession, Integer offset, Integer limit);

    Integer countWaiverApplication(String filter);

    Integer countWaiverApplication(AcAcademicSession academicSession);
}
