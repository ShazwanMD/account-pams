package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcSponsor;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcAccountDao extends GenericDao<Long, AcAccount> {

    // find account, given a sponsor
    List<AcAccount> find(AcSponsor sponsor);
}
