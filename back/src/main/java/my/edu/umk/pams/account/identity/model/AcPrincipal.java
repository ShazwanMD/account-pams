package my.edu.umk.pams.account.identity.model;


import my.edu.umk.pams.account.core.AcMetaObject;

import java.util.Set;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcPrincipal extends AcMetaObject {

    String getName();

    void setName(String name);

    AcPrincipalType getPrincipalType();

    void setPrincipalType(AcPrincipalType principalType);

    boolean isLocked();

    void setLocked(boolean locked);

    boolean isEnabled();

    void setEnabled(boolean enabled);

    Set<AcPrincipalRole> getRoles();

    void setRoles(Set<AcPrincipalRole> roles);

}
