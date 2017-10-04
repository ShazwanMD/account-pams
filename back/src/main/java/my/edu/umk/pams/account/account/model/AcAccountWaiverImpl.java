package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplicationImpl;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplicationType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
@Entity(name = "AcAccountWaiver")
@Table(name = "AC_ACCT_WAVR")
public class AcAccountWaiverImpl implements AcAccountWaiver {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_ACCT_WAVR")
    @SequenceGenerator(name = "SQ_AC_ACCT_WAVR", sequenceName = "SQ_AC_ACCT_WAVR", allocationSize = 1)
    private Long id;

    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Column(name = "STATUS")
    private Boolean status = false;

    @ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

    @NotNull
    @OneToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID")
    private AcAcademicSession session;

    @Enumerated(value = EnumType.ORDINAL)
    @Column(name = "WAIVER_TYPE", nullable = false)
    private AcWaiverApplicationType waiverType;
    
    @Embedded
    private AcMetadata metadata;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
	public Boolean getStatus() {
		return status;
	}

    @Override
	public void setStatus(Boolean status) {
		this.status = status;
	}

    @Override
	public AcWaiverApplicationType getWaiverType() {
		return waiverType;
	}

    @Override
	public void setWaiverType(AcWaiverApplicationType waiverType) {
		this.waiverType = waiverType;
	}

	@Override
    public Class<?> getInterfaceClass() {
        return AcAccountWaiver.class;
    }
}
