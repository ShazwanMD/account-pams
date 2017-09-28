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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcKnockoff")
@Table(name = "AC_KNOF")
@Inheritance(strategy = InheritanceType.JOINED)
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
    
    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @Column(name = "REMOVE_COMMENT")
    private String removeComment;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount = BigDecimal.ZERO;
    
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @NotNull
    @Column(name = "ISSUED_DATE")
    private Date issuedDate;

    @NotNull
    @ManyToOne(targetEntity = AcAdvancePaymentImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ADVANCE_PAYMENT_ID")
    private AcAdvancePayment payments;
    
    @Embedded
    private AcMetadata metadata;
    
    @Embedded
    private AcFlowdata flowdata;
    
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = AcKnockoffImpl.class)
    @JoinTable(name = "AC_KNOF_INVC", joinColumns = {
            @JoinColumn(name = "KNOCKOFF_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "INVOICE_ID",
                    nullable = false, updatable = false)})
    private List<AcInvoice> invoices;
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = AcAccountChargeImpl.class)
    @JoinTable(name = "AC_RCPT_ACCT_CHRG", joinColumns = {
            @JoinColumn(name = "RECEIPT_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "ACCOUNT_CHARGE_ID",
                    nullable = false, updatable = false)})
    private List<AcAccountCharge> accountCharges;
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = AcDebitNoteImpl.class)
    @JoinTable(name = "AC_RCPT_DBT", joinColumns = {
            @JoinColumn(name = "RECEIPT_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "DEBITNOTE_ID",
                    nullable = false, updatable = false)})
    private List<AcDebitNote> debitNotes;
	
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
    public AcAdvancePayment getPayments() {
		return payments;
	}

    @Override
	public void setPayments(AcAdvancePayment payments) {
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
    public AcFlowdata getFlowdata() {
		return flowdata;
	}

    @Override
	public void setFlowdata(AcFlowdata flowdata) {
		this.flowdata = flowdata;
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
    public String getRemoveComment() {
        return removeComment;
    }

    @Override
    public void setRemoveComment(String removeComment) {
        this.removeComment = removeComment;
    }
    
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	@Override
	public List<AcInvoice> getInvoices() {
		return invoices;
	}
	
	@Override
	public List<AcAccountCharge> getAccountCharges() {
		return accountCharges;
	}
	
	@Override
	public List<AcDebitNote> getDebitNotes() {
		return debitNotes;
	}

	@Override
    public Class<?> getInterfaceClass() {
        return AcKnockoff.class;
    }

}
