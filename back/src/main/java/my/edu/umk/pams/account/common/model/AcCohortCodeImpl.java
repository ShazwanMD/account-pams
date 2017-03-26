package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.identity.model.*;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "AcCohortCode")
@Table(name = "AC_CHRT_CODE")
public class AcCohortCodeImpl implements AcCohortCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_CHRT_CODE")
    @SequenceGenerator(name = "SQ_AC_CHRT_CODE", sequenceName = "SQ_AC_CHRT_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @ManyToOne(targetEntity = AcProgramCodeImpl.class)
    @JoinColumn(name = "PROGRAM_CODE_ID")
    private AcProgramCode programCode;

    @OneToMany(targetEntity = AcStudentImpl.class, mappedBy = "student", fetch = FetchType.LAZY)
    private List<AcStudent> student;
    
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
    public AcProgramCode getProgramCode() {
        return programCode;
    }

    @Override
    public void setProgramCode(AcProgramCode programCode) {
        this.programCode = programCode;
    }

    @Override
    public List<AcStudent> getStudent() {
        return student;
    }

    @Override
    public void setStudent(List<AcStudent> student) {
        this.student=student;
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
        return AcCohortCode.class;
    }
    
}
