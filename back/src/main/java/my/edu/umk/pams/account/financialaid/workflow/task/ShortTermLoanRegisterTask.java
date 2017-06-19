package my.edu.umk.pams.account.financialaid.workflow.task;

import static java.lang.System.currentTimeMillis;
import static my.edu.umk.pams.account.AccountConstants.SHORT_TERM_LOAN_ID;
import static my.edu.umk.pams.account.core.AcFlowState.REGISTERED;
import static org.slf4j.LoggerFactory.getLogger;

import java.sql.Timestamp;

import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.financialaid.model.AcShortTermLoan;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("stl_draft_ST")
public class ShortTermLoanRegisterTask extends BpmnActivityBehavior implements ActivityBehavior {

    private static Logger LOG = getLogger(ShortTermLoanRegisterTask.class);

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
        Long shortTermLoanId = (Long) execution.getVariable(SHORT_TERM_LOAN_ID);
        AcShortTermLoan shortTermLoan = financialAidService.findShortTermLoanById(shortTermLoanId);

        LOG.debug("drafting shortTermLoan {}", shortTermLoan.getReferenceNo());

        // append charge to invoice
        // appending will increase total/balance amount
        // appending will add invoice item

        shortTermLoan.getFlowdata().setState(REGISTERED);
        shortTermLoan.getFlowdata().setDraftedDate(new Timestamp(currentTimeMillis()));
        shortTermLoan.getFlowdata().setDrafterId(securityService.getCurrentUser().getId());
        financialAidService.updateShortTermLoan(shortTermLoan);
    }
}
