package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAcademicSessionImpl;
import my.edu.umk.pams.account.core.AcMetadata;

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

    @Column(name = "SOURCE_NO", unique = true)
    private String sourceNo;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "EXECUTED")
    private boolean executed = false;

    @NotNull
    @OneToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID")
    private AcAcademicSession session;

    @OneToMany(targetEntity = AcSettlementItemImpl.class, mappedBy = "settlement", fetch = FetchType.LAZY)
    private List<AcSettlementItem> items;

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
    public String getSourceNo() {
        return sourceNo;
    }

    @Override
    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
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
    public boolean isExecuted() {
        return executed;
    }

    @Override
    public void setExecuted(boolean executed) {
        this.executed = executed;
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
    public List<AcSettlementItem> getItems() {
        return items;
    }

    @Override
    public void setItems(List<AcSettlementItem> details) {
        this.items = details;
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
