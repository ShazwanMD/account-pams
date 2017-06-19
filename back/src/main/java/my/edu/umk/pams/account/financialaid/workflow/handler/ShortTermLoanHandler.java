package my.edu.umk.pams.account.financialaid.workflow.handler;

import static my.edu.umk.pams.account.AccountConstants.SHORT_TERM_LOAN_PROCESS_KEY;
import static my.edu.umk.pams.account.AccountConstants.SHORT_TERM_LOAN_RESOURCE_PATH;
import static my.edu.umk.pams.account.AccountConstants.SHORT_TERM_LOAN_PROCESS_NAME;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import my.edu.umk.pams.account.financialaid.model.AcShortTermLoan;
import my.edu.umk.pams.account.workflow.integration.registry.DocumentHandler;

public class ShortTermLoanHandler implements DocumentHandler<AcShortTermLoan>{
    private static final Logger LOG = LoggerFactory.getLogger(ShortTermLoanHandler.class);

    @Autowired
    protected ProcessEngine processEngine;

    @Autowired
    protected RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

    @Autowired
    protected HistoryService historyService;

    @Autowired
    protected RepositoryService repositoryService;

    @Override
    public String process(AcShortTermLoan shortTermLoan, Map<String, Object> variables) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
        		SHORT_TERM_LOAN_PROCESS_KEY,
                shortTermLoan.getReferenceNo(),
                variables);
        LOG.info("Process started for {} with process instance #{} ", SHORT_TERM_LOAN_PROCESS_KEY, instance.getId());
        return instance.getProcessInstanceId();
    }

    @PostConstruct
    public void deployShortTermLoan() {
        DeploymentBuilder deployment = repositoryService.createDeployment();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        // start only when we don't have one
        long count = query.processDefinitionKey(SHORT_TERM_LOAN_PROCESS_KEY).count();
        if (count < 1) {
            deployment
                    .addClasspathResource(SHORT_TERM_LOAN_RESOURCE_PATH)
                    .name(SHORT_TERM_LOAN_PROCESS_NAME)
                    .deploy();
        }
    }
}
