package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAcademicSessionImpl;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorImpl;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Process Batch for processing per academic session
 * <p>
 * Processing
 * - qualification or disqualification
 * - text file generator
 * - batch processing charges
 * - credit generator
 * - invoice generator
 * </p>

 */
@Entity(name = "AcSettlement")
@Table(name = "AC_STLT")
public class AcSettlementImpl implements AcSettlement {

    @Id
    @GeneratedValue(generator = "SQ_AC_STLT")
    @SequenceGenerator(name = "SQ_AC_STLT", sequenceName = "SQ_AC_STLT", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "REFERENCE_NO", unique = true, nullable = false)
    private String referenceNo;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotNull
    @OneToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID")
    private AcAcademicSession session;

    @NotNull
    @OneToOne(targetEntity = AcSponsorImpl.class)
    @JoinColumn(name = "SPONSOR_ID")
    private AcSponsor sponsor;

    @OneToMany(targetEntity = AcSettlementItemImpl.class, mappedBy = "settlement", fetch = FetchType.LAZY)
    private List<AcSettlementItem> details;

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
    public String getReferenceNo() {
        return referenceNo;
    }

    @Override
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
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
    public AcAcademicSession getSession() {
        return session;
    }

    @Override
    public void setSession(AcAcademicSession session) {
        this.session = session;
    }

    @Override
    public AcSponsor getSponsor() {
        return sponsor;
    }

    @Override
    public void setSponsor(AcSponsor sponsor) {
        this.sponsor = sponsor;
    }

    public List<AcSettlementItem> getItems() {
        return details;
    }

    public void setItems(List<AcSettlementItem> details) {
        this.details = details;
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
        return AcSettlement.class;
    }
}
