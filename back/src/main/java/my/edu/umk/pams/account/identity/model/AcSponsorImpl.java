package my.edu.umk.pams.account.identity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "AcSponsor")
@Table(name = "AC_SPONSOR")
public class AcSponsorImpl extends AcActorImpl implements AcSponsor {

    @Column(name = "CODE")
    private String code;

    @OneToMany(targetEntity = AcCoverageImpl.class, mappedBy = "sponsor")
    private List<AcCoverage> coverages;

	@Column(name = "SPONSOR_TYPE")
	private AcSponsorType sponsorType;

    public AcSponsorImpl() {
        setActorType(AcActorType.SPONSOR);
        setAcSponsorType(ac)
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<AcCoverage> getCoverages() {
        return coverages;
    }

    public void setCoverages(List<AcCoverage> coverages) {
        this.coverages = coverages;
    }

	public AcSponsorType getSponsorType() {
		return sponsorType;
	}

	public void setAcSponsorType(AcSponsorType sponsorType) {
		this.sponsorType = sponsorType;
	}
	
    @Override
    public Class<?> getInterfaceClass() {
        return AcSponsor.class;
    }
}
