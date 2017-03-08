package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author PAMS
 */
public interface AcAcademicSession extends AcMetaObject{

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    Boolean isCurrent();

    void setCurrent(Boolean current);

}
