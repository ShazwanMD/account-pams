package my.edu.umk.pams.account.security.model;

import javax.persistence.*;

/**
 * @author canang technologies
 * @since 1/15/14
 */
@Entity(name = "AclEntry")
@Table(name = "ACL_ENTRY")
public class AclEntryImpl implements AclEntry {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "ACE_ORDER", nullable = false)
    private Integer aclOrder;

    @Column(name = "MASK", nullable = false)
    private Integer mask;

    @Column(name = "GRANTING", nullable = false)
    private Boolean granting;

    @Column(name = "AUDIT_SUCCESS", nullable = false)
    private Boolean auditSuccess;

    @Column(name = "AUDIT_FAILURE", nullable = false)
    private Boolean auditFailure;

    @ManyToOne(targetEntity = AclSidImpl.class)
    @JoinColumn(name = "SID")
    private AclSid sid;

    @ManyToOne(targetEntity = AclObjectIdentityImpl.class)
    @JoinColumn(name = "ACL_OBJECT_IDENTITY")
    private AclObjectIdentity objectIdentity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAclOrder() {
        return aclOrder;
    }

    public void setAclOrder(Integer aclOrder) {
        this.aclOrder = aclOrder;
    }

    public Integer getMask() {
        return mask;
    }

    public void setMask(Integer mask) {
        this.mask = mask;
    }

    public Boolean getGranting() {
        return granting;
    }

    public void setGranting(Boolean granting) {
        this.granting = granting;
    }

    public Boolean getAuditSuccess() {
        return auditSuccess;
    }

    public void setAuditSuccess(Boolean auditSuccess) {
        this.auditSuccess = auditSuccess;
    }

    public Boolean getAuditFailure() {
        return auditFailure;
    }

    public void setAuditFailure(Boolean auditFailure) {
        this.auditFailure = auditFailure;
    }

    public AclSid getSid() {
        return sid;
    }

    public void setSid(AclSid sid) {
        this.sid = sid;
    }

    public AclObjectIdentity getObjectIdentity() {
        return objectIdentity;
    }

    public void setObjectIdentity(AclObjectIdentity objectIdentity) {
        this.objectIdentity = objectIdentity;
    }
}
