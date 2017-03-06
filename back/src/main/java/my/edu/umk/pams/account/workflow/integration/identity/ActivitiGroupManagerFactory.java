package my.edu.umk.pams.account.workflow.integration.identity;

import org.activiti.engine.impl.interceptor.Session;
import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.GroupIdentityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author canang technologies
 * @since 1/19/14
 */
@Component("activitiGroupManagerFactory")
public class ActivitiGroupManagerFactory implements SessionFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ActivitiGroupManagerFactory.class);

    @Autowired
    private ActivitiGroupManager groupManager;

    @Override
    public Class<?> getSessionType() {
        return GroupIdentityManager.class;
    }

    @Override
    public Session openSession() {
        return groupManager;
    }
}
