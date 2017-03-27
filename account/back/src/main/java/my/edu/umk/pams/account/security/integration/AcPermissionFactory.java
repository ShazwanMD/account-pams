package my.edu.umk.pams.account.security.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.acls.domain.DefaultPermissionFactory;

/**
 * @author canang technologies
 * @since 1/13/14
 */
public class AcPermissionFactory extends DefaultPermissionFactory {

    private static final Logger log = LoggerFactory.getLogger(AcPermissionFactory.class);

    public AcPermissionFactory() {
        super(AcPermission.class);
        registerPublicPermissions(AcPermission.class);
    }
}
