package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author PAMS
 */
@Entity(name = "AcAccount")
@Table(name = "AC_ACCT")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcAccountImpl implements AcAccount{

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_ACCT")
    @SequenceGenerator(name = "SQ_AC_ACCT", sequenceName = "SQ_AC_ACCT", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @OneToOne(targetEntity = AcActorImpl.class)
    @JoinColumn(name = "ACTOR_ID")
    private AcActor actor;

    @OneToMany(targetEntity = AcAccountTransactionImpl.class, mappedBy = "account")
    private List<AcAccountTransaction> transactions;

    @OneToMany(targetEntity = AcAccountChargeImpl.class, mappedBy = "account")
    private List<AcAccountCharge> charges;

    @Embedded
    private AcMetadata metadata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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
    public List<AcAccountCharge> getCharges() {
        return charges;
    }

    @Override
    public void setCharges(List<AcAccountCharge> charges) {
        this.charges = charges;
    }

    @Override
    public List<AcAccountTransaction> getTransactions() {
        return transactions;
    }

    @Override
    public void setTransactions(List<AcAccountTransaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public AcMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcAccount.class;
    }
}
