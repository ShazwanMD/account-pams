package my.edu.umk.pams.account.security.event;

import junit.framework.Assert;
import my.edu.umk.pams.account.identity.dao.AcPrincipalDao;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.security.integration.AcPermission;
import my.edu.umk.pams.account.security.integration.NonSerializableSecurityContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.acls.domain.ObjectIdentityImpl;
import org.springframework.security.acls.domain.PrincipalSid;
import org.springframework.security.acls.model.MutableAcl;
import org.springframework.security.acls.model.MutableAclService;
import org.springframework.security.acls.model.NotFoundException;
import org.springframework.security.acls.model.ObjectIdentity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author canang technologies
 * @since 4/18/14
 */
@Component("accessListener")
public class AccessListener implements ApplicationListener<AccessEvent> {

    private static final Logger log = LoggerFactory.getLogger(AccessListener.class);

    private static final String ROOT = "root";
    @Autowired
    protected AcPrincipalDao principalDao;
    @Autowired
    protected MutableAclService mutableAclService;
    @Autowired
    protected PermissionEvaluator permissionEvaluator;
    @Autowired(required = false)
    private AuthenticationManager authenticationManager;

    @Override
    public void onApplicationEvent(AccessEvent event) {
        log.debug("Access event triggered");
        log.debug("object: " + event.getObject().getId());
        log.debug("command: " + event.getCommand());
//        log.debug("parent: " + event.getParent());
//        log.debug("principal: " + event.getPrincipal().getName());
//        log.debug("permission: " + event.getPermission());

        // saved current context
        SecurityContext savedCtx = SecurityContextHolder.getContext();
        try {

            // temporarily log in as root
            // NOTE: http://issues.hudson-ci.org/browse/HUDSON-7256
            AcAutoLoginToken authenticationToken = new AcAutoLoginToken("root");
            Authentication authed = authenticationManager.authenticate(authenticationToken);
            SecurityContext system = new NonSerializableSecurityContext();
            system.setAuthentication(authed);
            SecurityContextHolder.setContext(system);

            // grab parameter from event
            my.edu.umk.pams.account.core.AcMetaObject object = event.getObject();

            // creating oid
            MutableAcl acl;
            ObjectIdentity oid = new ObjectIdentityImpl(
                    object.getInterfaceClass().getCanonicalName(),
                    object.getId());

            // TODO: clean up
            switch (event.getCommand()) {
                case GRANT:
                    my.edu.umk.pams.account.core.AcMetaObject parent = event.getParent();
                    AcPermission permission = event.getPermission();
                    my.edu.umk.pams.account.identity.model.AcPrincipal principal = event.getPrincipal();
                    PrincipalSid sid = new PrincipalSid(principal.getName());
                    log.debug("==================================================");
                    log.debug("oid: " + oid.getType() + "#" + oid.getIdentifier());
                    log.debug("sid: " + sid);
                    log.debug("mask: " + permission.getMask());
                    log.debug("pattern: " + permission.getPattern());
                    log.debug("==================================================");

                    createAce(permission, sid, oid);
                    break;
                case REVOKE:
                    parent = event.getParent();
                    permission = event.getPermission();
                    principal = event.getPrincipal();
                    sid = new PrincipalSid(principal.getName());
                    log.debug("==================================================");
                    log.debug("oid: " + oid.getType() + "#" + oid.getIdentifier());
                    log.debug("sid: " + sid);
                    log.debug("mask: " + permission.getMask());
                    log.debug("pattern: " + permission.getPattern());
                    log.debug("==================================================");

                    deleteAce(permission, sid, oid);
                    break;
                case INHERIT:
                    parent = event.getParent();
                    ObjectIdentity parentOid = new ObjectIdentityImpl(
                            parent.getInterfaceClass().getCanonicalName(),
                            parent.getId());
                    inheritAcl(oid, parentOid);
                    break;
            }
        } finally {
            // revert to original context
            SecurityContextHolder.setContext(savedCtx);
        }
    }

    private void createAce(AcPermission permission, PrincipalSid sid, ObjectIdentity oid) {
        MutableAcl acl;// read acl, if fail, create
        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException nfe) {
            log.debug("ACL Not found! Creating new oid:" + oid);
            acl = mutableAclService.createAcl(oid);
        }
        // insert new ace and update
        acl.insertAce(acl.getEntries().size(), permission, sid, true);
        mutableAclService.updateAcl(acl);
    }


    private void inheritAcl(ObjectIdentity oid, ObjectIdentity parentOid) {
        Assert.assertNotNull("oid cannot be null", oid);
        Assert.assertNotNull("Parent oid cannot be null", parentOid);
        MutableAcl acl;// read acl, if fail, create
        MutableAcl parentAcl;// read acl, if fail, create
        try {
            acl = (MutableAcl) mutableAclService.readAclById(oid);
        } catch (NotFoundException nfe) {
            log.debug("ACL Not found! Creating new oid:" + oid);
            acl = mutableAclService.createAcl(oid);
        }

        try {
            parentAcl = (MutableAcl) mutableAclService.readAclById(parentOid);
        } catch (NotFoundException nfe) {
            log.debug("ACL Not found! Creating new parent oid:" + parentOid);
            parentAcl = mutableAclService.createAcl(parentOid);
        }

        // insert new ace and update
        acl.setParent(parentAcl);
        mutableAclService.updateAcl(acl);
    }

    private void deleteAce(AcPermission permission, PrincipalSid sid, ObjectIdentity oid) {
        mutableAclService.deleteAcl(oid, true);
    }
}
