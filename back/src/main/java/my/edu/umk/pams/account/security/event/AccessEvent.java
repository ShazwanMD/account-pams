package my.edu.umk.pams.account.security.event;

import my.edu.umk.pams.account.security.integration.AdPermission;
import org.springframework.context.ApplicationEvent;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public class AccessEvent extends ApplicationEvent {

    private my.edu.umk.pams.account.core.AcMetaObject object;
    private my.edu.umk.pams.account.core.AcMetaObject parent;
    private AdPermission permission;
    private my.edu.umk.pams.account.identity.model.AcPrincipal principal;
    private Command command = Command.GRANT;

    public enum Command {GRANT, REVOKE, INHERIT}

    public AccessEvent(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AdPermission permission) {
        super(object);
        this.object = object;
        this.principal = principal;
        this.permission = permission;
    }

    public AccessEvent(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AdPermission permission, Command command) {
        super(object);
        this.object = object;
        this.principal = principal;
        this.permission = permission;
        this.command = command;
    }

    public AccessEvent(my.edu.umk.pams.account.core.AcMetaObject parent, my.edu.umk.pams.account.core.AcMetaObject object) {
        super(object);
        this.object = object;
        this.parent = parent;
        this.command = Command.INHERIT;
    }

    public my.edu.umk.pams.account.core.AcMetaObject getObject() {
        return object;
    }

    public my.edu.umk.pams.account.core.AcMetaObject getParent() {
        return parent;
    }

    public AdPermission getPermission() {
        return permission;
    }

    public void setPermission(AdPermission permission) {
        this.permission = permission;
    }

    public my.edu.umk.pams.account.identity.model.AcPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(my.edu.umk.pams.account.identity.model.AcPrincipal principal) {
        this.principal = principal;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
}
