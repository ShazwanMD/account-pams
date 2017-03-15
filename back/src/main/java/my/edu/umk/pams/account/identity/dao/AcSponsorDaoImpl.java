package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorImpl;
import my.edu.umk.pams.account.identity.model.AcUser;
import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

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

    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    @Override
    public void addCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user) {
        Validate.notNull(sponsor, "Batch should not be null");
        Validate.notNull(coverage, "coverage member should not be null");

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
    public void updateCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user) {
        Validate.notNull(sponsor, "Batch should not be null");
        Validate.notNull(coverage, "coverage member should not be null");

        Session session = sessionFactory.getCurrentSession();
        coverage.setSponsor(sponsor);

        AcMetadata metadata = coverage.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        coverage.setMetadata(metadata);
        session.update(coverage);
    }

    @Override
    public void deleteCoverage(AcSponsor sponsor, AcCoverage coverage, AcUser user) {
        Validate.notNull(sponsor, "Batch should not be null");
        Validate.notNull(coverage, "coverage member should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(coverage);
    }

}
