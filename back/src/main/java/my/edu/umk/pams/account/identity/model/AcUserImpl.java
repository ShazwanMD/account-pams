package my.edu.umk.pams.account.identity.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static my.edu.umk.pams.account.identity.model.AcPrincipalType.USER;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Entity(name = "AcUser")
@Table(name = "AC_USER")
public class AcUserImpl extends AcPrincipalImpl implements AcUser {

    @NotNull
    @Column(name = "PASSWORD")
    private String password;

    @NotNull
    @Column(name = "REAL_NAME", nullable = false)
    private String realName;

    @NotNull
    @Column(name = "EMAIL", nullable = false, unique = true)
    private String email;

    @OneToOne(targetEntity = AcActorImpl.class)
    @JoinColumn(name = "ACTOR_ID", nullable = true)
    private AcActor actor;

    public AcUserImpl() {
        setPrincipalType(USER);
    }

    @Override
    public String getUsername() {
        return getName();
    }

    @Override
    public void setUsername(String username) {
        setName(username);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getRealName() {
        return realName;
    }

    @Override
    public void setRealName(String realName) {
        this.realName = realName;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public AcActor getActor() {
        return actor;
    }

    @Override
    public void setActor(AcActor actor) {
        this.actor = actor;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcUser.class;
    }

}
