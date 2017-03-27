package my.edu.umk.pams.account.security.integration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.acls.model.ObjectIdentityRetrievalStrategy;

/**
 * @author canang technologies
 * @since 1/15/14
 */
public class AcDomainRetrievalStrategy implements ObjectIdentityRetrievalStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(AcDomainRetrievalStrategy.class);

    public ObjectIdentity getObjectIdentity(Object domainObject) {
        return new ObjectIdentityImpl(
                ((my.edu.umk.pams.account.core.AcMetaObject) domainObject).getInterfaceClass(),
                ((my.edu.umk.pams.account.core.AcMetaObject) domainObject).getId()
        );
    }
}
