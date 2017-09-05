package my.edu.umk.pams.account.billing.workflow.handler;

import my.edu.umk.pams.account.billing.model.AcKnockoff;
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
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                KNOCKOFF_PROCESS_KEY,
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
