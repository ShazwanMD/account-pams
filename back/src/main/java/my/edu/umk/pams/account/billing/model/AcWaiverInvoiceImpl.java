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

import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcWaiverInvoice")
@Table(name = "AC_WAVR_INVC")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcWaiverInvoiceImpl implements AcWaiverInvoice {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_AC_WAVR_INVC")
	@SequenceGenerator(name = "SQ_AC_WAVR_INVC", sequenceName = "SQ_AC_WAVR_INVC", allocationSize = 1)
	private Long id;
	
	@OneToOne(targetEntity = AcWaiverFinanceApplicationImpl.class)
    @JoinColumn(name = "WAIVER_FINANCE_ID")
    private AcWaiverFinanceApplication waiverFinanceApplication;

    @OneToOne(targetEntity = AcInvoiceImpl.class)
    @JoinColumn(name = "INVOICE_ID")
    private AcInvoice invoice;

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
	public AcMetadata getMetadata() {
		return metadata;
	}

	@Override
	public void setMetadata(AcMetadata metadata) {
		this.metadata = metadata;
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
	public AcInvoice getInvoice() {
		return invoice;
	}

	@Override
	public void setInvoice(AcInvoice invoice) {
		this.invoice = invoice;
	}
	
	@Override
	public Class<?> getInterfaceClass() {
		return AcWaiverInvoice.class;
	}
	
}
