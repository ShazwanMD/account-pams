package my.edu.umk.pams.account.identity.model;

import java.math.BigDecimal;
import java.util.Date;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.web.module.identity.vo.Sponsor;

/**
 * ?? todo: coverage
 *
 * @author PAMS
 */
public interface AcSponsorship extends AcMetaObject {


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
	
    AcAccount getAccount();

    void setAccount(AcAccount account);

    AcSponsor getSponsor();
    
	void setSponsor(AcSponsor sponsor);

	
	
//    AcAcademicSession getSession();
//
//    void setSession(AcAcademicSession session);

}
 