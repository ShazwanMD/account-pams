package my.edu.umk.pams.account.identity.model;


import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcGroupMember extends AcMetaObject {

    AcGroup getGroup();

    void setGroup(AcGroup group);

    AcPrincipal getPrincipal();

    void setPrincipal(AcPrincipal principal);
}
