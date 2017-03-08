package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
@Entity(name = "AcAccountCharge")
@Table(name = "AC_ACCT_CHRG")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcAccountChargeImpl implements AcAccountCharge {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_ACTR_ACCT_CHRG")
    @SequenceGenerator(name = "SEQ_ACTR_ACCT_CHRG", sequenceName = "SEQ_ACTR_ACCT_CHRG", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "REFERENCE_NO", unique = true, nullable = false)
    private String referenceNo;

    @Column(name = "SOURCE_NO", nullable = false)
    private String sourceNo;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @NotNull
    @Column(name = "CHARGE_TYPE", nullable = false)
    private AcAccountChargeType chargeType;

    @NotNull
    @ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

    @NotNull
    @ManyToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID", nullable = false)
    private AcAcademicSession session;

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
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public AcAccountChargeType getChargeType() {
        return chargeType;
    }

    @Override
    public void setChargeType(AcAccountChargeType chargeType) {
        this.chargeType = chargeType;
    }

    @Override
    public AcAccount getAccount() {
        return account;
    }

    @Override
    public void setAccount(AcAccount account) {
        this.account = account;
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
    public AcMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcAccountCharge.class;
    }
}
