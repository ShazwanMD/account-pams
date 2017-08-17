package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.core.AcMetadata;

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
    @Column(name = "REFERENCE_NO", unique = true, nullable = false)
    private String referenceNo;
    
    @NotNull
    @Column(name = "ACCOUNT_NO", unique = true, nullable = false)
    private String accountNo;

    @ManyToOne(targetEntity = AcStudentImpl.class)
    @JoinColumn(name = "STUDENT_ID")
    private AcStudent student;

	@ManyToOne(targetEntity = AcSponsorImpl.class)
    @JoinColumn(name = "SPONSOR_ID")
    private AcSponsor sponsor;
    
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

    public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
    
    public AcStudent getStudent() {
        return student;
    }

    public void setStudent(AcStudent student) {
        this.student = student;
    }

    public AcSponsor getSponsor() {
        return sponsor;
    }

    public void setSponsor(AcSponsor sponsor) {
        this.sponsor = sponsor;
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
    public Class<?> getInterfaceClass() {
        return AcSponsorship.class;
    }
}
