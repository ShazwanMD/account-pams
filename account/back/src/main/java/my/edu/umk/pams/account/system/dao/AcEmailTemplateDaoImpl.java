package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDaoSupport;
import my.edu.umk.pams.account.system.model.AcEmailTemplate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author canang technologies
 * @since 9/4/2016.
 */
@Repository("emailTemplateDao")
public class AcEmailTemplateDaoImpl extends GenericDaoSupport<Long, AcEmailTemplate> implements AcEmailTemplateDao {

    public AcEmailTemplateDaoImpl() {
        super(my.edu.umk.pams.account.system.model.AcEmailTemplateImpl.class);
    }

    @Override
    public AcEmailTemplate findByCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("select s from AcEmailTemplate s where " +
                "s.code = :code and  " +
                " s.metadata.state = :state");
        query.setString("code", code);
        query.setInteger("state", my.edu.umk.pams.account.core.AcMetaState.ACTIVE.ordinal());
        query.setCacheable(true);
        return (AcEmailTemplate) query.uniqueResult();
    }

    @Override
    public Integer count(String filter) {
        return null;
    }

    @Override
    public List<AcEmailTemplate> find(String filter) {
        return null;
    }

    @Override
    public List<AcEmailTemplate> find(String filter, Integer offset, Integer limit) {
        return null;
    }
}
