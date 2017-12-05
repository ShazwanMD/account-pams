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
@Entity(name = "AcCreditNoteItem")
@Table(name = "AC_CDIT_NOTE_ITEM")
public class AcCreditNoteItemImpl implements AcCreditNoteItem {

    @Id
    @GeneratedValue(generator = "SQ_AC_CDIT_NOTE_ITEM")
    @SequenceGenerator(name = "SQ_AC_CDIT_NOTE_ITEM", sequenceName = "SQ_AC_CDIT_NOTE_ITEM", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;
    
    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount = BigDecimal.ZERO;

    @NotNull
    @ManyToOne(targetEntity = AcChargeCodeImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CHARGE_CODE_ID")
    private AcChargeCode chargeCode;

    @NotNull
    @ManyToOne(targetEntity = AcCreditNoteImpl.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDIT_NOTE_ID")
    private AcCreditNote creditNote;
    
    @NotNull
    @Column(name = "CREDITNOTEITEM_DATE")
    private Date creditNoteItemDate;


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
    public AcCreditNote getCreditNote() {
        return creditNote;
    }

    @Override
    public void setCreditNote(AcCreditNote creditNote) {
        this.creditNote = creditNote;
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
    public Date getCreditNoteItemDate() {
        return creditNoteItemDate;
    }

    @Override
    public void setCreditNoteItemDate(Date creditNoteItemDate) {
        this.creditNoteItemDate = creditNoteItemDate;
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
        return AcCreditNote.class;
    }
}
