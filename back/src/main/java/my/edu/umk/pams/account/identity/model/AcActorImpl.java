package my.edu.umk.pams.account.identity.model;


import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author canang.technologies
 * @since 2/11/2014
 */
@Entity(name = "AcActor")
@Table(name = "AC_ACTR")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AcActorImpl implements AcActor {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_ACTR")
    @SequenceGenerator(name = "SQ_AC_ACTR", sequenceName = "SQ_AC_ACTR", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "IDENTITY_NO", unique = true, nullable = false)
    private String identityNo;

    @NotNull
    @Column(name = "NAME", nullable = false)
    private String name;

    @NotNull
    @Column(name = "EMAIL")
    private String email;

    @NotNull
    @Column(name = "PHONE")
    private String phone;

    @Column(name = "MOBILE")
    private String mobile;

    @NotNull
    @Column(name = "FAX")
    private String fax;

    @Column(name = "ACTOR_TYPE")
    private AcActorType actorType;

    @Embedded
    private AcMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentityNo() {
        return identityNo;
    }

    public void setIdentityNo(String identityNo) {
        this.identityNo = identityNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public AcActorType getActorType() {
        return actorType;
    }

    public void setActorType(AcActorType actorType) {
        this.actorType = actorType;
    }

    public AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }
}
