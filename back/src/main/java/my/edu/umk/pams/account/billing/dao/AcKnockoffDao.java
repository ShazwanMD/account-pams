package my.edu.umk.pams.account.billing.dao;

import java.util.List;

import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

public interface AcKnockoffDao extends GenericDao<Long, AcKnockoff> {

	AcKnockoff findByReferenceNo(String referenceNo);
	
	List<AcKnockoff> find(String filter, Integer offset, Integer limit);
	
	boolean hasKnockoff(AcKnockoff knockoff);
	
    void addKnockoff(AcKnockoff knockoff, AcUser user);

    void updateKnockoff(AcKnockoff knockoff, AcUser user);

    void removeKnockoff(AcKnockoff knockoff, AcUser user);
}
