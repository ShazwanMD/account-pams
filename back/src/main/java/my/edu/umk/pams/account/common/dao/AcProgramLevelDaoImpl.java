package my.edu.umk.pams.account.common.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import my.edu.umk.pams.account.common.model.AcProgramLevel;
import my.edu.umk.pams.account.common.model.AcProgramLevelImpl;
import my.edu.umk.pams.account.core.GenericDaoSupport;

@Repository("acProgramLevelDao")
public class AcProgramLevelDaoImpl extends GenericDaoSupport<Long, AcProgramLevel> implements AcProgramLevelDao {

    private static final Logger LOG = LoggerFactory.getLogger(AcProgramLevelDaoImpl.class);

    public AcProgramLevelDaoImpl() {
        super(AcProgramLevelImpl.class);
    }
    
    @Override
    public AcProgramLevel findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select r from AcProgramLevel r where r.code = :code");
        query.setString("code", code);
        return (AcProgramLevel) query.uniqueResult();
    }


    @Override
    public List<AcProgramLevel> find(String filter, Integer offset, Integer limit) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select p from AcProgramLevel p where " +
                "(upper(p.code) like upper(:filter) or upper(p.description) like upper(:filter))");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        return (List<AcProgramLevel>) query.list();
    }

    @Override
    public Integer count(String filter) {
        Session currentSession = sessionFactory.getCurrentSession();
        Query query = currentSession.createQuery("select count(p) from AcProgramLevel p " +
                "where upper(p.code) like  upper(:filter)");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }
    
}
