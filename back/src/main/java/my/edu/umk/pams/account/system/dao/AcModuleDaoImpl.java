package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.system.model.AcModule;
import my.edu.umk.pams.account.system.model.AcModuleImpl;
import my.edu.umk.pams.account.system.model.AcSubModule;
import org.apache.commons.lang3.Validate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 * @author canang technologies
 * @since 4/18/14
 */
@SuppressWarnings({"unchecked"})
@Repository("moduleDao")
public final class AcModuleDaoImpl extends GenericDaoSupport<Long, AcModule> implements AcModuleDao {

    public AcModuleDaoImpl() {
        super(AcModuleImpl.class);
    }

    @Override
    public AcModule findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select c from AcModule c where c.code = :code");
        query.setString("code", code);
        query.setCacheable(true);
        return (AcModule) query.uniqueResult();
    }

    @Override
    public Integer count(String filter) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(s) from AcModule s where " +
                "upper(s.code) like upper(:filter) " +
                "or upper(s.description) like upper(:filter)");
        query.setString("filter", WILDCARD + filter + WILDCARD);
        return ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isExists(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AcModule c where " +
                "c.code = :code");
        query.setString("code", code);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public boolean isSubModuleExists(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select count(*) from AdSubModule c where " +
                "c.code = :code");
        query.setString("code", code);
        return 0 < ((Long) query.uniqueResult()).intValue();
    }

    @Override
    public void addSubModule(AcModule module, AcSubModule subModule, AcUser user) {
        Validate.notNull(module, "Module should not be null");
        Validate.notNull(subModule, "SubModule member should not be null");

        Session session = sessionFactory.getCurrentSession();
        subModule.setModule(module);
        session.save(subModule);
    }

    @Override
    public void updateSubModule(AcModule module, AcSubModule subModule, AcUser user) {
        Validate.notNull(module, "Module should not be null");
        Validate.notNull(subModule, "SubModule member should not be null");

        Session session = sessionFactory.getCurrentSession();
        subModule.setModule(module);
        session.update(subModule);
    }

    @Override
    public void removeSubModule(AcModule module, AcSubModule subModule, AcUser user) {
        Validate.notNull(module, "Module should not be null");
        Validate.notNull(subModule, "SubModule member should not be null");

        Session session = sessionFactory.getCurrentSession();
        session.delete(subModule);
    }
}
