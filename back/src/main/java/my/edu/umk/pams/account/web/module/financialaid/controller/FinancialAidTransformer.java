package my.edu.umk.pams.account.web.module.financialaid.controller;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.web.module.account.controller.AccountTransformer;
import my.edu.umk.pams.account.web.module.billing.controller.BillingTransformer;
import my.edu.umk.pams.account.web.module.core.vo.FlowState;
import my.edu.umk.pams.account.web.module.core.vo.MetaState;
import my.edu.umk.pams.account.web.module.financialaid.vo.Settlement;
import my.edu.umk.pams.account.web.module.financialaid.vo.SettlementItem;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplication;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplicationTask;
import my.edu.umk.pams.account.web.module.identity.controller.IdentityTransformer;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toCollection;

/**
 * @author PAMS
 */
@Component("financialAidTransformer")
public class FinancialAidTransformer {


    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private BillingTransformer billingTransformer;

    @Autowired
    private AccountTransformer accountTransformer;

    @Autowired
    private IdentityTransformer identityTransformer;

    public WaiverApplicationTask toWaiverApplicationTaskVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        AcWaiverApplication application = financialAidService.findWaiverApplicationById((Long) vars.get(AccountConstants.WAIVER_APPLICATION_ID));

        WaiverApplicationTask task = new WaiverApplicationTask();
        task.setId(application.getId());
        task.setTaskId(t.getId());
        task.setReferenceNo(application.getReferenceNo());
        task.setSourceNo(application.getSourceNo());
        task.setDescription(application.getDescription());
        task.setTaskName(t.getName());
        task.setAssignee(task.getAssignee());
        task.setCandidate(task.getCandidate());
        task.setApplication(toWaiverApplicationVo(application));
        task.setFlowState(FlowState.get(application.getFlowdata().getState().ordinal()));
        task.setMetaState(MetaState.get(application.getMetadata().getState().ordinal()));
        return task;
    }

    public WaiverApplication toWaiverApplicationVo(AcWaiverApplication e) {
        WaiverApplication vo = new WaiverApplication();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setDescription(e.getDescription());
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setAcademicSession(accountTransformer.toAcademicSessionVo(e.getSession()));
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public Settlement toSimpleSettlementVo(AcSettlement e) {
        Settlement vo = new Settlement();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        vo.setAcademicSession(accountTransformer.toAcademicSessionVo(e.getSession()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public Settlement toSettlementVo(AcSettlement e) {
        Settlement vo = new Settlement();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public SettlementItem toSettlementItemVo(AcSettlementItem e) {
        // todo(uda): more properties
        SettlementItem vo = new SettlementItem();
        vo.setId(e.getId());
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setInvoice(billingTransformer.toInvoiceVo(e.getInvoice()));
        vo.setBalanceAmount(e.getBalanceAmount());
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public List<WaiverApplicationTask> toWaiverApplicationTaskVos(List<Task> tasks) {
        return tasks.stream()
                .map((task) -> toWaiverApplicationTaskVo(task))
                .collect(toCollection(() -> new ArrayList<WaiverApplicationTask>()));
    }

    public List<Settlement> toSettlementVos(List<AcSettlement> journals) {
        return journals.stream()
                .map((task) -> toSettlementVo(task))
                .collect(toCollection(() -> new ArrayList<Settlement>()));
    }

    public List<SettlementItem> toSettlementItemVos(List<AcSettlementItem> entries) {
        return entries.stream()
                .map((entry) -> toSettlementItemVo(entry))
                .collect(toCollection(() -> new ArrayList<SettlementItem>()));
    }
    public List<WaiverApplication> toWaiverApplicationVos(List<AcWaiverApplication> entries) {
        return entries.stream()
                .map((entry) -> toWaiverApplicationVo(entry))
                .collect(toCollection(() -> new ArrayList<WaiverApplication>()));
    }
}
