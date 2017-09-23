package my.edu.umk.pams.account.common.model;

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

@Entity(name = "AcSecurityChargeCode")
@Table(name = "AC_SCTY_CHRG_CODE")
public class AcSecurityChargeCodeImpl implements AcSecurityChargeCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_SCTY_CHRG_CODE")
    @SequenceGenerator(name = "SQ_AC_SCTY_CHRG_CODE", sequenceName = "SQ_AC_SCTY_CHRG_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "SECTION", nullable = false)
    private String section;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    
    @NotNull
    @Column(name = "OFFENSE", nullable = false)
    private String offense;
    
    @NotNull
    @Column(name = "OFFENSE_DESCRIPTION", nullable = false)
    private String offenseDescription;
    
    @Column(name = "TAX_AMOUNT")
    private BigDecimal taxAmount = BigDecimal.ZERO;
    
    @Column(name = "NET_AMOUNT")
    private BigDecimal netAmount = BigDecimal.ZERO;
    
    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    
    @NotNull
    @Column(name = "AMOUNT_DESCRIPTION", nullable = false)
    private String amountDescription;
    
    @Column(name = "INCLUSIVE")
    private Boolean inclusive;
    
    @NotNull
    @Column(name = "ACTIVE")
    private Boolean active;
    
    @NotNull
    @ManyToOne(targetEntity = AcTaxCodeImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "TAX_CODE_ID")
    private AcTaxCode taxCode;
    
    @Embedded
    private AcMetadata metadata;

    @Override
    public Long getId() {
		return id;
	}

    @Override
    public void setId(Long id) {
		this.id = id;
	}

    @Override
    public String getSection() {
		return section;
	}

    @Override
    public void setSection(String section) {
		this.section = section;
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
    public String getOffense() {
		return offense;
	}

    @Override
    public void setOffense(String offense) {
		this.offense = offense;
	}

    @Override
    public String getOffenseDescription() {
		return offenseDescription;
	}

    @Override
    public void setOffenseDescription(String offenseDescription) {
		this.offenseDescription = offenseDescription;
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
    public String getAmountDescription() {
		return amountDescription;
	}

    @Override
    public void setAmountDescription(String amountDescription) {
		this.amountDescription = amountDescription;
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
    public Boolean getInclusive() {
		return inclusive;
	}

    @Override
    public void setInclusive(Boolean inclusive) {
		this.inclusive = inclusive;
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
    public AcTaxCode getTaxCode() {
        return taxCode;
    }

    @Override
    public void setTaxCode(AcTaxCode taxCode) {
        this.taxCode = taxCode;
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
		// TODO Auto-generated method stub
		return null;
	} 

}
