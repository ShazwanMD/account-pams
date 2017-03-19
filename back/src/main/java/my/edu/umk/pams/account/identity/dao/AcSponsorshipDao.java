package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcSponsorshipDao extends GenericDao<Long, AcSponsorship>{

	AcSponsorship findSponsorhipById(Long id);
	
	void addSponsorship(AcSponsor sponsor, AcSponsorship sponsorship, AcUser user);
}
