package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author canang.technologies
 * @since 31/10/2014
 */
public interface AcActor extends AcMetaObject {

    String getIdentityNo();

    void setIdentityNo(String identityNo);

    String getName();

    void setName(String firstName);

    String getPhone();

    void setPhone(String phone);

    String getMobile();

    void setMobile(String mobile);

    String getFax();

    void setFax(String fax);

    String getEmail();

    void setEmail(String email);
    
    String getAddress();

    void setAddress(String address);

    AcActorType getActorType();

    void setActorType(AcActorType actorType);
}
