package my.edu.umk.pams.account.identity.dao;

import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
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
 * @author canang technologies
 * @since 1/31/14
 */
@SuppressWarnings({"unchecked"})
@Repository("studentDao")
public class AcStudentDaoImpl extends GenericDaoSupport<Long, AcStudent> implements AcStudentDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcStudentDaoImpl.class);

    public AcStudentDaoImpl() {
        super(AcStudentImpl.class);
    }

    @Override
    public AcStudent findByMatricNo(String matricNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcStudent a where " +
                "a.identityNo = :identityNo");
        query.setString("identityNo", matricNo);
        return (AcStudent) query.uniqueResult();
    }

    @Override
    public AcSponsorship findSponsorshipById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcSponsorship) session.get(AcSponsorshipImpl.class, id);
    }

    @Override
    public List<AcStudent> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStudent s where " +
                "(upper(s.identityNo) like upper(:filter) " +
                "or upper(s.name) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return query.list();
    }

    @Override
    public List<AcStudent> find(AcSponsor sponsor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStudent s join s.sponsorships sp where " +
                "sp.sponsor = :sponsor " +
                "and s.metadata.state = :state ");
        query.setEntity("sponsor", sponsor);
        query.setInteger("state", ACTIVE.ordinal());
        return query.list();
    }

    @Override
    public List<AcSponsorship> findSponsorships(AcStudent student) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcSponsorship s where " +
                "s.student = :student " +
                "and s.metadata.state = :state ");
        query.setEntity("student", student);
        query.setInteger("state", ACTIVE.ordinal());
        return query.list();
    }

	@Override
	public List<AcStudent> findCohort(AcCohortCode cohortCode) {
		Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcStudent s where " +
                "s.cohortCode = :cohortCode " +
                "and s.metadata.state = :state ");
        query.setEntity("cohortCode", cohortCode);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<AcStudent>) query.list();
	}
	
	@Override
	public List<AcSponsorship> find(AcFacultyCode facultyCode) {
		Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcSponsorship s where " +
                "s.student.cohortCode.programCode.facultyCode = :facultyCode " +
                "and s.metadata.state = :state ");
        query.setEntity("facultyCode", facultyCode);
        query.setInteger("state", ACTIVE.ordinal());
        return (List<AcSponsorship>) query.list();
	}
	
    @Override
    public List<AcSponsor> findSponsors(AcStudent student) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s.sponsor from AcStudent s join s.sponsorships sp where " +
                "sp.student = :student " +
                "and s.metadata.state = :state ");
        query.setEntity("student", student);
        query.setInteger("state", ACTIVE.ordinal());
        return query.list();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcStudent s where " +
                "(upper(s.identityNo) like upper(:filter) " +
                "or upper(s.name) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countSponsorship(AcStudent student) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcSponsorship s where " +
                "s.student = :student " +
                "and s.metadata.state = :state ");
        query.setEntity("student", student);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasSponsorship(AcStudent student) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcSponsorship s where " +
                "s.student = :student " +
                "and s.metadata.state = :state ");
        query.setEntity("student", student);
        query.setInteger("state", ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue() >= 1;
    }


    @Override
    public void addSponsorship(AcStudent student, AcSponsorship sponsorship, AcUser user) {
        Validate.notNull(student, "Student should not be null");
        Validate.notNull(sponsorship, "Sponsorship should not be null");

        Session session = sessionFactory.getCurrentSession();
        sponsorship.setStudent(student);
        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(ACTIVE);
        sponsorship.setMetadata(metadata);

        session.save(sponsorship);
    }

    @Override
    public void removeSponsorship(AcStudent student, AcSponsorship sponsorship, AcUser user) {
        Validate.notNull(student, "Student should not be null");
        Validate.notNull(sponsorship, "Sponsorship should not be null");

        Session session = sessionFactory.getCurrentSession();
        AcMetadata metadata = sponsorship.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.INACTIVE);
        sponsorship.setMetadata(metadata);
        session.update(student);
    }





}
