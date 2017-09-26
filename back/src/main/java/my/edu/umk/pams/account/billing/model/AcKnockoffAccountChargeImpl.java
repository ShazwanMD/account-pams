package my.edu.umk.pams.account.billing.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcKnockoffAccountCharge")
@Table(name = "AC_KNOF_CHRG")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcKnockoffAccountChargeImpl implements AcKnockoffAccountCharge {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_AC_KNOF_CHRG")
	@SequenceGenerator(name = "SQ_AC_KNOF_CHRG", sequenceName = "SQ_AC_KNOF_CHRG", allocationSize = 1)
	private Long id;
	
    @OneToOne(targetEntity = AcInvoiceImpl.class)
    @JoinColumn(name = "ACCOUNT_CHARGE_ID")
    private AcAccountCharge accountCharge;
    
	@NotNull
	@OneToOne(targetEntity = AcKnockoffImpl.class)
	@JoinColumn(name = "KNOCKOFF_ID")
	private AcKnockoff knockoff;
	
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
	public AcKnockoff getKnockoff() {
		return knockoff;
	}

	@Override
	public void setKnockoff(AcKnockoff knockoff) {
		this.knockoff = knockoff;
		
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
		return AcKnockoffAccountCharge.class;
	}
}
