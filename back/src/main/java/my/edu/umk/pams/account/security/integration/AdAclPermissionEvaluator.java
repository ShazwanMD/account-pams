package my.edu.umk.pams.account.security.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.ObjectIdentityRetrievalStrategyImpl;
import org.springframework.security.acls.model.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author canang technologies
 * @since 1/15/14
 */
public class AdAclPermissionEvaluator extends AclPermissionEvaluator {

    private static final Logger LOG = LoggerFactory.getLogger(AdAclPermissionEvaluator.class);

    private AclService aclService;
    private ObjectIdentityGenerator objectIdentityGenerator = new ObjectIdentityRetrievalStrategyImpl();
    private AdDomainRetrievalStrategy retrievalStrategy = new AdDomainRetrievalStrategy();
    private AdPermissionFactory permissionFactory = new AdPermissionFactory();
    private AdSidRetrievalStrategy sidRetrievalStrategy = new AdSidRetrievalStrategy();

    public AdAclPermissionEvaluator(AclService aclService, AdSidRetrievalStrategy sidRetrievalStrategy) {
        super(aclService);
        this.aclService = aclService;
        setObjectIdentityRetrievalStrategy(retrievalStrategy);
        setSidRetrievalStrategy(sidRetrievalStrategy);
        this.sidRetrievalStrategy = sidRetrievalStrategy;
    }

    public boolean checkPermissionByProxy(my.edu.umk.pams.account.identity.model.AcPrincipal principal, my.edu.umk.pams.account.core.AcMetaObject object, Object permission) {
        String targetType = object.getInterfaceClass().getCanonicalName();
        Serializable targetId = object.getId();
        ObjectIdentity oid = objectIdentityGenerator.createObjectIdentity(targetId, targetType);
        List<Sid> sids = sidRetrievalStrategy.getSids(principal);
        List<Permission> requiredPermission = resolvePermission(permission);
        try {
            // Lookup only ACLs for SIDs we're interested in
            Acl acl = aclService.readAclById(oid, sids);
            if (acl.isGranted(requiredPermission, sids, false)) {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Access is granted");
                }
                return true;
            }
            if (LOG.isDebugEnabled()) {
                LOG.debug("Returning false - ACLs returned, but insufficient permissions for this principal");
            }

        } catch (NotFoundException nfe) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Returning false - no ACLs apply for this principal");
            }
        }
        return false;
    }

    private List<Permission> resolvePermission(Object permission) {
        if (permission instanceof Integer) {
            return Arrays.asList(permissionFactory.buildFromMask(((Integer) permission).intValue()));
        }

        if (permission instanceof Permission) {
            return Arrays.asList((Permission) permission);
        }

        if (permission instanceof Permission[]) {
            return Arrays.asList((Permission[]) permission);
        }

        if (permission instanceof String) {
            String permString = (String) permission;
            Permission p = null;

            try {
                p = permissionFactory.buildFromName(permString);
            } catch (IllegalArgumentException notfound) {
                p = permissionFactory.buildFromName(permString.toUpperCase());
            }

            if (p != null) {
                return Arrays.asList(p);
            }

        }
        throw new IllegalArgumentException("Unsupported permission: " + permission);
    }
}
