package my.edu.umk.pams.account.system.event;

import my.edu.umk.pams.account.system.dao.AcAuditDao;
import my.edu.umk.pams.account.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author canang technologies
 * @since 3/8/14
 */
@Transactional
@Component("auditListener")
public class AuditListener implements ApplicationListener<AuditEvent> {

    @Autowired
    private AcAuditDao auditDao;

    @Override
    public void onApplicationEvent(AuditEvent auditEvent) {
        my.edu.umk.pams.account.system.model.AcAudit audit = new my.edu.umk.pams.account.system.model.AcAuditImpl();
        audit.setClassName(auditEvent.getObject().getInterfaceClass().getCanonicalName());
        audit.setMessage(auditEvent.getMessage());
        audit.setObjectId(auditEvent.getObject().getId());
        audit.setUserId(Util.getCurrentUser().getId());
        auditDao.save(audit, Util.getCurrentUser());
    }
}
