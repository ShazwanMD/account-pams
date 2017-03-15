package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.model.AcChargeCodeImpl;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;

/**
 * @author PAMS
 */
@Entity(name = "AcCoverage")
@Table(name = "AC_CVRG")
public class AcCoverageImpl implements AcCoverage {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_CVRG")
    @SequenceGenerator(name = "SQ_AC_CVRG", sequenceName = "SQ_AC_CVRG", allocationSize = 1)
    private Long id;

    @ManyToOne(targetEntity = AcChargeCodeImpl.class)
    @JoinColumn(name = "CHARGE_CODE_ID")
    private AcChargeCode chargeCode;

    @ManyToOne(targetEntity = AcSponsorImpl.class)
    @JoinColumn(name = "SPONSOR_ID")
    private AcSponsor sponsor;

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
    public AcChargeCode getChargeCode() {
        return chargeCode;
    }

    @Override
    public void setChargeCode(AcChargeCode chargeCode) {
        this.chargeCode = chargeCode;
    }

    @Override
    public AcSponsor getSponsor() {
        return sponsor;
    }

    @Override
    public void setSponsor(AcSponsor sponsor) {
        this.sponsor = sponsor;
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
        return AcCoverage.class;
    }
}
