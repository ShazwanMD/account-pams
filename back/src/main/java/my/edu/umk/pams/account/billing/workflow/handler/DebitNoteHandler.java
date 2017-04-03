package my.edu.umk.pams.account.billing.workflow.handler;

import my.edu.umk.pams.account.billing.model.AcDebitNote;
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
public class DebitNoteHandler implements DocumentHandler<AcDebitNote> {

    private static final Logger LOG = LoggerFactory.getLogger(DebitNoteHandler.class);

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
    public String process(AcDebitNote debit_note, Map<String, Object> variables) {
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(
                DEBIT_NOTE_PROCESS_KEY,
                debit_note.getReferenceNo(),
                variables);
        LOG.info("Process started for {} with process instance #{} ", DEBIT_NOTE_PROCESS_KEY, instance.getId());
        return instance.getProcessInstanceId();
    }

    @PostConstruct
    public void deployDebit_note() {
        DeploymentBuilder deployment = repositoryService.createDeployment();
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();

        // start only when we don't have one
//        long count = query.processDefinitionKey(DEBIT_NOTE_PROCESS_KEY).count();
//        if (count < 1) {
//            deployment
//                    .addClasspathResource(DEBIT_NOTE_RESOURCE_PATH)
//                    .name(DEBIT_NOTE_PROCESS_NAME)
//                    .deploy();
//        }
    }
}
