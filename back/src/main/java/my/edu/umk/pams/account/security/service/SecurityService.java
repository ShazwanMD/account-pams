package my.edu.umk.pams.account.security.service;

import my.edu.umk.pams.account.identity.model.AcUser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.security.core.Authentication;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/31/14
 */
public interface SecurityService {

    List<my.edu.umk.pams.account.core.AcMetaObject> find(Authentication authentication, Class<?> clazz, Integer offset, Integer limit);

    Integer count(Authentication authentication, Class<?> clazz);

    AcUser getCurrentUser();

    SessionFactory getSessionFactory();

    Session getCurrentSession();

}
