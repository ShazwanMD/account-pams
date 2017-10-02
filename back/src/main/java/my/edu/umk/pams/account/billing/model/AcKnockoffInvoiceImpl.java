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

import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcKnockoffInvoice")
@Table(name = "AC_KNOF_INVC")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcKnockoffInvoiceImpl implements AcKnockoffInvoice {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_AC_KNOF_INVC")
	@SequenceGenerator(name = "SQ_AC_KNOF_INVC", sequenceName = "SQ_AC_KNOF_INVC", allocationSize = 1)
	private Long id;
	
	@NotNull
    @OneToOne(targetEntity = AcInvoiceImpl.class)
    @JoinColumn(name = "INVOICE_ID")
    private AcInvoice invoice;
    
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
	public AcInvoice getInvoice() {
		return invoice;
	}

	@Override
	public void setInvoice(AcInvoice invoice) {
		this.invoice = invoice;
		
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
		return AcKnockoffInvoice.class;
	}
}
