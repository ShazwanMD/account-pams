package my.edu.umk.pams.account.security.service;

import my.edu.umk.pams.account.security.integration.AdPermission;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 4/18/14
 */
public interface AccessService {

    Set<my.edu.umk.pams.account.identity.model.AcPrincipal> findGrants(my.edu.umk.pams.account.core.AcMetaObject object);

    Set<my.edu.umk.pams.account.identity.model.AcPrincipal> findGrants(my.edu.umk.pams.account.core.AcMetaObject object, AdPermission permission);

    boolean checkPermission(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AdPermission permission);

    void grantPermission(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AdPermission permission);

    void inheritPermission(my.edu.umk.pams.account.core.AcMetaObject parent, my.edu.umk.pams.account.core.AcMetaObject object);

    void revokePermission(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AdPermission permission);

    boolean hasPermission(my.edu.umk.pams.account.core.AcMetaObject object, my.edu.umk.pams.account.identity.model.AcPrincipal principal, AdPermission permission);

    boolean hasPermission(my.edu.umk.pams.account.core.AcMetaObject object, Authentication authentication, AdPermission permission);

    Integer countArchivedRecord(String filter, Date startDate, Date endDate, Class<?> aClass);

    Integer countArchivedRecord(String filter, my.edu.umk.pams.account.core.AcFlowState flowType, Date startDate, Date endDate, Class<?> aClass);

    List<Long> findArchived(String filter, my.edu.umk.pams.account.core.AcFlowState flowType, Date startDate, Date endDate, Class<?> aClass, Integer offset, Integer limit);
}
