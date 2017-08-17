package my.edu.umk.pams.account.identity.dao;

import java.util.List;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;

public interface AcSponsorshipDao extends GenericDao<Long, AcSponsorship> {
	
	// ====================================================================================================
    // FINDER
    // ====================================================================================================
	
	AcSponsorship findSponsorshipById(Long id);
	
	List<AcSponsorship> findSponsorships(AcSponsorship sponsorship);

    List<AcSponsorship> find(String filter, Integer offset, Integer limit);
    
    List<AcSponsorship> find(AcAccount account);


    // ====================================================================================================
    // HELPER
    // ====================================================================================================

    //Integer count(String filter);


}
