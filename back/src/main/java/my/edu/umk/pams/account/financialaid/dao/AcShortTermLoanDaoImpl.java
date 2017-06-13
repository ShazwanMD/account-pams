package my.edu.umk.pams.account.financialaid.dao;

import java.sql.Timestamp;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.financialaid.model.AcShortTermLoan;
import my.edu.umk.pams.account.financialaid.model.AcShortTermLoanImpl;
import my.edu.umk.pams.account.identity.model.AcUser;

@Repository("acShortTermLoanDao")
public class AcShortTermLoanDaoImpl extends GenericDaoSupport<Long, AcShortTermLoan> implements AcShortTermLoanDao {


    private static final Logger LOG = LoggerFactory.getLogger(AcShortTermLoanDaoImpl.class);

    public AcShortTermLoanDaoImpl() {
        super(AcShortTermLoanImpl.class);
    }

    // ====================================================================================================
    // FINDER
    // ====================================================================================================

    @Override
    public AcShortTermLoan findByReferenceNo(String referenceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcShortTermLoan a where " +
                "a.referenceNo = :referenceNo " +
                "and a.metadata.state = :state");
        query.setString("referenceNo", referenceNo);
        query.setCacheable(true);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (AcShortTermLoan) query.uniqueResult();
    }

	@Override
	public List<AcShortTermLoan> find(AcAcademicSession academicSession, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select a from AcShortTermLoan a where " +
                "a.session = :academicSession "+
                "and a.metadata.state = :state");
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcShortTermLoan>) query.list();
	}
	
    // ====================================================================================================
    // CRUD
    // ====================================================================================================

    @Override
    public void addShortTermLoan(AcShortTermLoan shortTermLoan, AcUser user) {
        Validate.notNull(shortTermLoan, "Loan should not be null");

        Session session = sessionFactory.getCurrentSession();
        
        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        shortTermLoan.setMetadata(metadata);
        session.save(shortTermLoan);
    }

    @Override
    public void updateShortTermLoan(AcShortTermLoan shortTermLoan, AcUser user) {
    	Validate.notNull(shortTermLoan, "Loan should not be null");

        Session session = sessionFactory.getCurrentSession();

        AcMetadata metadata = shortTermLoan.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        shortTermLoan.setMetadata(metadata);
        session.update(shortTermLoan);
    }

    @Override
    public void deleteShortTermLoan(AcShortTermLoan shortTermLoan, AcUser user) {
    	Validate.notNull(shortTermLoan, "Loan should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(shortTermLoan);
    }

}
