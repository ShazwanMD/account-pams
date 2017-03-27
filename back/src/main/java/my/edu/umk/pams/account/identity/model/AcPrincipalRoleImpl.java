package my.edu.umk.pams.account.identity.model;

import javax.persistence.*;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Table(name = "AC_PCPL_ROLE")
@Entity(name = "AcPrincipalRole")
public class AcPrincipalRoleImpl implements AcPrincipalRole {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_PCPL_ROLE")
    @SequenceGenerator(name = "SQ_AC_PCPL_ROLE", sequenceName = "SQ_AC_PCPL_ROLE", allocationSize = 1)
    private Long id;

    @Column(name = "ROLE_TYPE")
    private AcRoleType role;

    @ManyToOne(targetEntity = AcPrincipalImpl.class)
    @JoinColumn(name = "PRINCIPAL_ID")
    private AcPrincipal principal;

    @Embedded
    private my.edu.umk.pams.account.core.AcMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcRoleType getRole() {
        return role;
    }

    public void setRole(AcRoleType role) {
        this.role = role;
    }

    public AcPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(AcPrincipal principal) {
        this.principal = principal;
    }

    public my.edu.umk.pams.account.core.AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(my.edu.umk.pams.account.core.AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcPrincipalRole.class;
    }

}
