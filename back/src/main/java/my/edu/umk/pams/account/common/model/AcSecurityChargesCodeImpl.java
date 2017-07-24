package my.edu.umk.pams.account.common.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcSecurityChargesCode")
@Table(name = "AC_SCTY_CHRG_CODE")
public class AcSecurityChargesCodeImpl implements AcSecurityChargesCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_SCTY_CHRG_CODE")
    @SequenceGenerator(name = "SQ_AC_SCTY_CHRG_CODE", sequenceName = "SQ_AC_SCTY_CHRG_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "SECTION", unique = true, nullable = false)
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
    
    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;
    
    @NotNull
    @Column(name = "AMOUNT_DESCRIPTION", nullable = false)
    private String amountDescription;
    
    @NotNull
    @Column(name = "ACTIVE")
    private Boolean active;
    
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
		// TODO Auto-generated method stub
		return null;
	} 

}
