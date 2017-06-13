package my.edu.umk.pams.account.common.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcTaxCode")
@Table(name = "AC_TAX_CODE")
public class AcTaxCodeImpl implements AcTaxCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_TAX_CODE")
    @SequenceGenerator(name = "SQ_AC_TAX_CODE", sequenceName = "SQ_AC_TAX_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "TAX_RATE", nullable = false)
    private String taxRate;
    
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    
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
	public String getTaxRate() {
		return taxRate;
	}

	@Override
	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
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
        return AcTaxCode.class;
    }
}
