package my.edu.umk.pams.account.identity.dao;


import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.account.dao.AcChargeCodeDao;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountWaiver;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcSponsorshipImpl;
import my.edu.umk.pams.account.identity.model.AcUser;


@Repository("AcSponsorshipDao")
public class AcSponsorshipDaoImpl extends GenericDaoSupport<Long, AcSponsorship> implements AcSponsorshipDao {

	
	private static final Logger LOG = LoggerFactory.getLogger(AcSponsorshipDaoImpl.class);

    public AcSponsorshipDaoImpl() {
        super(AcSponsorshipImpl.class);
    }
    
    @Override
    public AcSponsorship findSponsorshipById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcSponsorship) session.get(AcSponsorshipImpl.class, id);
    }
    
    @Override
    public List<AcSponsorship> findSponsorships(AcSponsorship sponsorship) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcSponsorship a where " +
                "a.sponsor = :sponsor "+
                "and a.metadata.state = :state");
        query.setEntity("sponsorship", sponsorship);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcSponsorship>) query.list();
    }
    
    @Override
    public List<AcSponsorship> find(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcSponsorship s where " +
                "s.account= :account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (List<AcSponsorship>) query.list();
    }
    

    @Override
    public List<AcSponsorship> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select i from AcSponsorship i where " +
                "(upper(i.code) like upper(:filter) " +
                "or upper(i.description) like upper(:filter)) " +
                "and i.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcSponsorship>) query.list();
    }
    
    @Override
    public void addSponsorship(AcAccount account, AcSponsorship sponsorship, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(sponsorship, "Sponsorship should not be null");

        Session session = sessionFactory.getCurrentSession();
        sponsorship.setAccount(account);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        sponsorship.setMetadata(metadata);

        session.save(sponsorship);
    }



//    @Override
//    public Integer count(String filter) {
//        Session session = sessionFactory.getCurrentSession();
//        Query query = session.createQuery("select count(i) from AcChargeCode i where " +
//                "(upper(i.code) like upper(:filter) " +
//                "or upper(i.description) like upper(:filter)) " +
//                "and i.metadata.state = :state ");
//        query.setString("filter", WILDCARD + filter + WILDCARD);
//        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
//        return ((Long) query.uniqueResult()).intValue();
//    }

}
