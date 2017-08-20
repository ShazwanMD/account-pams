package my.edu.umk.pams.account.account.model;

import java.math.BigDecimal;
import java.util.Date;

import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcStudent;

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
    
    String getReferenceNo();

    void setReferenceNo(String referenceNo);
    
    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    void setStartDate(Date startDate);

    Date getStartDate();
    
    void setEndDate(Date endDate);

    Date getEndDate();
    
    String getAccountNo();

    void setAccountNo(String accountNo);
    
    Boolean getActive();

	void setActive(Boolean active);

}
 