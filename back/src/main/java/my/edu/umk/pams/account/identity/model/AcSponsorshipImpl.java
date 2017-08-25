package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAcademicSessionImpl;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorImpl;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.web.module.identity.vo.Sponsor;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author PAMS
 */
@Entity(name = "AcSponsorship")
@Table(name = "AC_SPHP")
public class AcSponsorshipImpl implements AcSponsorship {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_SPHP")
    @SequenceGenerator(name = "SQ_AC_SPHP", sequenceName = "SQ_AC_SPHP", allocationSize = 1)
    private Long id;
    
	@NotNull
	@Column(name = "REFERENCE_NO", nullable = false)
	private String referenceNo;
    
    @NotNull
    @Column(name = "ACCOUNT_NO", unique = true, nullable = false)
    private String accountNo;

	@ManyToOne(targetEntity = AcSponsorImpl.class)
    @JoinColumn(name = "SPONSOR_ID")
    private AcSponsor sponsor;
    
	@ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

    @ManyToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID", nullable = true)
    private AcAcademicSession session;
	

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

//    @NotNull
    @Column(name = "START_DATE")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    
//  @NotNull
    @Column(name = "END_DATE")
    private Date endDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    
    @Column(name = "ACTIVE")
    private Boolean active;
    
    @Embedded
    private AcMetadata metadata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getReferenceNo() {
        return referenceNo;
    }

    @Override
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    @Override
	public String getAccountNo() {
		return accountNo;
	}

    @Override
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
       
    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    
    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    @Override
    public Boolean getActive() {
		return active;
	}

    @Override
    public void setActive(Boolean active) {
		this.active = active;
	}

    @Override
    public AcMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }
    
    @Override
    public AcSponsor getSponsor() {
		return sponsor;
	}

    @Override
	public void setSponsor(AcSponsor sponsor) {
		this.sponsor = sponsor;
	}
    
    @Override
    public AcAccount getAccount() {
        return account;
    }

    @Override
    public AcAcademicSession getSession() {
        return session;
    }

    @Override
    public void setSession(AcAcademicSession session) {
        this.session = session;
    }
    
    @Override
    public void setAccount(AcAccount account) {
        this.account = account;
    }
	
    @Override
    public Class<?> getInterfaceClass() {
        return AcSponsorship.class;
    }
	
}
