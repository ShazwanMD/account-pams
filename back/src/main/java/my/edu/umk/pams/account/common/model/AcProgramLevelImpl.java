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


@Entity(name = "AcProgramLevel")
@Table(name = "AC_PRGM_LEVEL")
public class AcProgramLevelImpl implements AcProgramLevel {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_AC_PRGM_LEVEL")
    @SequenceGenerator(name = "SQ_AC_PRGM_LEVEL", sequenceName = "SQ_AC_PRGM_LEVEL", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = true)
    private String description;
    
    @NotNull
    @Column(name = "PREFIX")
    private String prefix;
    
    @Embedded
    private AcMetadata metadata;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public AcMetadata getMetadata() {
		return metadata;
	}

	public void setMetadata(AcMetadata metadata) {
		this.metadata = metadata;
	}
    
    @Override
    public Class<?> getInterfaceClass() {
        return AcProgramLevel.class;
    }
    
    
}
