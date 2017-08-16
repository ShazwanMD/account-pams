package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcKnockoff")
@Table(name = "AC_KNOF")
public class AcKnockoffImpl implements AcKnockoff {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_KNOF")
    @SequenceGenerator(name = "SQ_AC_KNOF", sequenceName = "SQ_AC_KNOF", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "SOURCE_NO")
    private String sourceNo;

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
    
    @NotNull
    @OneToOne(targetEntity = AcInvoiceImpl.class)
    @JoinColumn(name = "INVOICE_ID")
    private AcInvoice invoice;
    
    @OneToMany(targetEntity = AcAdvancePaymentImpl.class, mappedBy = "advancePayment")
    private List<AcAdvancePayment> payments;
    
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
    public AcInvoice getInvoice() {
        return invoice;
    }

    @Override
    public void setInvoice(AcInvoice invoice) {
        this.invoice = invoice;
    }
    
    @Override	
    public List<AcAdvancePayment> getPayments() {
		return payments;
	}

    @Override
	public void setPayments(List<AcAdvancePayment> payments) {
		this.payments = payments;
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
        return AcKnockoff.class;
    }

}
