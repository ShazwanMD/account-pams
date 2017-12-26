package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "AcProgramCode")
@Table(name = "AC_PRGM_CODE")
public class AcProgramCodeImpl implements AcProgramCode {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_AC_PRGM_CODE")
    @SequenceGenerator(name = "SQ_AC_PRGM_CODE", sequenceName = "SQ_AC_PRGM_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne(targetEntity = AcFacultyCodeImpl.class)
    @JoinColumn(name = "FACULTY_CODE_ID")
    private AcFacultyCode facultyCode;

    @OneToMany(targetEntity = AcCohortCodeImpl.class, mappedBy = "programCode", fetch = FetchType.LAZY)
    private List<AcCohortCode> cohortCode;
    
    @ManyToOne(targetEntity = AcStudyCenterCodeImpl.class)
    @JoinColumn(name = "STUDY_CENTER_ID")
    private AcStudyCenterCode studyCenterCode;
    
    @OneToOne(targetEntity = AcProgramLevelImpl.class)
    @JoinColumn(name = "PROGRAM_LEVEL_ID", nullable = true)
    private AcProgramLevel programLevel;
    
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
    public AcFacultyCode getFacultyCode() {
        return facultyCode;
    }

    @Override
    public void setFacultyCode(AcFacultyCode facultyCode) {
        this.facultyCode = facultyCode;
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
        return AcProgramCode.class;
    }

	@Override
	public List<AcCohortCode> getCohortCode() {
		return cohortCode;
	}

	@Override
	public void setCohortCode(List<AcCohortCode> cohortCode) {
		this.cohortCode=cohortCode;
	}

	@Override
	public AcStudyCenterCode getStudyCenterCode() {
		return studyCenterCode;
	} 

	@Override
	public void setStudyCenterCode(AcStudyCenterCode studyCenterCode) {
		this.studyCenterCode = studyCenterCode;
	}

	public AcProgramLevel getProgramLevel() {
		return programLevel;
	}

	public void setProgramLevel(AcProgramLevel programLevel) {
		this.programLevel = programLevel;
	}
	
	
	
}
