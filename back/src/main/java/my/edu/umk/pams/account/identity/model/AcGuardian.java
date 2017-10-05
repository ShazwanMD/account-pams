package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcGuardian extends AcMetaObject {

	String getIdentityNo();

    void setIdentityNo(String noIc);

    String getName();

    void setName(String name);

    AcGuardianType getType();

    void setType(AcGuardianType type);

    AcStudent getStudent();

    void setStudent(AcStudent student);
    
    String getPhone();

    void setPhone(String phone);

}
