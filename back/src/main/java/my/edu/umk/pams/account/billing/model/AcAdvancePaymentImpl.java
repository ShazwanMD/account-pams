package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAcademicSessionImpl;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcAdvancePayment")
@Table(name = "AC_ADVC_PYMT")
public class AcAdvancePaymentImpl implements AcAdvancePayment {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_ADVC_PYMT")
    @SequenceGenerator(name = "SQ_AC_ADVC_PYMT", sequenceName = "SQ_AC_ADVC_PYMT", allocationSize = 1)
    private Long id;
    
	@Column(name = "REFERENCE_NO")
    private String referenceNo;

	@Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount = BigDecimal.ZERO;
    
    @Column(name = "STATUS")
    private Boolean status = false;

    @ManyToOne(targetEntity = AcReceiptImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "RECEIPT_ID")
    private AcReceipt receipt;
    
    @OneToMany(targetEntity = AcKnockoffImpl.class, mappedBy = "payments")
    private List<AcKnockoff> knockoff;
    
    @OneToMany(targetEntity = AcRefundPaymentImpl.class, mappedBy = "payments")
    private List<AcRefundPayment> refundPayment;
    
    @NotNull
    @ManyToOne(targetEntity = AcAccountImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;
    
    @NotNull
    @OneToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID")
    private AcAcademicSession session;

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
	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	@Override
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	
	@Override
	public Boolean getStatus() {
		return status;
	}

	@Override
	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Override
	public AcReceipt getReceipt() {
		return receipt;
	}

	@Override
	public void setReceipt(AcReceipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public List<AcRefundPayment> getRefundPayment() {
		return refundPayment;
	}

	@Override
	public void setRefundPayment(List<AcRefundPayment> refundPayment) {
		this.refundPayment = refundPayment;
	}
	
	@Override
	public List<AcKnockoff> getKnockoff() {
		return knockoff;
	}

	@Override
	public void setKnockoff(List<AcKnockoff> knockoff) {
		this.knockoff = knockoff;
	}
	
	@Override
	public AcAccount getAccount() {
		return account;
	}

	@Override
	public void setAccount(AcAccount account) {
		this.account = account;
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
        return AcAdvancePayment.class;
    }

    @Override
    public AcAcademicSession getSession() {
        return session;
    }

    @Override
    public void setSession(AcAcademicSession session) {
        this.session = session;
    }
}
