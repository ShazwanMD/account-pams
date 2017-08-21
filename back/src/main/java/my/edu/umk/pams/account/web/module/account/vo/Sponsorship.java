package my.edu.umk.pams.account.web.module.account.vo;

import java.math.BigDecimal;
import java.util.Date;


import my.edu.umk.pams.account.web.module.core.vo.MetaObject;
import my.edu.umk.pams.account.web.module.identity.vo.Sponsor;
import my.edu.umk.pams.account.web.module.identity.vo.Student;

/**
 * @author PAMS
 */
public class Sponsorship extends MetaObject {

	private BigDecimal amount;
	private Date startDate;
	private Date endDate;
	private String referenceNo;
	private String accountNo;
	private Boolean active;
	private Student student;
	private Sponsor sponsor;

	public BigDecimal getAmount() {
		return amount;	
	}
	
	 public void setAmount(BigDecimal amount) {
	        this.amount = amount;
	 }

	public Date getStartDate() {
		return startDate;	
	}

	public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
	
	public Date getEndDate() {
		return endDate;	
	}

	public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
	
	public String getReferenceNo() {
		return referenceNo;
	}
	
	public void setReferenceNo(String referenceNo){
		this.referenceNo = referenceNo;
	}
	
	public String getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(String accountNo){
		this.accountNo = accountNo;
	}

	public Boolean getActive() {
		return active;
	}

    public void setActive(Boolean active) {
		this.active = active;
	}

    public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
    public Sponsor getSponsor() {
		return sponsor;
	}

	public void setSponsor(Sponsor sponsor) {
		this.sponsor = sponsor;
	}
	
}


