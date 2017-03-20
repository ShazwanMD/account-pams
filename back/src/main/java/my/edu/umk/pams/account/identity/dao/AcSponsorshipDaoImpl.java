package my.edu.umk.pams.account.identity.dao;

import java.sql.Timestamp;

import org.apache.commons.lang.Validate;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcCoverageImpl;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorImpl;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcSponsorshipImpl;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcUser;

@SuppressWarnings({"unchecked"})
@Repository("sponsorshipDao")
public class AcSponsorshipDaoImpl extends GenericDaoSupport<Long, AcSponsorship> implements AcSponsorshipDao{

	public AcSponsorshipDaoImpl() {
		super(AcSponsorshipImpl.class);
	}

	@Override
	public AcSponsorship findSponsorhipById(Long id) {
		Session session = sessionFactory.getCurrentSession();
        return (AcSponsorship) session.get(AcSponsorshipImpl.class, id);
	}

	@Override
	public void addSponsorship(AcSponsor sponsor, AcSponsorship sponsorship, AcUser user) {
	    Validate.notNull(sponsorship, "Batch should not be null");
        Validate.notNull(sponsor, "student member should not be null");

        Session session = sessionFactory.getCurrentSession();
        sponsorship.setSponsor(sponsor);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        sponsorship.setMetadata(metadata);
        session.save(sponsorship);
	}

}
