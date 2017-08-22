package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcCohortCodeImpl;
import my.edu.umk.pams.account.common.model.AcResidencyCode;
import my.edu.umk.pams.account.common.model.AcResidencyCodeImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author PAMS
 */
@Entity(name = "AcStudent")
@Table(name = "AC_STDN")
public class AcStudentImpl extends AcActorImpl implements AcStudent {

//	@OneToMany(targetEntity = AcSponsorshipImpl.class, mappedBy = "student")
//	private List<AcSponsorship> sponsorships;

	@Column(name = "STUDENT_STATUS")
	private AcStudentStatus studentStatus;

	@NotNull
	@OneToOne(targetEntity = AcCohortCodeImpl.class)
	@JoinColumn(name = "COHORT_CODE_ID", nullable = true) // todo(uda): set to false
	private AcCohortCode cohortCode;

	@NotNull
	@OneToOne(targetEntity = AcResidencyCodeImpl.class)
	@JoinColumn(name = "RESIDENCY_CODE_ID", nullable = true) // todo(uda): set to false
	private AcResidencyCode residencyCode;

	public AcStudentImpl() {
		super();
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
	public AcStudentStatus getStudentStatus() {
		return studentStatus;
	}

	@Override
	public void setStudentStatus(AcStudentStatus studentStatus) {
		this.studentStatus = studentStatus;
	}

	@Override
	public AcCohortCode getCohortCode() {
		return cohortCode;
	}

	@Override
	public void setCohortCode(AcCohortCode cohortCode) {
		this.cohortCode = cohortCode;
	}

	@Override
	public AcResidencyCode getResidencyCode() {
		return residencyCode;
	}

	public void setResidencyCode(AcResidencyCode residencyCode) {
		this.residencyCode = residencyCode;
	}

//	@Override
//	public List<AcSponsorship> getSponsorships() {
//		return sponsorships;
//	}
//
//	@Override
//	public void setSponsorships(List<AcSponsorship> sponsorships) {
//		this.sponsorships = sponsorships;
//	}

	@Override
	public Class<?> getInterfaceClass() {
		return AcStudent.class;
	}
}
