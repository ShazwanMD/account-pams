package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorImpl;
import my.edu.umk.pams.account.identity.model.AcStudent;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * @author PAMS
 */
@SuppressWarnings({"unchecked"})
@Repository("sponsorDao")
public class AcSponsorDaoImpl extends GenericDaoSupport<Long, AcSponsor> implements AcSponsorDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcSponsorDaoImpl.class);

    public AcSponsorDaoImpl() {
        super(AcSponsorImpl.class);
    }
    
    @Override
    public AcSponsor findBySponsorNo(String sponsorNo) {
    	Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcSponsor a where " +
                "a.identityNo = :identityNo");
        query.setString("identityNo", sponsorNo);
        return (AcSponsor) query.uniqueResult();
    }
}
