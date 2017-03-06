package my.edu.umk.pams.account.identity.model;

import javax.persistence.*;
import java.util.Set;

import static my.edu.umk.pams.account.identity.model.AcPrincipalType.GROUP;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Entity(name = "AcGroup")
@Table(name = "AC_GROP")
public class AcGroupImpl extends AcPrincipalImpl implements AcGroup {

    @ManyToMany(fetch = FetchType.LAZY, targetEntity = AcPrincipalImpl.class)
    @JoinTable(name = "AC_GROP_MMBR", joinColumns = {
            @JoinColumn(name = "GROUP_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "PRINCIPAL_ID",
                    nullable = false, updatable = false)})
    private Set<AcPrincipal> members;

    public AcGroupImpl() {
        setPrincipalType(GROUP);
    }

    public Set<AcPrincipal> getMembers() {
        return members;
    }

    public void setMembers(Set<AcPrincipal> members) {
        this.members = members;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcGroup.class;
    }

}
