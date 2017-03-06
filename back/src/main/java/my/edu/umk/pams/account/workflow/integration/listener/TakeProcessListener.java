package my.edu.umk.pams.account.workflow.integration.listener;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.pvm.process.TransitionImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Faizal Abdul Manan on 22/09/2014.
 */
public class TakeProcessListener implements ExecutionListener {

    private static final Logger LOG = LoggerFactory.getLogger(TakeProcessListener.class);


    @Override
    public void notify(DelegateExecution execution) throws Exception {

        TransitionImpl transition = ((ExecutionEntity) execution).getTransition();
        String s = null;
        if (null != transition) {
            s = transition.toString();
        }

        LOG.debug("[" + execution.getProcessInstanceId() + ":(" + s
                + ") " + "] Process take:"
                + execution.getVariables());


    }
}
