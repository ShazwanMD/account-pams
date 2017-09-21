package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;

@Entity(name = "AcReceiptDebitNote")
@Table(name = "AC_RCPT_DBT")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcReceiptDebitNoteImpl implements AcReceiptDebitNote {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_AC_RCPT_DBT")
	@SequenceGenerator(name = "SQ_AC_RCPT_DBT", sequenceName = "SQ_AC_RCPT_DBT", allocationSize = 1)
	private Long id;
	
	@OneToOne(targetEntity = AcReceiptImpl.class)
    @JoinColumn(name = "RECEIPT_ID")
    private AcReceipt receipt;

    @OneToOne(targetEntity = AcDebitNoteImpl.class)
    @JoinColumn(name = "DEBITNOTE_ID")
    private AcDebitNote debitNote;

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
	
	public AcDebitNote getDebitNote() {
		return debitNote;
	}

	public void setDebitNote(AcDebitNote debitNote) {
		this.debitNote = debitNote;
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
		return AcReceiptDebitNote.class;
	}

}
