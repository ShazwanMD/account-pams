package my.edu.umk.pams.account.common.router;

import my.edu.umk.pams.account.core.model.AcDocument;
import my.edu.umk.pams.account.identity.model.AcPrincipal;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.event.AccessEvent;
import my.edu.umk.pams.account.security.integration.AcPermission;
import my.edu.umk.pams.account.system.service.SystemService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Abstract Router Service
 */
public class RouterServiceSupport implements ApplicationContextAware {

    @Autowired
    protected ApplicationContext context;

    @Autowired
    protected SystemService systemService;

    @Autowired
    protected IdentityService identityService;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        this.context = context;
    }

    protected void publishAccessEvent(AcDocument document, AcPrincipal principal, AcPermission read) {
        AccessEvent accessEvent = new AccessEvent(document, principal, AcPermission.VIEW);
        context.publishEvent(accessEvent);
    }
}
