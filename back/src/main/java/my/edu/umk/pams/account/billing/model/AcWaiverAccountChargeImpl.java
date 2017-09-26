package my.edu.umk.pams.account.billing.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcWaiverAccountCharge")
@Table(name = "AC_WAVR_ACCT_CHRG")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcWaiverAccountChargeImpl implements AcWaiverAccountCharge {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_AC_WAVR_ACCT_CHRG")
	@SequenceGenerator(name = "SQ_AC_WAVR_ACCT_CHRG", sequenceName = "SQ_AC_WAVR_ACCT_CHRG", allocationSize = 1)
	private Long id;
	
	@OneToOne(targetEntity = AcWaiverFinanceApplicationImpl.class)
    @JoinColumn(name = "WAIVER_FINANCE_ID")
    private AcWaiverFinanceApplication waiverFinanceApplication;

    @OneToOne(targetEntity = AcAccountChargeImpl.class)
    @JoinColumn(name = "ACCOUNT_CHARGE_ID")
    private AcAccountCharge accountCharge;

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
	public AcAccountCharge getAccountCharge() {
		return accountCharge;
	}

	@Override
	public void setAccountCharge(AcAccountCharge accountCharge) {
		this.accountCharge = accountCharge;
	}
	
	@Override
	public AcWaiverFinanceApplication getWaiverFinanceApplication() {
		return waiverFinanceApplication;
	}

	@Override
	public void setWaiverFinanceApplication(AcWaiverFinanceApplication waiverFinanceApplication) {
		this.waiverFinanceApplication = waiverFinanceApplication;		
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
		return AcWaiverAccountCharge.class;
	}
}
