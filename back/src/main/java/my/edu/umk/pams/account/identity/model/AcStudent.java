package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.common.model.AcCohortCode;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcStudent extends AcActor {

	String getMatricNo();

	void setMatricNo(String matricNo);

	AcStudentStatus getStudentStatus();

	void setStudentStatus(AcStudentStatus studentStatus);

	AcCohortCode getCohortCode();

	void setCohortCode(AcCohortCode cohortCode);

	List<AcSponsorship> getSponsorships();

	void setSponsorships(List<AcSponsorship> sponsorships);

}
