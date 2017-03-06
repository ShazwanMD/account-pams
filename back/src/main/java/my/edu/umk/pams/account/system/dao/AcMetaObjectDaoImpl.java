package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.AcMetaObject;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author canang technologies
 * @since 5/25/14
 */
@SuppressWarnings({"unchecked"})
@Repository("metaObjectDao")
public class AcMetaObjectDaoImpl implements AcMetaObjectDao {

    @Autowired(required = true)
    protected SessionFactory sessionFactory;

    @Override
    public AcMetaObject findMetaObjectById(Class clazz, Long id) {
        return (AcMetaObject) sessionFactory.getCurrentSession().get(clazz, id);
    }
}
