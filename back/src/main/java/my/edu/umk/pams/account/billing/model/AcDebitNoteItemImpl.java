package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.model.AcChargeCodeImpl;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
@Entity(name = "AcDebitNoteItem")
@Table(name = "AC_DBIT_NOTE_ITEM")
public class AcDebitNoteItemImpl implements AcDebitNoteItem {

    @Id
    @GeneratedValue(generator = "SQ_AC_DBIT_NOTE_ITEM")
    @SequenceGenerator(name = "SQ_AC_DBIT_NOTE_ITEM", sequenceName = "SQ_AC_DBIT_NOTE_ITEM", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @NotNull
    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount = BigDecimal.ZERO;

    @NotNull
    @ManyToOne(targetEntity = AcChargeCodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHARGE_CODE_ID")
    private AcChargeCode chargeCode;

    @NotNull
    @ManyToOne(targetEntity = AcDebitNoteImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DEBIT_NOTE_ID")
    private AcDebitNote debitNote;
    
    @NotNull
    @Column(name = "DEBITNOTEITEM_DATE")
    private Date debitNoteItemDate;

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
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
    public AcDebitNote getDebitNote() {
        return debitNote;
    }

    @Override
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
    public Date getDebitNoteItemDate() {
        return debitNoteItemDate;
    }

    @Override
    public void setDebitNoteItemDate(Date debitNoteItemDate) {
        this.debitNoteItemDate = debitNoteItemDate;
    }

    @Override
    public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

    @Override
	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	@Override
    public Class<?> getInterfaceClass() {
        return AcDebitNote.class;
    }
}
