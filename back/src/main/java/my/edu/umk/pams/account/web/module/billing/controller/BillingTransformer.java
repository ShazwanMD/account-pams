package my.edu.umk.pams.account.web.module.billing.controller;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.web.module.account.controller.AccountTransformer;
import my.edu.umk.pams.account.web.module.account.vo.AccountChargeType;
import my.edu.umk.pams.account.web.module.billing.vo.*;
import my.edu.umk.pams.account.web.module.common.controller.CommonTransformer;
import my.edu.umk.pams.account.web.module.common.vo.PaymentMethod;
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

    @Autowired
    private BillingTransformer billingTransformer;

    @Autowired
    private AccountTransformer accountTransformer;

    @Autowired
    private CommonTransformer commonTransformer;


    public InvoiceTask toInvoiceTaskVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        AcInvoice invoice = billingService.findInvoiceById((Long) vars.get(AccountConstants.INVOICE_ID));

        InvoiceTask task = new InvoiceTask();
        task.setId(invoice.getId());
        task.setTaskId(t.getId());
        task.setReferenceNo(invoice.getReferenceNo());
        task.setSourceNo(invoice.getSourceNo());
        task.setAccountCode(invoice.getAccount().getCode());
        task.setDescription(invoice.getDescription());
        task.setTotalAmount(invoice.getTotalAmount());
        task.setBalanceAmount(invoice.getBalanceAmount());
        task.setIssuedDate(invoice.getIssuedDate());
        task.setTaskName(t.getName());
        task.setAssignee(task.getAssignee());
        task.setCandidate(task.getCandidate());
        task.setInvoice(toInvoiceVo(invoice));
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
        if (null == e) return null;

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
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public InvoiceItem toInvoiceItemVo(AcInvoiceItem e) {
        InvoiceItem vo = new InvoiceItem();
        vo.setId(e.getId());
        vo.setAmount(e.getAmount());
        vo.setDescription(e.getDescription());
        vo.setDebitAmount(e.getAmount().compareTo(BigDecimal.ZERO) < 0 ? e.getAmount().negate() : null);
        vo.setCreditAmount(e.getAmount().compareTo(BigDecimal.ZERO) > 0 ? e.getAmount() : null);
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        //vo.setTaxCode(commonTransformer.toTaxCodeVo(e.getTaxCode()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }


    public DebitNoteItem toDebitNoteItemVo(AcDebitNoteItem e) {
        DebitNoteItem vo = new DebitNoteItem();
        vo.setId(e.getId());
        vo.setAmount(e.getAmount());
        vo.setDescription(e.getDescription());
        vo.setDebitNoteItemDate(e.getDebitNoteItemDate());
        vo.setDebitAmount(e.getAmount().compareTo(BigDecimal.ZERO) < 0 ? e.getAmount().negate() : null);
        vo.setCreditAmount(e.getAmount().compareTo(BigDecimal.ZERO) > 0 ? e.getAmount() : null);
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public CreditNoteItem toCreditNoteItemVo(AcCreditNoteItem e) {
        CreditNoteItem vo = new CreditNoteItem();
        vo.setId(e.getId());
        vo.setAmount(e.getAmount());
        vo.setDescription(e.getDescription());
        vo.setCreditNoteItemDate(e.getCreditNoteItemDate());
        vo.setCreditAmount(e.getAmount().compareTo(BigDecimal.ZERO) < 0 ? e.getAmount().negate() : null);
        vo.setCreditAmount(e.getAmount().compareTo(BigDecimal.ZERO) > 0 ? e.getAmount() : null);
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public ReceiptTask toReceiptTaskVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        AcReceipt receipt = billingService.findReceiptById((Long) vars.get(AccountConstants.RECEIPT_ID));

        ReceiptTask task = new ReceiptTask();
        task.setId(receipt.getId());
        task.setTaskId(t.getId());
        task.setReferenceNo(receipt.getReferenceNo());
        task.setSourceNo(receipt.getSourceNo());
        task.setDescription(receipt.getDescription());
        task.setTaskName(t.getName());
        task.setAssignee(task.getAssignee());
        task.setCandidate(task.getCandidate());
        task.setReceipt(toReceiptVo(receipt));
        task.setReceivedDate(receipt.getReceivedDate());
        task.setPaymentMethod(PaymentMethod.get(receipt.getPaymentMethod().ordinal()));
        task.setReceiptType(ReceiptType.get(receipt.getReceiptType().ordinal()));
        task.setFlowState(FlowState.get(receipt.getFlowdata().getState().ordinal()));
        task.setMetaState(MetaState.get(receipt.getMetadata().getState().ordinal()));
        return task;
    }

    public Receipt toSimpleReceiptVo(AcReceipt e) {
        Receipt vo = new Receipt();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setReceiptNo(e.getReceiptNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setDescription(e.getDescription());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setReceivedDate(e.getReceivedDate());
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setPaymentMethod(PaymentMethod.get(e.getPaymentMethod().ordinal()));
        vo.setReceiptType(ReceiptType.get(e.getReceiptType().ordinal()));
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }

    public Receipt toReceiptVo(AcReceipt e) {
        Receipt vo = new Receipt();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setDescription(e.getDescription());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setReceivedDate(e.getReceivedDate());
        vo.setPaymentMethod(PaymentMethod.get(e.getPaymentMethod().ordinal()));
        vo.setReceiptType(ReceiptType.get(e.getReceiptType().ordinal()));
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        return vo;
    }
    
    public ReceiptInvoice toReceiptInvoiceVo(AcReceiptInvoice r) {
    	
    	ReceiptInvoice vo = new ReceiptInvoice();
    	vo.setReceipt(billingTransformer.toReceiptVo(r.getReceipt()));
    	vo.setInvoice(billingTransformer.toInvoiceVo(r.getInvoice()));
        return vo;
    }

    public ReceiptItem toReceiptItemVo(AcReceiptItem e) {
        ReceiptItem vo = new ReceiptItem();
        vo.setId(e.getId());
        vo.setDescription(e.getDescription());
        vo.setAdjustedAmount(e.getAdjustedAmount());
        vo.setAppliedAmount(e.getAppliedAmount());
        vo.setDueAmount(e.getDueAmount());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        vo.setInvoice(billingTransformer.toInvoiceVo(e.getInvoice()));
        return vo;
    }


    public DebitNote toDebitNoteVos(AcDebitNote e) {
        DebitNote vo = new DebitNote();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setDebitNoteDate(e.getDebitNoteDate());
        vo.setAccountCode(e.getInvoice().getAccount().getCode());
        vo.setAccountName(e.getInvoice().getAccount().getActor().getName());
        vo.setDescription(e.getDescription());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        return vo;
    }

    public DebitNote toDebitNoteVo(AcDebitNote e) {
        DebitNote vo = new DebitNote();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setDebitNoteDate(e.getDebitNoteDate());
        vo.setDescription(e.getDescription());
        vo.setAccountCode(e.getInvoice().getAccount().getCode());
        vo.setAccountName(e.getInvoice().getAccount().getActor().getName());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        return vo;
    }

    public DebitNoteTask toDebitNoteTaskVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        AcDebitNote debitNote = billingService.findDebitNoteById((Long) vars.get(AccountConstants.DEBIT_NOTE_ID));

        DebitNoteTask task = new DebitNoteTask();
        task.setId(debitNote.getId());
        task.setTaskId(t.getId());
        task.setReferenceNo(debitNote.getReferenceNo());
        task.setSourceNo(debitNote.getSourceNo());
        task.setAccountCode(debitNote.getInvoice().getAccount().getCode());
        task.setAccountName(debitNote.getInvoice().getAccount().getActor().getName());
        task.setDescription(debitNote.getDescription());
        task.setDebitNoteDate(debitNote.getDebitNoteDate());
        task.setTotalAmount(debitNote.getTotalAmount());
        task.setTaskName(t.getName());
        task.setAssignee(task.getAssignee());
        task.setCandidate(task.getCandidate());
        task.setDebitNote(toDebitNoteVo(debitNote));
        task.setFlowState(FlowState.get(debitNote.getFlowdata().getState().ordinal()));
        task.setMetaState(MetaState.get(debitNote.getMetadata().getState().ordinal()));
        task.setChargeCode(accountTransformer.toChargeCodeVo(debitNote.getChargeCode()));
        return task;
    }

    public CreditNote toCreditNoteVos(AcCreditNote e) {
        CreditNote vo = new CreditNote();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setAccountCode(e.getInvoice().getAccount().getCode());
        vo.setAccountName(e.getInvoice().getAccount().getActor().getName());
        vo.setDescription(e.getDescription());
        vo.setCreditNoteDate(e.getCreditNoteDate());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        return vo;
    }

    public CreditNote toCreditNoteVo(AcCreditNote e) {
        CreditNote vo = new CreditNote();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setAccountCode(e.getInvoice().getAccount().getCode());
        vo.setAccountName(e.getInvoice().getAccount().getActor().getName());
        vo.setDescription(e.getDescription());
        vo.setCreditNoteDate(e.getCreditNoteDate());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        return vo;
    }

    public CreditNoteTask toCreditNoteTaskVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        AcCreditNote creditNote = billingService.findCreditNoteById((Long) vars.get(AccountConstants.CREDIT_NOTE_ID));

        CreditNoteTask task = new CreditNoteTask();
        task.setId(creditNote.getId());
        task.setTaskId(t.getId());
        task.setReferenceNo(creditNote.getReferenceNo());
        task.setSourceNo(creditNote.getSourceNo());
        task.setDescription(creditNote.getDescription());
        task.setTaskName(t.getName());
        task.setTotalAmount(creditNote.getTotalAmount());
        task.setAccountCode(creditNote.getInvoice().getAccount().getCode());
        task.setAccountName(creditNote.getInvoice().getAccount().getActor().getName());
        task.setAssignee(task.getAssignee());
        task.setCreditNoteDate(creditNote.getCreditNoteDate());
        task.setCandidate(task.getCandidate());
        task.setCreditNote(toCreditNoteVo(creditNote));
        task.setFlowState(FlowState.get(creditNote.getFlowdata().getState().ordinal()));
        task.setMetaState(MetaState.get(creditNote.getMetadata().getState().ordinal()));
        task.setChargeCode(accountTransformer.toChargeCodeVo(creditNote.getChargeCode()));
        return task;
    }
    
    public AdvancePayment toAdvancePaymentVo(AcAdvancePayment e) {
    	AdvancePayment vo = new AdvancePayment();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        vo.setAmount(e.getAmount());
        vo.setBalanceAmount(e.getBalanceAmount());
        vo.setStatus(e.getStatus());
        vo.setReceipt(billingTransformer.toReceiptVo(e.getReceipt()));
        vo.setKnockoff(billingTransformer.toKnockoffVos(e.getKnockoff()));
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        return vo;
    }
    
    public Knockoff toKnockoffVo(AcKnockoff e) {
    	Knockoff vo = new Knockoff();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setDescription(e.getDescription());
        vo.setAmount(e.getAmount());
        vo.setIssuedDate(e.getIssuedDate());
        vo.setInvoice(billingTransformer.toInvoiceVo(e.getInvoice()));
        vo.setPayments(billingTransformer.toAdvancePaymentVo(e.getPayments()));
        return vo;
    }

    public List<InvoiceTask> toInvoiceTaskVos(List<Task> tasks) {
        return tasks.stream()
                .map((task) -> toInvoiceTaskVo(task))
                .collect(toCollection(() -> new ArrayList<InvoiceTask>()));
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

    public List<DebitNoteItem> toDebitNoteItemVos(List<AcDebitNoteItem> entries) {
        return entries.stream()
                .map((entry) -> toDebitNoteItemVo(entry))
                .collect(toCollection(() -> new ArrayList<DebitNoteItem>()));
    }

    public List<CreditNoteItem> toCreditNoteItemVos(List<AcCreditNoteItem> entries) {
        return entries.stream()
                .map((entry) -> toCreditNoteItemVo(entry))
                .collect(toCollection(() -> new ArrayList<CreditNoteItem>()));
    }

    public List<ReceiptTask> toReceiptTaskVos(List<Task> tasks) {
        return tasks.stream()
                .map((task) -> toReceiptTaskVo(task))
                .collect(toCollection(() -> new ArrayList<ReceiptTask>()));
    }

    public List<Receipt> toReceiptVos(List<AcReceipt> journals) {
        return journals.stream()
                .map((task) -> toReceiptVo(task))
                .collect(toCollection(() -> new ArrayList<Receipt>()));
    }

    public List<ReceiptItem> toReceiptItemVos(List<AcReceiptItem> entries) {
        return entries.stream()
                .map((entry) -> toReceiptItemVo(entry))
                .collect(toCollection(() -> new ArrayList<ReceiptItem>()));
    }

    public List<DebitNoteTask> toDebitNoteTaskVos(List<Task> tasks) {
        return tasks.stream()
                .map((task) -> toDebitNoteTaskVo(task))
                .collect(toCollection(() -> new ArrayList<DebitNoteTask>()));
    }

    public List<CreditNoteTask> toCreditNoteTaskVos(List<Task> tasks) {
        return tasks.stream()
                .map((task) -> toCreditNoteTaskVo(task))
                .collect(toCollection(() -> new ArrayList<CreditNoteTask>()));
    }

    public List<DebitNote> toDebitNoteVos(List<AcDebitNote> journals) {
        return journals.stream()
                .map((task) -> toDebitNoteVos(task))
                .collect(toCollection(() -> new ArrayList<DebitNote>()));
    }

    public List<CreditNote> toCreditNoteVos(List<AcCreditNote> journals) {
        return journals.stream()
                .map((task) -> toCreditNoteVos(task))
                .collect(toCollection(() -> new ArrayList<CreditNote>()));
    }
    
    public List<ReceiptInvoice> toReceiptInvoiceVos(List<AcReceiptInvoice> entries) {
        return entries.stream()
                .map((entry) -> toReceiptInvoiceVo(entry))
                .collect(toCollection(() -> new ArrayList<ReceiptInvoice>()));
    }
    
    public List<AdvancePayment> toAdvancePaymentVos(List<AcAdvancePayment> entries) {
        return entries.stream()
                .map((entry) -> toAdvancePaymentVo(entry))
                .collect(toCollection(() -> new ArrayList<AdvancePayment>()));
    }
    
    public List<Knockoff> toKnockoffVos(List<AcKnockoff> entries) {
        return entries.stream()
                .map((entry) -> toKnockoffVo(entry))
                .collect(toCollection(() -> new ArrayList<Knockoff>()));
    }
}
