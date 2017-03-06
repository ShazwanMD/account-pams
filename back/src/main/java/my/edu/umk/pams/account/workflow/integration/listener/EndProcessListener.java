package my.edu.umk.pams.account.workflow.integration.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EndProcessListener implements ExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(EndProcessListener.class);


    @Override
    public void notify(DelegateExecution execution) throws Exception {
        LOG.debug("[" + execution.getProcessInstanceId() + "] Process end");
    }

}
