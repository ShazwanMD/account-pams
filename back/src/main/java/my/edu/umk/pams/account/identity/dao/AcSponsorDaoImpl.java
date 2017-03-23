package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.*;
import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

import static my.edu.umk.pams.account.core.AcMetaState.ACTIVE;

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

    @Override
    public AcCoverage findCoverageById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcCoverage) session.get(AcCoverageImpl.class, id);
    }

    @Override
    public AcSponsorship findSponsorshipById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcSponsorship) session.get(AcSponsorshipImpl.class, id);
    }

    @Override
    public List<AcSponsor> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcSponsor s where " +
                "(upper(s.identityNo) like upper(:filter) " +
                "or upper(s.name) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return query.list();
    }

    @Override
    public List<AcCoverage> findCoverages(AcSponsor sponsor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcCoverage a where " +
                "a.sponsor = :sponsor "+
                "and a.metadata.state = :state");
        query.setEntity("sponsor", sponsor);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcCoverage>) query.list();
    }

    @Override
    public List<AcSponsorship> findSponsorships(AcSponsor sponsor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcSponsorship a where " +
                "a.sponsor = :sponsor "+
                "and a.metadata.state = :state");
        query.setEntity("sponsor", sponsor);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcSponsorship>) query.list();
    }

    @Override
    public Integer countCoverage(AcSponsor sponsor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from AcCoverage c where " +
                "c.sponsor = :sponsor " +
                "and c.metadata.state = :state ");
        query.setEntity("sponsor", sponsor);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countSponsorship(AcSponsor sponsor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcSponsorship s where " +
                "s.sponsor = :sponsor " +
                "and s.metadata.state = :state ");
        query.setEntity("sponsor", sponsor);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasSponsorship(AcSponsor sponsor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcSponsorship s where " +
                "s.sponsor = :sponsor " +
                "and s.metadata.state = :state ");
        query.setEntity("sponsor", sponsor);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() >= 1;
    }

    @Override
    public boolean hasCoverage(AcSponsor sponsor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(c) from AcCoverage c where " +
                "c.sponsor = :sponsor " +
                "and c.metadata.state = :state ");
        query.setEntity("sponsor", sponsor);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() >= 1;
    }

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    @Override
    public void addCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user) {
        Validate.notNull(sponsor, "Sponosr should not be null");
        Validate.notNull(coverage, "Coverage member should not be null");

        Session session = sessionFactory.getCurrentSession();
        coverage.setSponsor(sponsor);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        coverage.setMetadata(metadata);
        session.save(coverage);
    }

    @Override
    public void deleteCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user) {
        Validate.notNull(sponsor, "Sponsor should not be null");
        Validate.notNull(coverage, "Coverage should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(coverage);
    }

    @Override
    public void addSponsorship(AcSponsor sponsor, AcSponsorship sponsorship, AcUser user) {
        Validate.notNull(sponsor, "Sponsor should not be null");
        Validate.notNull(sponsorship, "Sponsorship should not be null");

        Session session = sessionFactory.getCurrentSession();
        sponsorship.setSponsor(sponsor);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        sponsorship.setMetadata(metadata);

        session.save(sponsorship);
    }

    @Override
    public void removeSponsorship(AcSponsor sponsor, AcSponsorship sponsorship, AcUser user) {
        Validate.notNull(sponsor, "Sponsor should not be null");
        Validate.notNull(sponsorship, "Sponsorship should not be null");

        Session session = sessionFactory.getCurrentSession();
        AcMetadata metadata = sponsorship.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.INACTIVE);
        sponsorship.setMetadata(metadata);
        session.update(sponsor);
    }
}
