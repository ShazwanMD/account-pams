package my.edu.umk.pams.account.identity.model;

import javax.persistence.*;
import java.util.List;

@Entity(name = "AcSponsor")
@Table(name = "AC_SPSR")
public class AcSponsorImpl extends AcActorImpl implements AcSponsor {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "SPONSOR_TYPE")
    private AcSponsorType sponsorType = AcSponsorType.NONE;

    @OneToMany(targetEntity = AcCoverageImpl.class, mappedBy = "sponsor")
    private List<AcCoverage> coverages;

    public AcSponsorImpl() {
        setActorType(AcActorType.SPONSOR);
    }

    @Override
    public String getSponsorNo() {
        return getIdentityNo();
    }

    @Override
    public void setSponsorNo(String sponsorNo) {
        setIdentityNo(sponsorNo);
    }

    @Override
    public AcSponsorType getSponsorType() {
        return sponsorType;
    }

    @Override
    public void setSponsorType(AcSponsorType sponsorType) {
        this.sponsorType = sponsorType;
    }

    @Override
    public List<AcCoverage> getCoverages() {
        return coverages;
    }

    @Override
    public void setCoverages(List<AcCoverage> coverages) {
        this.coverages = coverages;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcSponsor.class;
    }
}
