package my.edu.umk.pams.account.security.model;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/15/14
 */
@Entity(name = "AclObjectIdentity")
@Table(name = "ACL_OBJECT_IDENTITY")
@Filters({
        @Filter(
                name = "filter1_InXxx",
                condition = "OBJECT_ID_IDENTITY in (" +
                        "select C.ID from AC_XXX C " +
                        " where 1=1" +
                        ")")

})

@FilterDefs({
        @FilterDef(name = "filter1_Xxx",
                parameters = {@ParamDef(name = "filter", type = "string")})


})

public class AclObjectIdentityImpl implements AclObjectIdentity {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "OBJECT_ID_IDENTITY", nullable = false)
    private Long objectIdIdentity;

    @Column(name = "PARENT_OBJECT", nullable = true)
    private Long parentObject;

    @Column(name = "ENTRIES_INHERITING", nullable = false)
    private Boolean entriesInheriting;

    @ManyToOne(targetEntity = AclSidImpl.class)
    @JoinColumn(name = "OWNER_SID")
    private AclSid ownerSid;

    @ManyToOne(targetEntity = AclClassImpl.class)
    @JoinColumn(name = "OBJECT_ID_CLASS")
    private AclClass objectClass;

    @OneToMany(targetEntity = AclEntryImpl.class, mappedBy = "objectIdentity", fetch = FetchType.EAGER)
    private Set<AclEntry> entries;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectIdIdentity() {
        return objectIdIdentity;
    }

    public void setObjectIdIdentity(Long objectIdIdentity) {
        this.objectIdIdentity = objectIdIdentity;
    }

    public Long getParentObject() {
        return parentObject;
    }

    public void setParentObject(Long parentObject) {
        this.parentObject = parentObject;
    }


    public Boolean getEntriesInheriting() {
        return entriesInheriting;
    }

    public void setEntriesInheriting(Boolean entriesInheriting) {
        this.entriesInheriting = entriesInheriting;
    }

    public AclSid getOwnerSid() {
        return ownerSid;
    }


    public void setOwnerSid(AclSid ownerSid) {
        this.ownerSid = ownerSid;
    }

    public AclClass getObjectClass() {
        return objectClass;
    }

    public void setObjectClass(AclClass objectClass) {
        this.objectClass = objectClass;
    }

    public Set<AclEntry> getEntries() {
        return entries;
    }

    public void setEntries(Set<AclEntry> entries) {
        this.entries = entries;
    }
}

