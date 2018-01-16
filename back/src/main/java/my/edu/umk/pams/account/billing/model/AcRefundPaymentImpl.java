package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcRefundPayment")
@Table(name = "AC_RFND_PYMT")
public class AcRefundPaymentImpl implements AcRefundPayment {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_RFND_PYMT")
    @SequenceGenerator(name = "SQ_AC_RFND_PYMT", sequenceName = "SQ_AC_RFND_PYMT", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "SOURCE_NO")
    private String sourceNo;
    
    @Column(name = "VOUCHER_NO")
    private String voucherNo;

    @Column(name = "AUDIT_NO")
    private String auditNo;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;

    @NotNull
    @Column(name = "ISSUED_DATE")
    private Date issuedDate;
    
    @ManyToOne(targetEntity = AcAdvancePaymentImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ADVANCE_PAYMENT_ID")
    private AcAdvancePayment payments;
    
    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @Column(name = "REMOVE_COMMENT")
    private String removeComment;
    
    @Column(name = "REPORT_STATUS")
    private String reportStatus;
    
    @Embedded
    private AcMetadata metadata;

    @Embedded
    private AcFlowdata flowdata;
    
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
	public String getSourceNo() {
		return sourceNo;
	}

	@Override
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Override
	public String getAuditNo() {
		return auditNo;
	}

	@Override
	public void setAuditNo(String auditNo) {
		this.auditNo = auditNo;
	}

	@Override
	public String getVoucherNo() {
		return voucherNo;
	}

	@Override
	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	@Override
	public String getRemoveComment() {
		return removeComment;
	}

	@Override
	public void setRemoveComment(String removeComment) {
		this.removeComment = removeComment;
	}

	@Override
	public String getCancelComment() {
		return cancelComment;
	}

	@Override
	public void setCancelComment(String cancelComment) {
		this.cancelComment = cancelComment;
	}

	@Override
	public AcFlowdata getFlowdata() {
		return flowdata;
	}

	@Override
	public void setFlowdata(AcFlowdata flowdata) {
		this.flowdata = flowdata;
	}
    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public Date getIssuedDate() {
        return issuedDate;
    }

    @Override
    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    @Override	
    public AcAdvancePayment getPayments() {
		return payments;
	}

    @Override
	public void setPayments(AcAdvancePayment payments) {
		this.payments = payments;
	}
    
	@Override
    public String getReportStatus() {
		return reportStatus;
	}

    @Override
    public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
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
        return AcRefundPayment.class;
    }
}
