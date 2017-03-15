package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcUser;

/**
 * @author PAMS
 */
public interface AcSponsorDao extends GenericDao<Long, AcSponsor>{
	
	AcSponsor findBySponsorNo(String sponsorNo);

	// ====================================================================================================
	// CRUD
	// ====================================================================================================

	void addCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user);

	void updateCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user);

	void deleteCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user);

}
