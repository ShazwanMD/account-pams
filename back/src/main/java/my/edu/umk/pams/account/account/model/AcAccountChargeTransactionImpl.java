package my.edu.umk.pams.account.account.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcAccountChargeTransaction")
@Table(name = "AC_ACCT_CHRG_TRSN")
public class AcAccountChargeTransactionImpl implements AcAccountChargeTransaction {
	
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_ACCT_TRSN")
    @SequenceGenerator(name = "SQ_AC_ACCT_TRSN", sequenceName = "SQ_AC_ACCT_TRSN", allocationSize = 1)
    private Long id;

    @Column(name = "SOURCE_NO", nullable = false)
    private String sourceNo;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;
    
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotNull
    @Column(name = "POSTED_DATE", nullable = false)
    private Date postedDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TRANSACTION_CODE", nullable = false)
    private AcAccountTransactionCode transactionCode;

    //@NotNull
    @ManyToOne(targetEntity = AcChargeCodeImpl.class)
    @JoinColumn(name = "CHARGE_CODE_ID", nullable = true)
    private AcChargeCode chargeCode;

    @NotNull
    @OneToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID", nullable = false)
    private AcAcademicSession session;

    @NotNull
    @ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

    @Embedded
    private AcMetadata metadata;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSourceNo() {
		return sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public AcAccountTransactionCode getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(AcAccountTransactionCode transactionCode) {
		this.transactionCode = transactionCode;
	}

	public AcChargeCode getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(AcChargeCode chargeCode) {
		this.chargeCode = chargeCode;
	}

	public AcAcademicSession getSession() {
		return session;
	}

	public void setSession(AcAcademicSession session) {
		this.session = session;
	}

	public AcAccount getAccount() {
		return account;
	}

	public void setAccount(AcAccount account) {
		this.account = account;
	}

	public AcMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(AcMetadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public Class<?> getInterfaceClass() {
		return AcAccountChargeTransaction.class;
	}
    
    
}
