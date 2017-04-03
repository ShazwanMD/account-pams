package my.edu.umk.pams.account.billing.workflow.handler;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
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

import static my.edu.umk.pams.account.AccountConstants.CREDIT_NOTE_PROCESS_KEY;

/**
 * @author PAMS
 */
@Component
public class CreditNoteHandler implements DocumentHandler<AcCreditNote> {

    private static final Logger LOG = LoggerFactory.getLogger(CreditNoteHandler.class);

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
    public String process(AcCreditNote credit_note, Map<String, Object> variables) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                CREDIT_NOTE_PROCESS_KEY,
                credit_note.getReferenceNo(),
                variables);
        LOG.info("Process started for {} with process instance #{} ", CREDIT_NOTE_PROCESS_KEY, instance.getId());
        return instance.getProcessInstanceId();
    }

    @PostConstruct
    public void deployCredit_note() {
        DeploymentBuilder deployment = repositoryService.createDeployment();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        // start only when we don't have one
//        long count = query.processDefinitionKey(CREDIT_NOTE_PROCESS_KEY).count();
//        if (count < 1) {
//            deployment
//                    .addClasspathResource(CREDIT_NOTE_RESOURCE_PATH)
//                    .name(CREDIT_NOTE_PROCESS_NAME)
//                    .deploy();
//        }
    }
}
