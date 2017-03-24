package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.core.AcMetadata;

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
}
