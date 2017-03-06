package my.edu.umk.pams.account.system.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author canang technologies
 * @since 3/8/14
 */
public class AuditEvent extends ApplicationEvent {

    private my.edu.umk.pams.account.core.AcMetaObject object;
    private String message;

    public AuditEvent(my.edu.umk.pams.account.core.AcMetaObject source, String message) {
        super(source);
        this.object = source;
        this.message = message;
    }

    public my.edu.umk.pams.account.core.AcMetaObject getObject() {
        return object;
    }

    public void setObject(my.edu.umk.pams.account.core.AcMetaObject object) {
        this.object = object;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
