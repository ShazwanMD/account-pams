package my.edu.umk.pams.account.identity.model;

import javax.persistence.*;
import java.util.Set;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Entity(name = "AcPrincipal")
@Table(name = "AC_PCPL")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AcPrincipalImpl implements AcPrincipal {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_PCPL")
    @SequenceGenerator(name = "SQ_AC_PCPL", sequenceName = "SQ_AC_PCPL", allocationSize = 1)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "ENABLED", nullable = false)
    private boolean enabled = true;

    @Column(name = "LOCKED", nullable = false)
    private boolean locked = false;

    @Column(name = "PRINCIPAL_TYPE")
    private AcPrincipalType principalType;

    @OneToMany(targetEntity = AcPrincipalRoleImpl.class, mappedBy = "principal", fetch = FetchType.EAGER)
    private Set<AcPrincipalRole> roles;

    @Embedded
    private my.edu.umk.pams.account.core.AcMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public AcPrincipalType getPrincipalType() {
        return principalType;
    }

    public void setPrincipalType(AcPrincipalType principalType) {
        this.principalType = principalType;
    }

    public Set<AcPrincipalRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<AcPrincipalRole> roles) {
        this.roles = roles;
    }

    public my.edu.umk.pams.account.core.AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(my.edu.umk.pams.account.core.AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AcPrincipalImpl that = (AcPrincipalImpl) o;

        if (!id.equals(that.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcPrincipal.class;
    }

    @Override
    public String toString() {
        return "AcPrincipalImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", principalType=" + principalType +
                '}';
    }
}
