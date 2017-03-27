package my.edu.umk.pams.account.identity.model;

import java.util.Set;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcGroup extends AcPrincipal {

    // TODO: move to respective XxxConstants
    String GRP_USR = "UserGroup";
    String GRP_ADM_FIXED = "GRP_ADM_FIXED";
    String GRP_ADM_PURCHASE = "GRP_ADM_PURCHASE";
    String GRP_ADM_INVENTORY = "GRP_ADM_INVENTORY";

    Set<AcPrincipal> getMembers();

    void setMembers(Set<AcPrincipal> members);
}
