package my.edu.umk.pams.account.financialaid.workflow.task;

import static my.edu.umk.pams.account.AccountConstants.SHORT_TERM_LOAN_ID;

import java.sql.Timestamp;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.account.model.AcAccountSTL;
import my.edu.umk.pams.account.account.model.AcAccountSTLImpl;
import my.edu.umk.pams.account.account.model.AcAccountWaiver;
import my.edu.umk.pams.account.account.model.AcAccountWaiverImpl;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.model.AcShortTermLoan;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("stl_approve_ST")
public class ShortTermLoanApproveTask extends BpmnActivityBehavior
implements ActivityBehavior {

    private static final Logger LOG = LoggerFactory.getLogger(ShortTermLoanApproveTask.class);

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long shortTermLoanId = (Long) execution.getVariable(SHORT_TERM_LOAN_ID);
        AcShortTermLoan shortTermLoan = financialAidService.findShortTermLoanById(shortTermLoanId);
        LOG.debug("approving application {}", shortTermLoan.getReferenceNo());

        // update flow state
        shortTermLoan.getFlowdata().setState(AcFlowState.APPROVED);
        shortTermLoan.getFlowdata().setApprovedDate(new Timestamp(System.currentTimeMillis()));
        shortTermLoan.getFlowdata().setApproverId(securityService.getCurrentUser().getId());
        financialAidService.updateShortTermLoan(shortTermLoan);

        // setup account 
        AcAccountSTL stl = new AcAccountSTLImpl();
        stl.setSourceNo(shortTermLoan.getReferenceNo());
        stl.setAmount(shortTermLoan.getAmount());
        stl.setSession(shortTermLoan.getSession());
        stl.setAccount(shortTermLoan.getAccount());

        // save 
        accountService.addShortTermLoan(shortTermLoan.getAccount(), shortTermLoan.getSession(), stl);
    }
}
