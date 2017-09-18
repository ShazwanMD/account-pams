package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcCohortCodeImpl;
import my.edu.umk.pams.account.common.model.AcSecurityChargeCode;
import my.edu.umk.pams.account.common.model.AcSecurityChargeCodeImpl;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.common.model.AcStudyModeImpl;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.common.model.AcTaxCodeImpl;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
@Entity(name = "AcAccountCharge")
@Table(name = "AC_ACCT_CHRG")
public class AcAccountChargeImpl implements AcAccountCharge {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_ACCT_CHRG")
    @SequenceGenerator(name = "SEQ_ACCT_CHRG", sequenceName = "SEQ_ACCT_CHRG", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "REFERENCE_NO", unique = true, nullable = false)
    private String referenceNo;

    @Column(name = "SOURCE_NO", nullable = false)
    private String sourceNo;

    @NotNull
    @Column(name = "CODE")
    private String code;
    
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

//    @NotNull
    @Column(name = "ORDINAL", columnDefinition = "int default 0")
    private Integer ordinal = 0;

//    @NotNull
    @Column(name = "CHARGE_DATE")
    private Date chargeDate;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "CHARGE_TYPE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private AcAccountChargeType chargeType;

    @NotNull
    @ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;
    
    @Column(name = "TAX_AMOUNT")
    private BigDecimal taxAmount = BigDecimal.ZERO;
    
    @Column(name = "NET_AMOUNT")
    private BigDecimal netAmount = BigDecimal.ZERO;

    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount = BigDecimal.ZERO;
         
    @ManyToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID", nullable = true)
    private AcAcademicSession session;

    @ManyToOne(targetEntity = AcInvoiceImpl.class)
    @JoinColumn(name = "INVOICE_ID", nullable = true)
    private AcInvoice invoice;

    @NotNull
    @OneToOne(targetEntity = AcStudyModeImpl.class)
    @JoinColumn(name = "STUDY_MODE_ID")
    private AcStudyMode studyMode;

    @NotNull
    @OneToOne(targetEntity = AcCohortCodeImpl.class)
    @JoinColumn(name = "COHORT_CODE_ID")
    private AcCohortCode cohortCode;
    
    @NotNull
    @OneToOne(targetEntity = AcSecurityChargeCodeImpl.class)
    @JoinColumn(name = "SECURITY_CHARGE_CODE_ID")
    private AcSecurityChargeCode securityChargeCode;

    @OneToOne(targetEntity = AcTaxCodeImpl.class)
    @JoinColumn(name = "TAX_CODE_ID")
    private AcTaxCode taxCode;
    
    @Column(name = "INCLUSIVE")
    private Boolean inclusive;
    
    @Column(name = "PAID")
    private Boolean paid = false;
    
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
    public String getCode() {
		return code;
	}

    @Override
    public void setCode(String code) {
		this.code = code;
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
    public Integer getOrdinal() {
        return ordinal;
    }

    @Override
    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    @Override
    public Date getChargeDate() {
        return chargeDate;
    }

    @Override
    public void setChargeDate(Date chargeDate) {
        this.chargeDate = chargeDate;
    }

    @Override
    public AcAccountChargeType getChargeType() {
        return chargeType;
    }

    @Override
    public void setChargeType(AcAccountChargeType chargeType) {
        this.chargeType = chargeType;
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
    public AcAcademicSession getSession() {
        return session;
    }

    @Override
    public void setSession(AcAcademicSession session) {
        this.session = session;
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
    public AcStudyMode getStudyMode() {
        return studyMode;
    }

    @Override
    public void setStudyMode(AcStudyMode studyMode) {
        this.studyMode = studyMode;
    }

    @Override
    public AcCohortCode getCohortCode() {
        return cohortCode;
    }

    @Override
    public void setCohortCode(AcCohortCode cohortCode) {
        this.cohortCode = cohortCode;
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
    public Boolean getPaid() {
		return paid;
	}

    @Override
    public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	@Override
    public AcSecurityChargeCode getSecurityChargeCode() {
		return securityChargeCode;
	}

    @Override
    public void setSecurityChargeCode(AcSecurityChargeCode securityChargeCode) {
		this.securityChargeCode = securityChargeCode;
	}

    @Override
    public AcTaxCode getTaxCode() {
		return taxCode;
	}

    @Override
    public void setTaxCode(AcTaxCode taxCode) {
		this.taxCode = taxCode;
	}

    @Override
    public Boolean getInclusive() {
		return inclusive;
	}

    @Override
    public void setInclusive(Boolean inclusive) {
		this.inclusive = inclusive;
	}
    
    @Override
	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

    @Override
	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

    @Override
	public BigDecimal getNetAmount() {
		return netAmount;
	}

    @Override
	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
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
        return AcAccountCharge.class;
    }
}
