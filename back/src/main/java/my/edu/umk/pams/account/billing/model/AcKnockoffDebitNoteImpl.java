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
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcKnockoffDebitNote")
@Table(name = "AC_KNOF_DBT_NOTE")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcKnockoffDebitNoteImpl implements AcKnockoffDebitNote {
	
	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_AC_KNOF_DBT_NOTE")
	@SequenceGenerator(name = "SQ_AC_KNOF_DBT_NOTE", sequenceName = "SQ_AC_KNOF_DBT_NOTE", allocationSize = 1)
	private Long id;
	
    @OneToOne(targetEntity = AcDebitNoteImpl.class)
    @JoinColumn(name = "DEBIT_NOTE_ID")
    private AcDebitNote debitNote;
    
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
	public AcDebitNote getDebitNote() {
		return debitNote;
	}

	@Override
	public void setDebitNote(AcDebitNote invoice) {
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
		return AcKnockoffDebitNote.class;
	}
	
}
