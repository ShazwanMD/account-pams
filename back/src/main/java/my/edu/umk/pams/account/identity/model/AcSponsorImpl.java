package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;

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

    public AcSponsorImpl() {
        setActorType(AcActorType.SPONSOR);
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

    @Override
    public Class<?> getInterfaceClass() {
        return AcSponsor.class;
    }
}
