package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcAdvancePayment")
@Table(name = "AC_ADVC_PYMT")
public class AcAdvancePaymentImpl implements AcAdvancePayment {

    @Id
    @GeneratedValue(generator = "SQ_AC_ADVC_PYMT")
    @SequenceGenerator(name = "SQ_AC_ADVC_PYMT", sequenceName = "SQ_AC_ADVC_PYMT", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
	@Column(name = "REFERENCE_NO")
    private String referenceNo;

	@Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount = BigDecimal.ZERO;

    @NotNull
    @ManyToOne(targetEntity = AcReceiptImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "RECEIPT_ID")
    private AcReceipt receipt;
    
    @ManyToOne(targetEntity = AcKnockoffImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "KNOCKOFF_ID")
    private AcKnockoff knockoff;

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
	public AcReceipt getReceipt() {
		return receipt;
	}

	@Override
	public void setReceipt(AcReceipt receipt) {
		this.receipt = receipt;
	}

	@Override
	public AcKnockoff getKnockoff() {
		return knockoff;
	}

	@Override
	public void setKnockoff(AcKnockoff knockoff) {
		this.knockoff = knockoff;
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
}
