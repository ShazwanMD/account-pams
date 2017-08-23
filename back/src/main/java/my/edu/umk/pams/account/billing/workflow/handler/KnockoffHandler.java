package my.edu.umk.pams.account.billing.workflow.handler;

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
import static my.edu.umk.pams.account.AccountConstants.*;
import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.workflow.integration.registry.DocumentHandler;

public class KnockoffHandler implements DocumentHandler<AcKnockoff> {

    private static final Logger LOG = LoggerFactory.getLogger(KnockoffHandler.class);

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
    public String process(AcKnockoff knockoff, Map<String, Object> variables) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(KNOCKOFF_PROCESS_KEY,
                knockoff.getReferenceNo(),
                variables);
        LOG.info("Process started for {} with process instance #{} ", KNOCKOFF_PROCESS_KEY, instance.getId());
        return instance.getProcessInstanceId();
    }

    @PostConstruct
    public void deployKnockoff() {
        DeploymentBuilder deployment = repositoryService.createDeployment();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        // start only when we don't have one
        long count = query.processDefinitionKey(KNOCKOFF_PROCESS_KEY).count();
        if (count < 1) {
            deployment
                    .addClasspathResource(KNOCKOFF_RESOURCE_PATH)
                    .name(KNOCKOFF_PROCESS_NAME)
                    .deploy();
        }
    }
}
