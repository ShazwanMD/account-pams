package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;

@Entity(name = "AcReceiptAccountCharge")
@Table(name = "AC_RCPT_ACCT_CHRG")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcReceiptAccountChargeImpl implements AcReceiptAccountCharge {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_AC_RCPT_ACCT_CHRG")
	@SequenceGenerator(name = "SQ_AC_RCPT_ACCT_CHRG", sequenceName = "SQ_AC_RCPT_ACCT_CHRG", allocationSize = 1)
	private Long id;
	
	@OneToOne(targetEntity = AcReceiptImpl.class)
    @JoinColumn(name = "RECEIPT_ID")
    private AcReceipt receipt;

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
	public AcReceipt getReceipt() {
		return receipt;
	}

	@Override
	public void setReceipt(AcReceipt receipt) {
		this.receipt = receipt;
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
	public AcMetadata getMetadata() {
		return metadata;
	}

	@Override
	public void setMetadata(AcMetadata metadata) {
		this.metadata = metadata;
	}

	@Override
	public Class<?> getInterfaceClass() {
		return AcReceiptAccountCharge.class;
	}

}
