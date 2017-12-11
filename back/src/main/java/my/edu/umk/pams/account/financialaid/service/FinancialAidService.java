package my.edu.umk.pams.account.financialaid.service;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.model.AcGraduateCenterType;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcShortTermLoan;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.identity.model.AcSponsor;
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

    List<AcSettlement> findSettlements(Integer offset, Integer limit);

    List<AcSettlement> findSettlements(AcAcademicSession academicSession, Integer offset, Integer limit);

    List<AcSettlementItem> findSettlementItems(AcSettlement settlement);

    List<AcSettlementItem> findSettlementItems(AcSettlement settlement, Integer offset, Integer limit);

    Integer countSettlement();

    Integer countSettlement(AcAcademicSession academicSession);

    Integer countSettlementItem(AcSettlement settlement);

    @Deprecated
    String initSettlement(AcSettlement settlement);

    String initSettlementByFacultyCode(AcSettlement settlement, AcFacultyCode facultyCode);

    String initSettlementBySponsor(AcSettlement settlement, AcSponsor sponsor);

    String initSettlementByCohortCode(AcSettlement settlement, AcCohortCode cohortCode);

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

    String startWaiverApplicationTask(AcWaiverApplication application);

    void updateWaiverApplication(AcWaiverApplication application);

    void cancelWaiverApplication(AcWaiverApplication application);

    // ==================================================================================================== //
    // WAIVER APPLICATION
    // ==================================================================================================== //

    AcWaiverApplication findWaiverApplicationById(Long id);

    AcWaiverApplication findWaiverApplicationByReferenceNo(String referenceNo);

    List<AcWaiverApplication> findWaiverApplicationsByFlowState(AcFlowState acFlowState);

	List<AcWaiverApplication> findWaiverApplicationsByFlowStates(AcGraduateCenterType graduateCenterType,
			AcFlowState... acFlowState);

    List<AcWaiverApplication> findWaiverApplications(String filter, Integer offset, Integer limit);

    List<AcWaiverApplication> findWaiverApplications(AcAcademicSession academicSession, Integer offset, Integer limit);

    Integer countWaiverApplication(String filter);

    Integer countWaiverApplication(AcAcademicSession academicSession);

    // ==================================================================================================== //
    // SHORT TERM LOAN
    // ==================================================================================================== //

    // workflow
    AcShortTermLoan findShortTermLoanByTaskId(String taskId);

    Task findShortTermLoanTaskByTaskId(String taskId);

    List<Task> findAssignedShortTermLoanTasks(Integer offset, Integer limit);

    List<Task> findPooledShortTermLoanTasks(Integer offset, Integer limit);

    String startShortTermLoanTask(AcShortTermLoan shortTermLoan);

    void updateShortTermLoanTask(AcShortTermLoan shortTermLoan);

    void cancelShortTermLoan(AcShortTermLoan shortTermLoan);

    // ==================================================================================================== //
    // SHORT TERM LOAN
    // ==================================================================================================== //

    AcShortTermLoan findShortTermLoanById(Long id);

    AcShortTermLoan findShortTermLoanByReferenceNo(String referenceNo);

    List<AcShortTermLoan> findShortTermLoans(AcAcademicSession academicSession, Integer offset, Integer limit);

    void addShortTermLoan(AcShortTermLoan shortTermLoan);

    void updateShortTermLoan(AcShortTermLoan shortTermLoan);

    void deleteShortTermLoan(AcShortTermLoan shortTermLoan);


}
