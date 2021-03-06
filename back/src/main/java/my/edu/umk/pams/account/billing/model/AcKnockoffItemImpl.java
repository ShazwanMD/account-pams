package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.model.AcChargeCodeImpl;
import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcKnockoffItem")
@Table(name = "AC_KNOF_ITEM")
public class AcKnockoffItemImpl implements AcKnockoffItem {

	@Id
	@GeneratedValue(generator = "SQ_AC_KNOF_ITEM")
	@SequenceGenerator(name = "SQ_AC_KNOF_ITEM", sequenceName = "SQ_AC_KNOF_ITEM", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Column(name = "DESCRIPTION")
	private String description;

	@NotNull
	@Column(name = "DUE_AMOUNT", nullable = false)
	private BigDecimal dueAmount = BigDecimal.ZERO;
	
    @NotNull
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "KNOCKOFF_ITEM_TYPE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private AcKnockoffItemType knockoffItemType;

	@NotNull
	@Column(name = "APPLIED_AMOUNT", nullable = false)
	private BigDecimal appliedAmount = BigDecimal.ZERO;

	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount = BigDecimal.ZERO;

	@NotNull
	@ManyToOne(targetEntity = AcInvoiceImpl.class)
	@JoinColumn(name = "INVOICE_ID")
	private AcInvoice invoice;

	@ManyToOne(targetEntity = AcChargeCodeImpl.class, fetch = FetchType.LAZY)
	@JoinColumn(name = "CHARGE_CODE_ID")
	private AcChargeCode chargeCode;

    @ManyToOne(targetEntity = AcAccountChargeImpl.class)
    @JoinColumn(name = "ACCOUNT_CHARGE_ID")
    private AcAccountCharge accountCharge;
	
	@NotNull
	@ManyToOne(targetEntity = AcKnockoffImpl.class)
	@JoinColumn(name = "KNOCKOFF_ID")
	private AcKnockoff knockoff;

    @ManyToOne(targetEntity = AcDebitNoteImpl.class)
    @JoinColumn(name = "DEBIT_NOTE_ID")
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
	public String getDescription() {
		return description;
	}

	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public BigDecimal getDueAmount() {
		return dueAmount;
	}

	@Override
	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}

	@Override
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	@Override
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	@Override
	public BigDecimal getAppliedAmount() {
		return appliedAmount;
	}

	@Override
	public void setAppliedAmount(BigDecimal appliedAmount) {
		this.appliedAmount = appliedAmount;
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
	public AcChargeCode getChargeCode() {
		return chargeCode;
	}

	@Override
	public void setChargeCode(AcChargeCode chargeCode) {
		this.chargeCode = chargeCode;
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
		return AcKnockoffItem.class;
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
	public void setDebitNote(AcDebitNote debitNote) {
		this.debitNote = debitNote;
	}
		
	@Override
    public AcKnockoffItemType getKnockoffItemType() {
		return knockoffItemType;
	}

	@Override
	public void setKnockoffItemType(AcKnockoffItemType knockoffItemType) {
		this.knockoffItemType = knockoffItemType;
	}

	@Override
    public AcAccountCharge getAccountCharge() {
		return accountCharge;
	}

    @Override
	public void setAccountCharge(AcAccountCharge accountCharge) {
		this.accountCharge = accountCharge;
	}

}
