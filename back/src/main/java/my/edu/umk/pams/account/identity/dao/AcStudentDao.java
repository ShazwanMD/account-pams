package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcGuardian;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcStudentDao extends GenericDao<Long, AcStudent> {

	AcStudent findByMatricNo(String matricNo);

	AcSponsorship findSponsorshipById(Long id);

	List<AcStudent> find(String filter, Integer offset, Integer limit);

	List<AcStudent> find(AcSponsor sponsor);

	List<AcSponsorship> find(AcStudent student);
	
	List<AcSponsorship> findSponsorships(AcProgramCode programCode);

	List<AcSponsorship> findSponsorship(AcFacultyCode facultyCode);

	List<AcStudent> findByCohortCode(AcCohortCode cohortCode);
	
	List<AcStudent> findByFacultyCode(AcFacultyCode facultyCode);
	
	List<AcStudent> findBySponsor(AcSponsor sponsor);

	List<AcSponsor> findSponsors(AcStudent student);

	Integer count(String filter);

	Integer countSponsorship(AcStudent student);

	boolean hasSponsorship(AcStudent student);

	// ====================================================================================================
	// CRUD
	// ====================================================================================================

	void addSponsorship(AcStudent student, AcSponsorship sponsorship, AcUser user);

	void removeSponsorship(AcStudent student, AcSponsorship sponsorship, AcUser user);

	void addGuardian(AcStudent student, AcGuardian guardian, AcUser user);

	void removeGuardian(AcStudent student, AcGuardian guardian, AcUser user);

}
