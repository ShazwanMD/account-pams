package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.common.model.AcStudyModeImpl;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.common.model.AcTaxCodeImpl;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author PAMS
 */
@Entity(name = "AcChargeCode")
@Table(name = "AC_CHRG_CODE")
public class AcChargeCodeImpl implements AcChargeCode {

    @Id
    @GeneratedValue(generator = "SQ_AC_CHRG_CODE")
    @SequenceGenerator(name = "SQ_AC_CHRG_CODE", sequenceName = "SQ_AC_CHRG_CODE", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "CHARGE_TYPE", nullable = false)
    private AcChargeCodeType chargeType = AcChargeCodeType.ACADEMIC;

    @Column(name = "PRIORITY", nullable = false)
    private Integer priority;
    
    @OneToOne(targetEntity = AcTaxCodeImpl.class)
    @JoinColumn(name = "TAX_CODE_ID")
    private AcTaxCode taxCode;
    
    @Column(name = "INCLUSIVE")
    private Boolean inclusive;
    
    @Column(name = "ACTIVE")
    private Boolean active;
    
    @Embedded
    private AcMetadata metadata;

    public AcChargeCodeImpl() {
        setChargeType(AcChargeCodeType.ACADEMIC);
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AcChargeCodeType getChargeType() {
        return chargeType;
    }

    public void setChargeType(AcChargeCodeType chargeType) {
        this.chargeType = chargeType;
    }

    @Override
    public Integer getPriority() {
        return priority;
    }

    @Override
    public void setPriority(Integer priority) {
        this.priority = priority;
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

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcChargeCode.class;
    }
}
