package my.edu.umk.pams.account.security.model;

import java.util.Set;

/**
 * @author canang technologies
 * @since 1/15/14
 */
public interface AclObjectIdentity {

    Long getId();

    Long getObjectIdIdentity();

    Long getParentObject();

    Boolean getEntriesInheriting();

    AclClass getObjectClass();

    AclSid getOwnerSid();

    Set<AclEntry> getEntries();

}
