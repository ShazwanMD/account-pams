package my.edu.umk.pams.account.identity.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcFacultyCodeImpl;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Entity(name = "AcStudent")
@Table(name = "AC_STDN")
public class AcStudentImpl extends AcActorImpl implements AcStudent {

    @OneToMany(targetEntity = AcSponsorshipImpl.class, mappedBy = "student")
    private List<AcSponsorship> sponsorships;

    @NotNull
    @OneToOne(targetEntity = AcFacultyCodeImpl.class)
    @JoinColumn(name = "FACULTY_ID")
    private AcFacultyCode facultyCode;
    
    public AcStudentImpl() {
        setActorType(AcActorType.STUDENT);
    }

    @Override
    public String getMatricNo() {
        return getIdentityNo();
    }

    @Override
    public void setMatricNo(String matricNo) {
        setIdentityNo(matricNo);
    }

    @Override
    public List<AcSponsorship> getSponsorships() {
        return sponsorships;
    }

    @Override
    public void setSponsorships(List<AcSponsorship> sponsorships) {
        this.sponsorships = sponsorships;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcStudent.class;
    }

	@Override
	public AcFacultyCode getFacultyCode() {
		return facultyCode;
	}

	@Override
	public void setFacultyCode(AcFacultyCode facultyCode) {
		this.facultyCode=facultyCode;		
	}

}
