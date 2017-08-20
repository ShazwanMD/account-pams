package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorImpl;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcSponsorshipImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;
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

    @OneToMany(targetEntity = AcAccountWaiverImpl.class, mappedBy = "account")
    private List<AcAccountWaiver> waivers;
    
    @OneToMany(targetEntity = AcSponsorshipImpl.class, mappedBy = "account")
    private List<AcSponsorship> sponsorships;

    @Embedded
    private AcMetadata metadata;

    // transient
    @Transient
    private BigDecimal balance;

    @Transient
    private BigDecimal effectiveBalance;

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
    public List<AcAccountWaiver> getWaivers() {
        return waivers;
    }

    @Override
    public void setWaivers(List<AcAccountWaiver> waivers) {
        this.waivers = waivers;
    }
    
    @Override
    public List<AcSponsorship> getsponsorships() {
        return sponsorships;
    }

    @Override
    public void setSponsorships(List<AcSponsorship> sponsorships) {
        this.sponsorships = sponsorships;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getEffectiveBalance() {
        return effectiveBalance;
    }

    public void setEffectiveBalance(BigDecimal effectiveBalance) {
        this.effectiveBalance = effectiveBalance;
    }
}
