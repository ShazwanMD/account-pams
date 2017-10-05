package my.edu.umk.pams.account.identity.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcGuardian")
@Table(name = "AC_GRDN")
public class AcGuardianImpl implements AcGuardian {

	@Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_GRDN")
    @SequenceGenerator(name = "SQ_AC_GRDN", sequenceName = "SQ_AC_GRDN", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;
    
    @NotNull
    @Column(name = "PHONE", nullable = true)
    private String phone;

	@NotNull
    @Column(name = "IDENTITY_NO", nullable = false)
    private String identityNo;

    @NotNull
    @Column(name = "GUARDIAN_TYPE")
    private AcGuardianType type;
    
    @ManyToOne(targetEntity = AcStudentImpl.class)
    @JoinColumn(name = "STUDENT_ID")
    private AcStudent student;

    @Embedded
    private AcMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public AcGuardianType getType() {
        return type;
    }

    @Override
    public void setType(AcGuardianType type) {
        this.type = type;
    }

    @Override
    public String getIdentityNo() {
        return identityNo;
    }

    @Override
    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    @Override
    public AcStudent getStudent() {
        return student;
    }

    @Override
    public void setStudent(AcStudent student) {
        this.student = student;
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
        return AcGuardian.class;
    }
}
