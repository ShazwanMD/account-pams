package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetaObject;

import java.util.Date;

/**
 * @author PAMS
 */
public interface AcAcademicSession extends AcMetaObject{

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    Date getStartDate();

    void setStartDate(Date startDate);

    Date getEndDate();

    void setEndDate(Date endDate);
    
    Boolean isCurrent();

    void setCurrent(Boolean current);

}
