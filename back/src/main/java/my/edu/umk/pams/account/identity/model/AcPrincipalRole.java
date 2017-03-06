package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcPrincipalRole extends AcMetaObject {

    AcPrincipal getPrincipal();

    void setPrincipal(AcPrincipal principal);

    AcRoleType getRole();

    void setRole(AcRoleType role);
}
