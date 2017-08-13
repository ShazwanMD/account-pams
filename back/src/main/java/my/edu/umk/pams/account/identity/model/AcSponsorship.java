package my.edu.umk.pams.account.identity.model;

import java.math.BigDecimal;
import java.util.Date;

import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * ?? todo: coverage
 *
 * @author PAMS
 */
public interface AcSponsorship extends AcMetaObject {

    AcSponsor getSponsor();

    void setSponsor(AcSponsor sponsor);

    AcStudent getStudent();

    void setStudent(AcStudent student);
    
    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    void setStartDate(Date startDate);

    Date getStartDate();
    
    void setEndDate(Date endDate);

    Date getEndDate();
    
    Boolean getActive();

	void setActive(Boolean active);
}
 