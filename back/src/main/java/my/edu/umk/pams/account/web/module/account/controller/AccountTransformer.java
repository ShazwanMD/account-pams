package my.edu.umk.pams.account.web.module.account.controller;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.web.module.account.vo.*;
import my.edu.umk.pams.account.web.module.identity.controller.IdentityTransformer;
import my.edu.umk.pams.account.workflow.service.WorkflowService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;

/**
 * @author PAMS
 */
@Component("accountTransformer")
public class AccountTransformer {
    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private IdentityTransformer identityTransformer;

    public FeeSchedule toFeeScheduleVo(AcFeeSchedule e) {
    	FeeSchedule m = new FeeSchedule();
        m.setId(e.getId());
        m.setCode(e.getCode());
        m.setDescription(e.getDescription());
        return m;
    }
    
    public ChargeCode toChargeCodeVo(AcChargeCode e) {
        ChargeCode m = new ChargeCode();
        m.setId(e.getId());
        m.setCode(e.getCode());
        m.setDescription(e.getDescription());
        return m;
    }

    public List<FeeSchedule> toFeeScheduleVos(List<AcFeeSchedule> e) {
        List<FeeSchedule> vos = e.stream()
                .map((e1) -> toFeeScheduleVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public List<ChargeCode> toChargeCodeVos(List<AcChargeCode> e) {
        List<ChargeCode> vos = e.stream()
                .map((e1) -> toChargeCodeVo(e1))
                .collect(Collectors.toList());
        return vos;
    }

    public AcademicSession toAcademicSessionVo(AcAcademicSession e) {
        AcademicSession m = new AcademicSession();
        m.setId(e.getId());
        m.setCode(e.getCode());
        m.setDescription(e.getDescription());
        return m;
    }

    public Account toAccountVo(AcAccount e) {
        if (null == e) return null;
        Account m = new Account();
        m.setId(e.getId());
        m.setCode(e.getCode());
        m.setName(e.getActor().getName());
        m.setEmail(e.getActor().getEmail());
        m.setActor(identityTransformer.toActorVo(e.getActor()));
        return m;
    }

    public AccountTransaction toAccountTransactionVo(AcAccountTransaction e) {
        AccountTransaction m = new AccountTransaction();
        m.setId(e.getId());
        m.setSourceNo(e.getSourceNo());
        m.setPostedDate(e.getPostedDate());
        m.setAmount(e.getAmount());
        m.setChargeCode(toChargeCodeVo(e.getChargeCode()));
        m.setAccount(toAccountVo(e.getAccount()));
        m.setSession(toAcademicSessionVo(e.getSession()));
        return m;
    }

    public FeeScheduleItem toFeeScheduleItemVo(AcFeeScheduleItem e) {
        FeeScheduleItem m = new FeeScheduleItem();
        m.setId(e.getId());
        m.setAmount(e.getAmount());
        m.setChargeCode(toChargeCodeVo(e.getChargeCode()));
        return m;
    }

    public List<Account> toAccountVos(List<AcAccount> accounts) {
        return accounts.stream()
                .map((task) -> toAccountVo(task))
                .collect(toCollection(() -> new ArrayList<Account>()));
    }

    public List<AccountTransaction> toAccountTransactionVos(List<AcAccountTransaction> accounts) {
        return accounts.stream()
                .map((accountTx) -> toAccountTransactionVo(accountTx))
                .collect(toCollection(() -> new ArrayList<AccountTransaction>()));
    }
    public List<FeeScheduleItem> toFeeScheduleItemVos(List<AcFeeScheduleItem> items) {
        return items.stream()
                .map((item) -> toFeeScheduleItemVo(item))
                .collect(toCollection(() -> new ArrayList<FeeScheduleItem>()));
    }

    public List<AcademicSession> toAcademicSessionVos(List<AcAcademicSession> academicSessions) {
        return academicSessions.stream()
                .map((task) -> toAcademicSessionVo(task))
                .collect(toCollection(() -> new ArrayList<AcademicSession>()));
    }
}
