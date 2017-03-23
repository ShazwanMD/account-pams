package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.account.model.AcChargeCodeImpl;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItemImpl;
import my.edu.umk.pams.account.identity.model.AcSponsorType;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "AcFacultyCode")
@Table(name = "AC_FCTY_CODE")
public class AcFacultyCodeImpl implements AcFacultyCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_FCTY_CODE")
    @SequenceGenerator(name = "SQ_AC_FCTY_CODE", sequenceName = "SQ_AC_FCTY_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

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

    public AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcFacultyCode.class;
    }

	@Override
	public List<AcStudent> getStudent() {
		return student;
	}

	@Override
	public void setStudent(List<AcStudent> student) {
		this.student=student;
	}
    
    
}
