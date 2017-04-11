package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.common.model.AcCohortCode;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcStudent extends AcActor {

	/**
	 *
	 * @return
	 */
	String getMatricNo();

	void setMatricNo(String matricNo);

	/**
	 *
	 * @return
	 */
	AcStudentStatus getStudentStatus();

	void setStudentStatus(AcStudentStatus studentStatus);

	/**
	 *
	 * @return
	 */
	AcCohortCode getCohortCode();

	void setCohortCode(AcCohortCode cohortCode);

	/**
	 *
	 * @return
	 */
	List<AcSponsorship> getSponsorships();

	void setSponsorships(List<AcSponsorship> sponsorships);

}
