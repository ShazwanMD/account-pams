package my.edu.umk.pams.account.web.module.billing.controller;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.web.module.billing.vo.ChargeCode;
import my.edu.umk.pams.account.web.module.billing.vo.Invoice;
import my.edu.umk.pams.account.web.module.billing.vo.InvoiceItem;
import my.edu.umk.pams.account.web.module.billing.vo.InvoiceTask;
import my.edu.umk.pams.account.web.module.core.vo.FlowState;
import my.edu.umk.pams.account.web.module.core.vo.MetaState;
import my.edu.umk.pams.account.web.module.identity.controller.IdentityTransformer;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

/**
 * @author PAMS
 */
@Component("billingTransformer")
public class BillingTransformer {

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private IdentityTransformer identityTransformer;

    public ChargeCode toChargeCodeVo(AcChargeCode e) {
        ChargeCode m = new ChargeCode();
        m.setId(e.getId());
        m.setCode(e.getCode());
        m.setDescription(e.getDescription());
        return m;
    }

    public List<ChargeCode> toChargeCodeVos(List<AcChargeCode> e) {
        List<ChargeCode> vos = e.stream()
                .map((e1) -> toChargeCodeVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public List<InvoiceTask> toInvoiceTaskVos(List<Task> tasks) {
        return tasks.stream()
                .map((task) -> toInvoiceTaskVo(task))
                .collect(toCollection(() -> new ArrayList<InvoiceTask>()));
    }

    public InvoiceTask toInvoiceTaskVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        AcInvoice invoice = billingService.findInvoiceById((Long) vars.get(AccountConstants.INVOICE_ID));

        InvoiceTask task = new InvoiceTask();
        task.setId(invoice.getId());
        task.setTaskId(t.getId());
        task.setReferenceNo(invoice.getReferenceNo());
        task.setSourceNo(invoice.getSourceNo());
        task.setDescription(invoice.getDescription());
        task.setTaskName(t.getName());
        task.setAssignee(task.getAssignee());
        task.setCandidate(task.getCandidate());
//        task.setInvoice(toInvoiceVo(invoice));
        task.setFlowState(FlowState.get(invoice.getFlowdata().getState().ordinal()));
        task.setMetaState(MetaState.get(invoice.getMetadata().getState().ordinal()));
        return task;
    }

    public Invoice toSimpleInvoiceVo(AcInvoice e) {
        Invoice vo = new Invoice();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setInvoiceNo(e.getInvoiceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setDescription(e.getDescription());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public Invoice toInvoiceVo(AcInvoice e) {
        Invoice vo = new Invoice();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setDescription(e.getDescription());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setBalanceAmount(e.getBalanceAmount());
        vo.setPaid(e.isPaid());
        vo.setIssuedDate(e.getIssuedDate());
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public List<Invoice> toInvoiceVos(List<AcInvoice> journals) {
        return journals.stream()
                .map((task) -> toInvoiceVo(task))
                .collect(toCollection(() -> new ArrayList<Invoice>()));
    }

    public List<InvoiceItem> toInvoiceItemVos(List<AcInvoiceItem> entries) {
        return entries.stream()
                .map((entry) -> toInvoiceItemVo(entry))
                .collect(toCollection(() -> new ArrayList<InvoiceItem>()));
    }

    public InvoiceItem toInvoiceItemVo(AcInvoiceItem e) {
        InvoiceItem vo = new InvoiceItem();
        vo.setId(e.getId());
        vo.setAmount(e.getAmount());
        vo.setDescription(e.getDescription());
        vo.setDebitAmount(e.getAmount().compareTo(BigDecimal.ZERO) < 0 ? e.getAmount().negate() : null);
        vo.setCreditAmount(e.getAmount().compareTo(BigDecimal.ZERO) > 0 ? e.getAmount() : null);
        vo.setChargeCode(toChargeCodeVo(e.getChargeCode()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }
}
