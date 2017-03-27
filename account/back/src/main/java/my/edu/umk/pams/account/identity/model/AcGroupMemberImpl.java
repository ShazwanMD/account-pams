package my.edu.umk.pams.account.identity.model;


import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Entity(name = "AcGroupMember")
@Table(name = "AC_GROP_MMBR")
public class AcGroupMemberImpl implements AcGroupMember {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_GROP_MMBR")
    @SequenceGenerator(name = "SQ_AC_GROP_MMBR", sequenceName = "SQ_AC_GROP_MMBR", allocationSize = 1)
    private Long id;

    @OneToOne(targetEntity = AcGroupImpl.class)
    @JoinColumn(name = "GROUP_ID")
    private AcGroup group;

    @OneToOne(targetEntity = AcPrincipalImpl.class)
    @JoinColumn(name = "PRINCIPAL_ID")
    private AcPrincipal principal;

    @Embedded
    private AcMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AcGroup getGroup() {
        return group;
    }

    public void setGroup(AcGroup group) {
        this.group = group;
    }

    public AcPrincipal getPrincipal() {
        return principal;
    }

    public void setPrincipal(AcPrincipal principal) {
        this.principal = principal;
    }

    public AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcGroupMember.class;
    }


}
