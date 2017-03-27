package my.edu.umk.pams.account.workflow.integration.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Faizal Abdul Manan on 22/09/2014.
 */
public class StartProcessListener implements ExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(StartProcessListener.class);


    @Override
    public void notify(DelegateExecution execution) throws Exception {
        ExecutionEntity executionEntity = (ExecutionEntity) execution;
        LOG.debug("[" + executionEntity.getProcessDefinitionId() + "-" + execution.getProcessInstanceId() + "] Process start " + executionEntity.getProcessDefinitionId());
    }

}
