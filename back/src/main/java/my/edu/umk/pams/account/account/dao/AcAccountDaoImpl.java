package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.core.AcMetaState;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.model.AcUser;

import org.apache.commons.lang.Validate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.type.StandardBasicTypes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author PAMS
 */
@Repository("acAccountDao")
public class AcAccountDaoImpl extends GenericDaoSupport<Long, AcAccount> implements AcAccountDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcAccountDaoImpl.class);

    public AcAccountDaoImpl() {
        super(AcAccountImpl.class);
    }

    @Override
    public AcAccount findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccount sa where sa.code = :code and  " +
                " sa.metadata.state = :state");
        query.setString("code", code);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (AcAccount) query.uniqueResult();
    }

    @Override
    public AcAccount findByNricNo(String nricNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccount sa where " +
                "sa.student.nricNo = :nricNo " +
                "and sa.metadata.state = :state");
        query.setString("nricNo", nricNo);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (AcAccount) query.uniqueResult();
    }

    @Override
    public AcAccount findByActor(AcActor actor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccount sa where sa.actor = :actor ");
        query.setEntity("actor", actor);
        return (AcAccount) query.uniqueResult();
    }

    @Override
    public AcAccountTransaction findAccountTransactionById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (AcAccountTransaction) session.get(AcAccountTransactionImpl.class, id);
    }

    @Override
    public List<AcAccount> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccount s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.actor.name) like upper(:filter)" +
                "or upper(s.actor.identityNo) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccount>) query.list();
    }

    @Override
    public List<AcAccount> find(String filter, AcActorType actorType, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcAccount s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.actor.name) like upper(:filter)" +
                "or upper(s.actor.identityNo) like upper(:filter)) " +
                "and s.actor.actorType = :actorType " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("actorType", actorType.ordinal());
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setCacheable(true);
        return (List<AcAccount>) query.list();
    }

    @Override
    public List<AcAccountTransaction> findAccountTransactions(String sourceNo, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa.account from AcAccountTransaction sa inner join sa.account where " +
                "sa.account = :account " +
                "and sa.sourceNo = :sourceNo " +
                "and sa.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcAccountTransaction>) query.list();
    }

    @Override
    public List<AcAccountTransaction> findAccountTransactions(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountTransaction sa where " +
                "sa.account = :account " +
                "and sa.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return (List<AcAccountTransaction>) query.list();
    }

    @Override
    public List<AcAccountTransaction> findAccountTransactions(AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountTransaction sa where " +
                "sa.account = :account " +
                "and sa.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcAccountTransaction>) query.list();
    }

    @Override
    public List<AcAccountTransaction> findAccountTransactions(AcAcademicSession academicSession, AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountTransaction sa where " +
                "sa.account = :account " +
                "and sa.session = :academicSession " +
                "and sa.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcAccountTransaction>) query.list();
    }

    @Override
    public List<AcAccountTransaction> findAccountTransactions(String filter, AcAccount account, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sa from AcAccountTransaction sa where " +
                "sa.account = :account " +
                "and sa.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcAccountTransaction>) query.list();
    }

    @Override
    public List<AcAccountActivity> findAccountActivities(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT \n" +
                "  SOURCE_NO as sourceNo, \n" +
                "  TRANSACTION_CODE as transactionCodeOrdinal, \n" +
                "  SUM(AMOUNT) as amount \n" +
                "FROM AC_ACCT_TRSN\n" +
                "GROUP BY \n" +
                "  SOURCE_NO, \n" +
                "  TRANSACTION_CODE");
        sqlQuery.addScalar("sourceNo", StandardBasicTypes.STRING);
        sqlQuery.addScalar("transactionCodeOrdinal", StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("amount", StandardBasicTypes.BIG_DECIMAL);
        sqlQuery.setResultTransformer(new AliasToBeanResultTransformer(AcAccountActivityImpl.class));
        List<AcAccountActivity> results = sqlQuery.list();
        // unpack id
        for (AcAccountActivity holder : results) {
            holder.setTransactionCode(AcAccountTransactionCode.get(holder.getTransactionCodeOrdinal()));
        }
        return results;
    }

    @Override
    public List<AcAccountActivity> findAccountActivities(AcAcademicSession academicSession, AcAccount account) {
        // todo(hajar): tambah academicSession code in query
        Session session = sessionFactory.getCurrentSession();
        SQLQuery sqlQuery = session.createSQLQuery("SELECT \n" +
                "  SOURCE_NO as sourceNo, \n" +
                "  TRANSACTION_CODE as transactionCodeOrdinal, \n" +
                "  SUM(AMOUNT) as amount \n" +
                "FROM AC_ACCT_TRSN\n" +
                "INNER JOIN AC_ACDM_SESN ON AC_ACCT_TRSN.SESSION_ID = AC_ACDM_SESN.ID" +
                "GROUP BY \n" +
                "  SOURCE_NO, \n" +
                "  TRANSACTION_CODE");
        sqlQuery.addScalar("sourceNo", StandardBasicTypes.STRING);
        sqlQuery.addScalar("transactionCodeOrdinal", StandardBasicTypes.INTEGER);
        sqlQuery.addScalar("amount", StandardBasicTypes.BIG_DECIMAL);
        sqlQuery.addScalar("academicSession", StandardBasicTypes.STRING);
        sqlQuery.setResultTransformer(new AliasToBeanResultTransformer(AcAccountActivityImpl.class));
        List<AcAccountActivity> results = sqlQuery.list();
        // unpack id
        for (AcAccountActivity holder : results) {
            holder.setTransactionCode(AcAccountTransactionCode.get(holder.getTransactionCodeOrdinal()));
        }
        return results;
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccount s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.actor.name) like upper(:filter)" +
                "or upper(s.actor.identityNo) like upper(:filter)) " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer count(String filter, AcActorType actorType) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccount s where " +
                "(upper(s.code) like upper(:filter) " +
                "or upper(s.actor.name) like upper(:filter)" +
                "or upper(s.actor.identityNo) like upper(:filter)) " +
                "and s.actor.actorType = :actorType " +
                "and s.metadata.state = :state ");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setInteger("actorType", actorType.ordinal());
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countAccountTransaction(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountTransaction s where " +
                "s.account=:account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countAccountTransaction(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountTransaction s where " +
                "s.account=:account " +
                "and s.session = :academicSession " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public Integer countAccountTransaction(String filter, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcAccountTransaction s where " +
                "s.account=:account " +
                "and s.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public BigDecimal sumBalanceAmount(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(sat.amount) from AcAccountTransaction sat where " +
                "sat.account = :account " +
                "and sat.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }

    @Override
    public BigDecimal sumSurplusAmount(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(sat.amount) from AcAccountTransaction sat where " +
                "sat.account = :account " +
                "and sat.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else {
            if (((BigDecimal) result).compareTo(BigDecimal.ZERO) < 0) return (BigDecimal) result;
            else return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal sumDebitAmount(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(sat.amount) from AcAccountTransaction sat where " +
                "sat.account = :account and sat.amount > 0 " +
                "and sat.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else {
            if (((BigDecimal) result).compareTo(BigDecimal.ZERO) > 0) return (BigDecimal) result;
            else return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal sumCreditAmount(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(sat.amount) from AcAccountTransaction sat where " +
                "sat.account = :account and sat.amount < 0 " +
                "and sat.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else {
            if (((BigDecimal) result).compareTo(BigDecimal.ZERO) < 0) return (BigDecimal) result;
            else return BigDecimal.ZERO;
        }
    }

    @Override
    public BigDecimal sumWaiverAmount(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(w.amount) from AcAccountWaiver w where " +
                "w.account = :account " +
                "and w.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }

    @Override
    public BigDecimal sumAccountTransaction(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.amount) from AcAccountTransaction a where " +
                "a.account = :account " +
                "and a.metadata.state = :state ");
        query.setEntity("account", account);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }

    @Override
    public BigDecimal sumAccountTransaction(AcAccount account, AcAcademicSession academicSession) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(a.amount) from AcAccountTransaction a where " +
                "a.account = :account " +
                "and a.session = :session " +
                "and a.metadata.state = :state ");
        query.setEntity("account", account);
        query.setEntity("session", academicSession);
        query.setInteger("state", AcMetaState.ACTIVE.ordinal());
        Object result = query.uniqueResult();
        if (null == result) return BigDecimal.ZERO;
        else return (BigDecimal) result;
    }

    @Override
    public boolean hasAccount(AcActor actor) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcAccount u where " +
                "u.actor = :actor");
        query.setEntity("actor", actor);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasAccountTransaction(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcAccountTransaction u where " +
                "u.account = :account");
        query.setEntity("account", account);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasAccountTransaction(AcAcademicSession academicSession, AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcAccountTransaction u where " +
                "u.account = :account " +
                "and u.session = :academicSession ");
        query.setEntity("account", account);
        query.setEntity("academicSession", academicSession);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasAccountTransaction(String sourceNo) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcAccountTransaction u where " +
                "u.sourceNo = :sourceNo");
        query.setString("sourceNo", sourceNo);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean hasSurplus(AcAccount account) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select sum(sat.amount) from AcAccountTransaction sat where " +
                "sat.account = :account ");
        query.setEntity("account", account);
        Object result = query.uniqueResult();
        if (null == result) return false;
        else {
            return ((BigDecimal) result).compareTo(BigDecimal.ZERO) < 0;
        }
    }

    @Override
    public boolean hasBalance(AcAcademicSession academicSession, AcActor actor) {
        return false; // TODO: what is has balance???
    }

    @Override
    public void addCharge(AcAccount account, AcAccountCharge charge, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(charge, "Charge should not be null");

        Session session = sessionFactory.getCurrentSession();
        charge.setAccount(account);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        charge.setMetadata(metadata);
        session.save(charge);
    }

    @Override
    public void updateCharge(AcAccount account, AcAccountCharge charge, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(charge, "Charge should not be null");

        Session session = sessionFactory.getCurrentSession();
        charge.setAccount(account);

        AcMetadata metadata = charge.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        charge.setMetadata(metadata);
        session.update(charge);
    }

    @Override
    public void deleteCharge(AcAccount account, AcAccountCharge charge, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(charge, "Charge should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(charge);
    }

    @Override
    public void addWaiver(AcAccount account, AcAcademicSession academicSession, AcAccountWaiver waiver, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(waiver, "Waiver should not be null");

        Session session = sessionFactory.getCurrentSession();
        waiver.setAccount(account);
        waiver.setSession(academicSession);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        waiver.setMetadata(metadata);
        session.save(waiver);
    }

    @Override
    public void deleteWaiver(AcAccount account, AcAcademicSession academicSession, AcAccountWaiver waiver, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(waiver, "Waiver should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(waiver);
    }

    @Override
    public void addTransaction(AcAccount account, AcAccountTransaction transaction, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(transaction, "Transaction should not be null");

        Session session = sessionFactory.getCurrentSession();
        transaction.setAccount(account);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        transaction.setMetadata(metadata);
        session.save(transaction);
    }

    @Override
    public void updateAccountTransaction(AcAccount account, AcAccountTransaction transaction, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(transaction, "Transaction should not be null");

        Session session = sessionFactory.getCurrentSession();
        transaction.setAccount(account);

        AcMetadata metadata = transaction.getMetadata();
        metadata.setModifiedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setModifierId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        transaction.setMetadata(metadata);

        session.update(transaction);
    }

    @Override
    public void deleteTransaction(AcAccount account, AcAccountTransaction transaction, AcUser user) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(transaction);
    }

    @Override
    public void addShortTermLoan(AcAccount account, AcAcademicSession academicSession, AcAccountSTL shortTermLoan, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(shortTermLoan, "Waiver should not be null");

        Session session = sessionFactory.getCurrentSession();
        shortTermLoan.setAccount(account);
        shortTermLoan.setSession(academicSession);

        AcMetadata metadata = new AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(user.getId());
        metadata.setState(AcMetaState.ACTIVE);
        shortTermLoan.setMetadata(metadata);
        session.save(shortTermLoan);
    }

    @Override
    public void deleteShortTermLoan(AcAccount account, AcAcademicSession academicSession, AcAccountSTL shortTermLoan, AcUser user) {
        Validate.notNull(account, "Account should not be null");
        Validate.notNull(shortTermLoan, "Waiver should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(shortTermLoan);
    }

    // todo(uda): vo account tx

    //    @Override
//    public List<AcAccountActivity> findAccountActivities(AcAccount account) {
//        org.hibernate.classic.Session session = sessionFactory.getCurrentSession();
//        SQLQuery query = session.createSQLQuery("SELECT   " +
//                "  SOURCE_NO as sourceNo,   " +
//                "  DEBIT as debit,   " +
//                "  CREDIT as credit,   " +
//                "  REFUND as refund    " +
//                "FROM (   " +
//                "  SELECT   " +
//                "    AAT.SOURCE_NO AS SOURCE_NO,   " +
//                "    AAT.AMOUNT    AS DEBIT,   " +
//                "    0             AS CREDIT,   " +
//                "    0             AS REFUND   " +
//                "  FROM AC_ACCT_TRSN AAT   " +
//                "    INNER JOIN AC_ACCT AA ON AA.ID = AAT.ACCOUNT_ID   " +
//                "    INNER JOIN AC_ACDM_SESN SAS ON SAS.ID = AAT.SESSION_ID   " +
//                "  WHERE   " +
//                "    AAT.AMOUNT > 0   " +
//                "    AND AAT.TRANSACTION_CODE = 1 -- VOUCHER   " +
//                "    AND AA.ID = :accountId   " +
//                "    AND AAT.M_ST = :metaState   " +
//                "  UNION   " +
//                "  SELECT   " +
//                "    AAT.SOURCE_NO  AS SOURCENO,   " +
//                "    0              AS DEBIT,   " +
//                "    AAT.AMOUNT *-1 AS CREDIT,   " +
//                "    0              AS REFUND   " +
//                "  FROM AC_ACCT_TRSN AAT   " +
//                "    INNER JOIN AC_ACCT AA ON AA.ID = AAT.ACCOUNT_ID   " +
//                "    INNER JOIN AC_ACDM_SESN SAS ON SAS.ID = AAT.SESSION_ID   " +
//                "  WHERE   " +
//                "    AAT.AMOUNT < 0   " +
//                "    AND AAT.TRANSACTION_CODE = 0 -- RECEIPT   " +
//                "    AND AA.ID = :accountId   " +
//                "    AND AAT.M_ST = :metaState   " +
//                "  UNION   " +
//                "  SELECT   " +
//                "    AAT.SOURCE_NO      AS SOURCENO,   " +
//                "    0                  AS DEBIT,   " +
//                "    0                  AS CREDIT,   " +
//                "    AAT.AMOUNT         AS REFUND   " +
//                "  FROM AC_ACCT_TRSN AAT   " +
//                "    INNER JOIN AC_ACCT AA ON AA.ID = AAT.ACCOUNT_ID   " +
//                "    INNER JOIN AC_ACDM_SESN SAS ON SAS.ID = AAT.SESSION_ID   " +
//                "  WHERE   " +
//                "    AAT.AMOUNT > 0   " +
//                "    AND AAT.TRANSACTION_CODE = 2 -- REFUND   " +
//                "    AND AA.ID = :accountId   " +
//                "    AND AAT.M_ST = :metaState   " +
//                ")");
//
//        query.setLong("accountId", account.getId());
//        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
//
//        query.addScalar("sourceNo", Hibernate.STRING);
//        query.addScalar("debit", Hibernate.BIG_DECIMAL);
//        query.addScalar("credit", Hibernate.BIG_DECIMAL);
//        query.addScalar("refund", Hibernate.BIG_DECIMAL);
//        query.setResultTransformer(new AliasToBeanResultTransformer(AcAccountActivity.class));
//        List<AcAccountActivity> results = query.list();
//        return results;
//    }
//
//    @Override
//    public List<AcAccountActivity> findAccountActivities(AcAcademicSession academicSession, AcAccount account) {
//        org.hibernate.classic.Session session = sessionFactory.getCurrentSession();
//        SQLQuery query = session.createSQLQuery("SELECT   " +
//                "  SOURCE_NO as sourceNo,   " +
//                "  DEBIT as debit,   " +
//                "  CREDIT as credit,   " +
//                "  REFUND as refund    " +
//                "FROM (   " +
//                "  SELECT   " +
//                "    AAT.SOURCE_NO  AS SOURCE_NO,   " +
//                "    AAT.AMOUNT     AS DEBIT,   " +
//                "    0              AS CREDIT,   " +
//                "    0              AS REFUND   " +
//                "  FROM AC_ACCT_TRSN AAT   " +
//                "    INNER JOIN AC_ACCT AA ON AA.ID = AAT.ACCOUNT_ID   " +
//                "    INNER JOIN AC_ACDM_SESN SAS ON SAS.ID = AAT.SESSION_ID   " +
//                "  WHERE   " +
//                "    AAT.AMOUNT > 0   " +
//                "    AND AAT.TRANSACTION_CODE = 1 -- VOUCHER   " +
//                "    AND SAS.ID = :sessionId   " +
//                "    AND AA.ID = :accountId   " +
//                "    AND AAT.M_ST = :metaState   " +
//                "  UNION   " +
//                "  SELECT   " +
//                "    AAT.SOURCE_NO SOURCENO,   " +
//                "    0                AS DEBIT,   " +
//                "    AAT.AMOUNT * -1  AS  CREDIT,   " +
//                "    0                AS REFUND    " +
//                "  FROM AC_ACCT_TRSN AAT   " +
//                "    INNER JOIN AC_ACCT AA ON AA.ID = AAT.ACCOUNT_ID   " +
//                "    INNER JOIN AC_ACDM_SESN SAS ON SAS.ID = AAT.SESSION_ID   " +
//                "  WHERE   " +
//                "    AAT.AMOUNT < 0   " +
//                "    AND AAT.TRANSACTION_CODE = 0 -- RECEIPT   " +
//                "    AND SAS.ID = :sessionId   " +
//                "    AND AA.ID = :accountId   " +
//                "    AND AAT.M_ST = :metaState   " +
//                "  UNION   " +
//                "  SELECT   " +
//                "    AAT.SOURCE_NO  AS SOURCENO,   " +
//                "    0              AS DEBIT,   " +
//                "    0              AS CREDIT,   " +
//                "    AAT.AMOUNT     AS REFUND   " +
//                "  FROM AC_ACCT_TRSN AAT   " +
//                "    INNER JOIN AC_ACCT AA ON AA.ID = AAT.ACCOUNT_ID   " +
//                "    INNER JOIN AC_ACDM_SESN SAS ON SAS.ID = AAT.SESSION_ID   " +
//                "  WHERE   " +
//                "    AAT.AMOUNT > 0   " +
//                "    AND AAT.TRANSACTION_CODE = 2 -- REFUND   " +
//                "    AND SAS.ID = :sessionId   " +
//                "    AND AA.ID = :accountId   " +
//                "    AND AAT.M_ST = :metaState   " +
//                ")");
//
//        query.setLong("sessionId", academicSession.getId());
//        query.setLong("accountId", account.getId());
//        query.setInteger("metaState", AcMetaState.ACTIVE.ordinal());
//
//        query.addScalar("sourceNo", Hibernate.STRING);
//        query.addScalar("debit", Hibernate.BIG_DECIMAL);
//        query.addScalar("credit", Hibernate.BIG_DECIMAL);
//        query.addScalar("refund", Hibernate.BIG_DECIMAL);
//        query.setResultTransformer(new AliasToBeanResultTransformer(AcAccountActivity.class));
//        List<AcAccountActivity> results = query.list();
//        return results;
//    }

}
