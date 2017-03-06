package my.edu.umk.pams.account.workflow.integration.identity;

import org.activiti.engine.impl.interceptor.SessionFactory;
import org.activiti.engine.impl.persistence.entity.UserIdentityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author canang technologies
 * @since 1/19/14
 */
@Component("activitiUserManagerFactory")
public class ActivitiUserManagerFactory implements SessionFactory {

    private static final Logger LOG = LoggerFactory.getLogger(ActivitiUserManagerFactory.class);

    @Autowired
    private ActivitiUserManager userManager;

    @Override
    public Class<?> getSessionType() {
        return UserIdentityManager.class;
    }

    @Override
    public org.activiti.engine.impl.interceptor.Session openSession() {
        LOG.debug("open session");
        return userManager;

    }
}
