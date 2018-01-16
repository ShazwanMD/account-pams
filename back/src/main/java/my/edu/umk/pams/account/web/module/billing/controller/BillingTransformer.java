package my.edu.umk.pams.account.web.module.billing.controller;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.billing.model.*;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.web.module.account.controller.AccountTransformer;
import my.edu.umk.pams.account.web.module.account.vo.AccountChargeType;
import my.edu.umk.pams.account.web.module.account.vo.ChargeCode;
import my.edu.umk.pams.account.web.module.billing.vo.*;
import my.edu.umk.pams.account.web.module.common.controller.CommonTransformer;
import my.edu.umk.pams.account.web.module.common.vo.PaymentMethod;
import my.edu.umk.pams.account.web.module.core.vo.FlowState;
import my.edu.umk.pams.account.web.module.core.vo.MetaState;
import my.edu.umk.pams.account.web.module.financialaid.vo.GraduateCenterType;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplication;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplicationTask;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplicationType;
import my.edu.umk.pams.account.web.module.identity.controller.IdentityTransformer;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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
        task.setAccountName(invoice.getAccount().getActor().getName());
        task.setDescription(invoice.getDescription());
        task.setTotalAmount(invoice.getTotalAmount());
        task.setBalanceAmount(invoice.getBalanceAmount());
        task.setIssuedDate(invoice.getIssuedDate());
        task.setTaskName(t.getName());
        task.setAssignee(task.getAssignee());
        task.setCandidate(task.getCandidate());
        task.setInvoice(toInvoiceVo(invoice));
        invoice.getFlowdata().getDrafterId();
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
        commonTransformer.decorateMeta(e,vo);
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
        commonTransformer.decorateMeta(e,vo);
        return vo;
    }

    public InvoiceItem toInvoiceItemVo(AcInvoiceItem e) {
        InvoiceItem vo = new InvoiceItem();
        vo.setId(e.getId());
        vo.setAmount(e.getAmount());
        vo.setDescription(e.getDescription());
        vo.setDebitAmount(e.getAmount().compareTo(BigDecimal.ZERO) < 0 ? e.getAmount().negate() : null);
        vo.setCreditAmount(e.getAmount().compareTo(BigDecimal.ZERO) > 0 ? e.getAmount() : null);
        vo.setTaxAmount(e.getTaxAmount());
        vo.setNetAmount(e.getNetAmount());
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        vo.setBalanceAmount(e.getBalanceAmount());
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
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
        vo.setBalanceAmount(e.getBalanceAmount());
        vo.setPaid(e.getPaid());
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        commonTransformer.decorateMeta(e,vo);
        return vo;
    }

    public DebitNote toDebitNoteVo(AcDebitNote e) {
        DebitNote vo = new DebitNote();
        if(null == e) return null;
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setDebitNoteDate(e.getDebitNoteDate());
        vo.setDescription(e.getDescription());
        vo.setAccountCode(e.getInvoice().getAccount().getCode());
        vo.setAccountName(e.getInvoice().getAccount().getActor().getName());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setBalanceAmount(e.getBalanceAmount());
        vo.setPaid(e.getPaid());
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        commonTransformer.decorateMeta(e,vo);
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
        return task;
    }
    
    public DebitNoteItem toDebitNoteItemVo(AcDebitNoteItem e) {
        DebitNoteItem vo = new DebitNoteItem();
        vo.setId(e.getId());
        vo.setAmount(e.getAmount());
        vo.setDescription(e.getDescription());
        vo.setDebitNoteItemDate(e.getDebitNoteItemDate());
        vo.setBalanceAmount(e.getBalanceAmount());
        vo.setChargeCode(accountTransformer.toChargeCodeVo(e.getChargeCode()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
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
        vo.setInvoice(billingTransformer.toInvoiceVo(e.getInvoice()));
        commonTransformer.decorateMeta(e,vo);
        return vo;
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
        vo.setInvoice(billingTransformer.toInvoiceVo(e.getInvoice()));
        commonTransformer.decorateMeta(e,vo);
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
        task.setInvoice(task.getInvoice());
        return task;
    }

    public CreditNoteItem toCreditNoteItemVo(AcCreditNoteItem e) {
        CreditNoteItem vo = new CreditNoteItem();
        vo.setId(e.getId());
        vo.setAmount(e.getAmount());
        vo.setDescription(e.getDescription());
        vo.setCreditNoteItemDate(e.getCreditNoteItemDate());
        vo.setBalanceAmount(e.getBalanceAmount());
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
        task.setTotalReceived(receipt.getTotalReceived());
        task.setTotalPayment(receipt.getTotalPayment());
        task.setAccount(accountTransformer.toAccountVo(receipt.getAccount()));
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
        commonTransformer.decorateMeta(e,vo);
        vo.setTotalPayment(e.getTotalPayment());
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
        vo.setTotalReceived(e.getTotalReceived());
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setReceivedDate(e.getReceivedDate());
        vo.setPaymentMethod(PaymentMethod.get(e.getPaymentMethod().ordinal()));
        vo.setReceiptType(ReceiptType.get(e.getReceiptType().ordinal()));
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        commonTransformer.decorateMeta(e,vo);
        vo.setTotalPayment(e.getTotalPayment());
        return vo;
    }
    
    public ReceiptInvoice toReceiptInvoiceVo(AcReceiptInvoice r) {
    	
    	ReceiptInvoice vo = new ReceiptInvoice();
    	vo.setId(r.getId());
    	vo.setReceipt(billingTransformer.toReceiptVo(r.getReceipt()));
    	vo.setInvoice(billingTransformer.toInvoiceVo(r.getInvoice()));
        return vo;
    }

    public ReceiptAccountCharge toReceiptAccountChargeVo(AcReceiptAccountCharge r) {
    	
    	ReceiptAccountCharge vo = new ReceiptAccountCharge();
    	vo.setId(r.getId());
    	vo.setReceipt(billingTransformer.toReceiptVo(r.getReceipt()));
    	vo.setAccountCharge(accountTransformer.toAccountChargeVo(r.getAccountCharge()));
        return vo;
    }
    
    public ReceiptDebitNote toReceiptDebitNoteVo(AcReceiptDebitNote r) {
    	
    	ReceiptDebitNote vo = new ReceiptDebitNote();
    	vo.setId(r.getId());
    	vo.setReceipt(billingTransformer.toReceiptVo(r.getReceipt()));
    	vo.setDebitNote(billingTransformer.toDebitNoteVo(r.getDebitNote()));
        return vo;
    }
    
    public ReceiptItem toReceiptItemVo(AcReceiptItem e) {
    	if(null == e) return null;
    	
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
        vo.setAccountCharge(accountTransformer.toAccountChargeVo(e.getAccountCharge()));
     //   vo.setReceiptItemType(ReceiptItemType.get(e.getReceiptItemType().ordinal()));
        //m.setInvoiced(null != e.getInvoice());
        vo.setDebitNote(billingTransformer.toDebitNoteVo(e.getDebitNote()));
        return vo;
    }

    
    
    public ReceiptAccountChargeItem toReceiptAccountChargeItemVo(AcReceiptAccountChargeItem e) {
    	ReceiptAccountChargeItem vo = new ReceiptAccountChargeItem();
        vo.setId(e.getId());
        vo.setDescription(e.getDescription());
        vo.setAdjustedAmount(e.getAdjustedAmount());
        vo.setAppliedAmount(e.getAppliedAmount());
        vo.setDueAmount(e.getDueAmount());
        vo.setTotalAmount(e.getTotalAmount());
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        vo.setAccountCharge(accountTransformer.toAccountChargeVo(e.getAccountCharge()));
        return vo;
    }
    
    public AdvancePayment toAdvancePaymentVo(AcAdvancePayment e) {
    	AdvancePayment vo = new AdvancePayment();
    	vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setDescription(e.getDescription());
        vo.setAmount(e.getAmount());
        vo.setBalanceAmount(e.getBalanceAmount());
        vo.setStatus(e.getStatus());
        //vo.setReceipt(billingTransformer.toReceiptVo(e.getReceipt()));
        vo.setReceipted(null != e.getReceipt());
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
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
        vo.setTotalAmount(e.getTotalAmount());
        vo.setPayments(billingTransformer.toAdvancePaymentVo(e.getPayments()));
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        commonTransformer.decorateMeta(e,vo);
        return vo;
    }
    
    public KnockoffInvoice toKnockoffInvoiceVo(AcKnockoffInvoice r) {
    	
    	KnockoffInvoice vo = new KnockoffInvoice();
    	vo.setId(r.getId());
    	vo.setInvoice(billingTransformer.toInvoiceVo(r.getInvoice()));
    	vo.setKnockoff(billingTransformer.toKnockoffVo(r.getKnockoff()));
        return vo;
    }
    
    public KnockoffAccountCharge toKnockoffAccountChargeVo(AcKnockoffAccountCharge r) {
    	
    	KnockoffAccountCharge vo = new KnockoffAccountCharge();
    	vo.setId(r.getId());
    	vo.setAccountCharge(accountTransformer.toAccountChargeVo(r.getAccountCharge()));
    	vo.setKnockoff(billingTransformer.toKnockoffVo(r.getKnockoff()));
        return vo;
    }
    
    public KnockoffDebitNote toKnockoffDebitNoteVo(AcKnockoffDebitNote r) {
    	
    	KnockoffDebitNote vo = new KnockoffDebitNote();
    	vo.setId(r.getId());
    	vo.setKnockoff(billingTransformer.toKnockoffVo(r.getKnockoff()));
    	vo.setDebitNote(billingTransformer.toDebitNoteVo(r.getDebitNote()));
        return vo;
    }
    
    public RefundPayment toRefundPaymentVo(AcRefundPayment e) {
    	RefundPayment vo = new RefundPayment();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setAuditNo(e.getAuditNo());
        vo.setVoucherNo(e.getVoucherNo());
        vo.setDescription(e.getDescription());
        vo.setAmount(e.getAmount());
        vo.setIssuedDate(e.getIssuedDate());
        vo.setPayments(billingTransformer.toAdvancePaymentVo(e.getPayments()));
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        vo.setApprovedDate(e.getFlowdata().getUpperApprovedDate());
        vo.setApprovedId(e.getFlowdata().getUpperApproverId());
        commonTransformer.decorateMeta(e,vo);
        return vo;
    }
    
    public KnockoffTask toKnockoffTaskVo(Task t) {
    	Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        AcKnockoff knockoff = billingService.findKnockoffById((Long) vars.get(AccountConstants.KNOCKOFF_ID));
       
        KnockoffTask task = new KnockoffTask();
        task.setId(knockoff.getId());
        task.setIssuedDate(knockoff.getIssuedDate());
        task.setTotalAmount(knockoff.getTotalAmount());
        task.setKnockoff(toKnockoffVo(knockoff));
        task.setTaskId(t.getId());
        task.setReceivedDate(task.getReceivedDate());
        task.setReferenceNo(knockoff.getReferenceNo());
        task.setSourceNo(knockoff.getSourceNo());
        task.setTaskName(t.getName());
        task.setTotalAmount(knockoff.getAmount());
        task.setCandidate(task.getCandidate());
        task.setAssignee(task.getAssignee());
        task.setDescription(knockoff.getDescription());
        task.setPayments(task.getPayments());
        task.setFlowState(FlowState.get(knockoff.getFlowdata().getState().ordinal()));
        task.setMetaState(MetaState.get(knockoff.getMetadata().getState().ordinal()));
        return task;

    }
    
    public KnockoffItem toKnockoffItemVo(AcKnockoffItem r) {
    	if(null == r) return null;
    	KnockoffItem vo = new KnockoffItem();
    	vo.setId(r.getId());
    	vo.setAppliedAmount(r.getAppliedAmount());
    	vo.setChargeCode(accountTransformer.toChargeCodeVo(r.getChargeCode()));
    	vo.setDescription(r.getDescription());
    	vo.setDueAmount(r.getDueAmount());
    	vo.setTotalAmount(r.getTotalAmount());
    	vo.setKnockoff(billingTransformer.toKnockoffVo(r.getKnockoff()));
    	vo.setInvoice(billingTransformer.toInvoiceVo(r.getInvoice()));
    	vo.setAccountCharge(accountTransformer.toAccountChargeVo(r.getAccountCharge()));
    	vo.setDebitNote(billingTransformer.toDebitNoteVo(r.getDebitNote()));
    	vo.setMetaState(MetaState.get(r.getMetadata().getState().ordinal()));
        return vo;
    }
    
    
    public WaiverFinanceApplicationTask toWaiverFinanceApplicationTaskVo(Task t) {
        Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        AcWaiverFinanceApplication application = billingService.findWaiverFinanceApplicationById((Long) vars.get(AccountConstants.WAIVER_FINANCE_APPLICATION_ID));

        WaiverFinanceApplicationTask task = new WaiverFinanceApplicationTask();
        task.setId(application.getId());
        task.setTaskId(t.getId());
        task.setReferenceNo(application.getReferenceNo());
        task.setSourceNo(application.getSourceNo());
        task.setDescription(application.getDescription());
        task.setTaskName(t.getName());
        task.setAssignee(task.getAssignee());
        task.setCandidate(task.getCandidate());
        task.setApplication(toWaiverFinanceApplicationVo(application));
        task.setAccount(accountTransformer.toAccountVo(application.getAccount()));
        task.setFlowState(FlowState.get(application.getFlowdata().getState().ordinal()));
        task.setMetaState(MetaState.get(application.getMetadata().getState().ordinal()));
        task.setWaiverType(WaiverApplicationType.get(application.getWaiverType().ordinal()));
        task.setGraduateCenterType(GraduateCenterType.get(application.getGraduateCenterType().ordinal()));
        return task;
    }

    public WaiverFinanceApplication toWaiverFinanceApplicationVo(AcWaiverFinanceApplication e) {
        WaiverFinanceApplication vo = new WaiverFinanceApplication();
        vo.setId(e.getId());
        vo.setReferenceNo(e.getReferenceNo());
        vo.setSourceNo(e.getSourceNo());
        vo.setDescription(e.getDescription());
        vo.setGracedAmount(e.getGracedAmount());
        vo.setWaivedAmount(e.getWaivedAmount());
        vo.setBalance(e.getWaivedAmount());
        vo.setEffectiveBalance(e.getEffectiveBalance());
        vo.setAccount(accountTransformer.toAccountVo(e.getAccount()));
        vo.setAcademicSession(accountTransformer.toAcademicSessionVo(e.getSession()));
        vo.setFlowState(FlowState.get(e.getFlowdata().getState().ordinal()));
        vo.setMetaState(MetaState.get(e.getMetadata().getState().ordinal()));
        vo.setGraduateCenterType(GraduateCenterType.get(e.getGraduateCenterType().ordinal()));
        vo.setWaiverType(WaiverApplicationType.get(e.getWaiverType().ordinal()));
        commonTransformer.decorateMeta(e,vo);
        return vo;
    }
    
    public WaiverInvoice toWaiverInvoiceVo(AcWaiverInvoice r) {
    	
    	WaiverInvoice vo = new WaiverInvoice();
    	vo.setId(r.getId());
    	vo.setWaiverFinanceApplication(billingTransformer.toWaiverFinanceApplicationVo(r.getWaiverFinanceApplication()));
    	vo.setInvoice(billingTransformer.toInvoiceVo(r.getInvoice()));
        return vo;
    }
    
    public WaiverAccountCharge toWaiverAccountChargeVo(AcWaiverAccountCharge r) {
    	
    	WaiverAccountCharge vo = new WaiverAccountCharge();
    	vo.setId(r.getId());
    	vo.setWaiverFinanceApplication(billingTransformer.toWaiverFinanceApplicationVo(r.getWaiverFinanceApplication()));
    	vo.setAccountCharge(accountTransformer.toAccountChargeVo(r.getAccountCharge()));
        return vo;
    }
    
    public WaiverDebitNote toWaiverDebitNoteVo(AcWaiverDebitNote r) {
    	
    	WaiverDebitNote vo = new WaiverDebitNote();
    	vo.setId(r.getId());
    	vo.setWaiverFinanceApplication(billingTransformer.toWaiverFinanceApplicationVo(r.getWaiverFinanceApplication()));
    	vo.setDebitNote(billingTransformer.toDebitNoteVo(r.getDebitNote()));
        return vo;
    }
    
    public WaiverItem toWaiverItemVo(AcWaiverItem r) {
    	if(null == r) return null;
    	WaiverItem vo = new WaiverItem();
    	vo.setId(r.getId());
    	vo.setAppliedAmount(r.getAppliedAmount());
    	vo.setChargeCode(accountTransformer.toChargeCodeVo(r.getChargeCode()));
    	vo.setDescription(r.getDescription());
    	vo.setDueAmount(r.getDueAmount());
    	vo.setTotalAmount(r.getTotalAmount());
    	vo.setWaiverFinanceApplication(billingTransformer.toWaiverFinanceApplicationVo(r.getWaiverFinanceApplication()));
    	vo.setInvoice(billingTransformer.toInvoiceVo(r.getInvoice()));
    	vo.setAccountCharge(accountTransformer.toAccountChargeVo(r.getAccountCharge()));
    	vo.setDebitNote(billingTransformer.toDebitNoteVo(r.getDebitNote()));
    	vo.setMetaState(MetaState.get(r.getMetadata().getState().ordinal()));
        return vo;
        

    }

    public RefundPaymentTask toRefundPaymentTaskVo(Task t) {
    	Map<String, Object> vars = workflowService.getVariables(t.getExecutionId());
        AcRefundPayment refundPayment = billingService.findRefundPaymentById((Long) vars.get(AccountConstants.REFUND_ID));
       
        RefundPaymentTask task = new RefundPaymentTask();
        task.setId(refundPayment.getId());
        task.setTaskId(t.getId());
        task.setReferenceNo(refundPayment.getReferenceNo());
        task.setSourceNo(refundPayment.getSourceNo());
        task.setDescription(refundPayment.getDescription());
        task.setTaskName(t.getName());
        task.setAmount(refundPayment.getAmount());
        task.setPaymentNo(refundPayment.getPayments().getReferenceNo());
        task.setAssignee(task.getAssignee());
        task.setIssuedDate(refundPayment.getIssuedDate());
        task.setCandidate(task.getCandidate());
        task.setRefundPayment(toRefundPaymentVo(refundPayment));
        task.setFlowState(FlowState.get(refundPayment.getFlowdata().getState().ordinal()));
        task.setMetaState(MetaState.get(refundPayment.getMetadata().getState().ordinal()));
        return task;
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
    
    public List<ReceiptAccountChargeItem> toReceiptAccountChargeItemVos(List<AcReceiptAccountChargeItem> entries) {
        return entries.stream()
                .map((entry) -> toReceiptAccountChargeItemVo(entry))
                .collect(toCollection(() -> new ArrayList<ReceiptAccountChargeItem>()));
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
    
    public List<ReceiptDebitNote> toReceiptDebitNoteVos(List<AcReceiptDebitNote> entries) {
        return entries.stream()
                .map((entry) -> toReceiptDebitNoteVo(entry))
                .collect(toCollection(() -> new ArrayList<ReceiptDebitNote>()));
    }
    
    public List<ReceiptAccountCharge> toReceiptAccountChargeVos(List<AcReceiptAccountCharge> entries) {
        return entries.stream()
                .map((entry) -> toReceiptAccountChargeVo(entry))
                .collect(toCollection(() -> new ArrayList<ReceiptAccountCharge>()));
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
    
    public List<KnockoffTask> toKnockoffTaskVos(List<Task> tasks) {
        return tasks.stream()
                .map((task) -> toKnockoffTaskVo(task))
                .collect(toCollection(() -> new ArrayList<KnockoffTask>()));
    }
    

    public List<RefundPayment> toRefundPaymentVos(List<AcRefundPayment> entries) {
        return entries.stream()
                .map((entry) -> toRefundPaymentVo(entry))
                .collect(toCollection(() -> new ArrayList<RefundPayment>()));
    }
    
    public List<RefundPaymentTask> toRefundPaymentTaskVos(List<Task> tasks) {
        return tasks.stream()
                .map((task) -> toRefundPaymentTaskVo(task))
                .collect(toCollection(() -> new ArrayList<RefundPaymentTask>()));
    }
    

    public List<WaiverFinanceApplicationTask> toWaiverFinanceApplicationTaskVos(List<Task> tasks) {
        return tasks.stream()
                .map((task) -> toWaiverFinanceApplicationTaskVo(task))
                .collect(toCollection(() -> new ArrayList<WaiverFinanceApplicationTask>()));
    }
    
    public List<WaiverFinanceApplication> toWaiverFinanceApplicationVos(List<AcWaiverFinanceApplication> entries) {
        return entries.stream()
                .map((entry) -> toWaiverFinanceApplicationVo(entry))
                .collect(toCollection(() -> new ArrayList<WaiverFinanceApplication>()));
    }
    
    public List<WaiverInvoice> toWaiverInvoiceVos(List<AcWaiverInvoice> entries) {
        return entries.stream()
                .map((entry) -> toWaiverInvoiceVo(entry))
                .collect(toCollection(() -> new ArrayList<WaiverInvoice>()));
    }
    
    public List<WaiverAccountCharge> toWaiverAccountChargeVos(List<AcWaiverAccountCharge> entries) {
        return entries.stream()
                .map((entry) -> toWaiverAccountChargeVo(entry))
                .collect(toCollection(() -> new ArrayList<WaiverAccountCharge>()));
    }
    
    public List<WaiverDebitNote> toWaiverDebitNoteVos(List<AcWaiverDebitNote> entries) {
        return entries.stream()
                .map((entry) -> toWaiverDebitNoteVo(entry))
                .collect(toCollection(() -> new ArrayList<WaiverDebitNote>()));
    }
    
    public List<WaiverItem> toWaiverItemVos(List<AcWaiverItem> entries) {
        return entries.stream()
                .map((entry) -> toWaiverItemVo(entry))
                .collect(toCollection(() -> new ArrayList<WaiverItem>()));
    }
    
    public List<KnockoffInvoice> toKnockoffInvoiceVos(List<AcKnockoffInvoice> entries) {
        return entries.stream()
                .map((entry) -> toKnockoffInvoiceVo(entry))
                .collect(toCollection(() -> new ArrayList<KnockoffInvoice>()));
    }

    public List<KnockoffAccountCharge> toKnockoffAccountChargeVos(List<AcKnockoffAccountCharge> entries) {
        return entries.stream()
                .map((entry) -> toKnockoffAccountChargeVo(entry))
                .collect(toCollection(() -> new ArrayList<KnockoffAccountCharge>()));
    }
    
    public List<KnockoffItem> toKnockoffItemVos(List<AcKnockoffItem> entries) {
        return entries.stream()
                .map((entry) -> toKnockoffItemVo(entry))
                .collect(toCollection(() -> new ArrayList<KnockoffItem>()));
    }

    public List<KnockoffDebitNote> toKnockoffDebitNoteVos(List<AcKnockoffDebitNote> entries) {
        return entries.stream()
                .map((entry) -> toKnockoffDebitNoteVo(entry))
                .collect(toCollection(() -> new ArrayList<KnockoffDebitNote>()));
    }
}
