package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;

@Entity(name = "AcReceiptInvoice")
@Table(name = "AC_RCPT_INVC")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcReceiptInvoiceImpl implements AcReceiptInvoice {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_AC_RCPT_INVC")
	@SequenceGenerator(name = "SQ_AC_RCPT_INVC", sequenceName = "SQ_AC_RCPT_INVC", allocationSize = 1)
	private Long id;
	
	@OneToOne(targetEntity = AcReceiptImpl.class)
    @JoinColumn(name = "RECEIPT_ID")
    private AcReceipt receipt;

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
	public AcReceipt getReceipt() {
		return receipt;
	}

	@Override
	public void setReceipt(AcReceipt receipt) {
		this.receipt = receipt;
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
		return AcReceiptInvoice.class;
	}

}
