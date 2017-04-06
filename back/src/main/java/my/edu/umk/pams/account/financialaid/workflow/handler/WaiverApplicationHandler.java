package my.edu.umk.pams.account.financialaid.workflow.handler;

import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.workflow.integration.registry.DocumentHandler;
import org.activiti.engine.*;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

import static my.edu.umk.pams.account.AccountConstants.*;

/**
 * @author PAMS
 */
@Component
public class WaiverApplicationHandler implements DocumentHandler<AcWaiverApplication> {
    private static final Logger LOG = LoggerFactory.getLogger(WaiverApplicationHandler.class);

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
    public String process(AcWaiverApplication waiverApplication, Map<String, Object> variables) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                WAIVER_APPLICATION_PROCESS_KEY,
                waiverApplication.getReferenceNo(),
                variables);
        LOG.info("Process started for {} with process instance #{} ", WAIVER_APPLICATION_PROCESS_KEY, instance.getId());
        return instance.getProcessInstanceId();
    }

    @PostConstruct
    public void deployWaiverApplication() {
        DeploymentBuilder deployment = repositoryService.createDeployment();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        // start only when we don't have one
        long count = query.processDefinitionKey(WAIVER_APPLICATION_PROCESS_KEY).count();
        if (count < 1) {
            deployment
                    .addClasspathResource(WAIVER_APPLICATION_RESOURCE_PATH)
                    .name(WAIVER_APPLICATION_PROCESS_NAME)
                    .deploy();
        }
    }
}
