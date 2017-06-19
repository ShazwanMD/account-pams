package my.edu.umk.pams.account.financialaid.workflow.task;

import static my.edu.umk.pams.account.AccountConstants.SHORT_TERM_LOAN_ID;
import org.activiti.engine.impl.bpmn.behavior.BpmnActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityBehavior;
import org.activiti.engine.impl.pvm.delegate.ActivityExecution;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.model.AcShortTermLoan;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.security.service.SecurityService;

@Component("STL_complete_ST")
public class ShortTermLoanCompleteTask extends BpmnActivityBehavior implements ActivityBehavior {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ShortTermLoanCompleteTask.class);

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private SecurityService securityService;

    public void execute(ActivityExecution execution) throws Exception {
    	Long shortTermLoanId = (Long) execution.getVariable(SHORT_TERM_LOAN_ID);
    	 AcShortTermLoan shortTermLoan = financialAidService.findShortTermLoanById(shortTermLoanId);

        LOG.debug("completing application {}", shortTermLoan.getReferenceNo());

        shortTermLoan.getFlowdata().setState(AcFlowState.COMPLETED);
        financialAidService.updateShortTermLoanTask(shortTermLoan);
    }
}
