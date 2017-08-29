package my.edu.umk.pams.account.billing.workflow.handler;

import static my.edu.umk.pams.account.AccountConstants.WAIVER_FINANCE_APPLICATION_PROCESS_KEY;
import static my.edu.umk.pams.account.AccountConstants.WAIVER_FINANCE_APPLICATION_PROCESS_NAME;
import static my.edu.umk.pams.account.AccountConstants.WAIVER_FINANCE_APPLICATION_RESOURCE_PATH;

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
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.workflow.integration.registry.DocumentHandler;

/**
 * @author PAMS
 */
@Component
public class WaiverFinanceApplicationHandler implements DocumentHandler<AcWaiverFinanceApplication> {

	private static final Logger LOG = LoggerFactory.getLogger(WaiverFinanceApplicationHandler.class);

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
    public String process(AcWaiverFinanceApplication waiverFinanceApplication, Map<String, Object> variables) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                WAIVER_FINANCE_APPLICATION_PROCESS_KEY,
                waiverFinanceApplication.getReferenceNo(),
                variables);
        LOG.info("Process started for {} with process instance #{} ", WAIVER_FINANCE_APPLICATION_PROCESS_KEY, instance.getId());
        return instance.getProcessInstanceId();
    }

    @PostConstruct
    public void deployWaiverFinanceApplication() {
        DeploymentBuilder deployment = repositoryService.createDeployment();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        // start only when we don't have one
        long count = query.processDefinitionKey(WAIVER_FINANCE_APPLICATION_PROCESS_KEY).count();
        if (count < 1) {
            deployment
                    .addClasspathResource(WAIVER_FINANCE_APPLICATION_RESOURCE_PATH)
                    .name(WAIVER_FINANCE_APPLICATION_PROCESS_NAME)
                    .deploy();
        }
    }
}
