package my.edu.umk.pams.account.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author canang technologies
 * @since 1/15/14
 */
@Entity(name = "AclClass")
@Table(name = "ACL_CLASS")
public class AclClassImpl implements AclClass {

    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Column(name = "CLASS", nullable = false)
    private String aclClass;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAclClass() {
        return aclClass;
    }

    public void setAclClass(String aclClass) {
        this.aclClass = aclClass;
    }
}
