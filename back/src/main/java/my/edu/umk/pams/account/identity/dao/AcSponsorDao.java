package my.edu.umk.pams.account.identity.dao;

import java.util.List;

import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcStudent;

/**
 * @author PAMS
 */
public interface AcSponsorDao extends GenericDao<Long, AcSponsor>{
	
	AcSponsor findBySponsorNo(String sponsorNo);
}
