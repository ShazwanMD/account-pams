package my.edu.umk.pams.account.security.service;

import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.identity.model.AcPrincipal;
import my.edu.umk.pams.account.security.integration.AcPermission;
import org.springframework.security.core.Authentication;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author canang technologies
 * @since 4/18/14
 */
public interface AccessService {

    Set<AcPrincipal> findGrants(AcMetaObject object);

    Set<AcPrincipal> findGrants(AcMetaObject object, AcPermission permission);

    boolean checkPermission(AcMetaObject object, AcPrincipal principal, AcPermission permission);

    void grantPermission(AcMetaObject object, AcPrincipal principal, AcPermission permission);

    void inheritPermission(AcMetaObject parent, AcMetaObject object);

    void revokePermission(AcMetaObject object, AcPrincipal principal, AcPermission permission);

    boolean hasPermission(AcMetaObject object, AcPrincipal principal, AcPermission permission);

    boolean hasPermission(AcMetaObject object, Authentication authentication, AcPermission permission);

    Integer countArchivedRecord(String filter, Date startDate, Date endDate, Class<?> aClass);

    Integer countArchivedRecord(String filter, AcFlowState flowType, Date startDate, Date endDate, Class<?> aClass);

    List<Long> findArchived(String filter, AcFlowState flowType, Date startDate, Date endDate, Class<?> aClass, Integer offset, Integer limit);
}
