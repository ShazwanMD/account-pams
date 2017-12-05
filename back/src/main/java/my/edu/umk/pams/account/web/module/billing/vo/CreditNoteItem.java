package my.edu.umk.pams.account.web.module.billing.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.account.vo.ChargeCode;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
public class CreditNoteItem extends MetaObject {

    private String description;
    private BigDecimal amount;
    private BigDecimal balanceAmount;
    private boolean readOnly;
    private ChargeCode sodoCode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date creditNoteItemDate;
	private CreditNote creditNote;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public ChargeCode getChargeCode() {
        return sodoCode;
    }

    public void setChargeCode(ChargeCode sodoCode) {
        this.sodoCode = sodoCode;
    }
  
    public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Date getCreditNoteItemDate() {
		return creditNoteItemDate;
	}
	
	public void setCreditNoteItemDate(Date creditNoteItemDate) {
		this.creditNoteItemDate = creditNoteItemDate;
	}

	public CreditNote getCreditNote() {
		return creditNote;
	}

	public void setCreditNote(CreditNote creditNote) {
		this.creditNote = creditNote;
	}

	@JsonCreator
    public static CreditNoteItem create(String jsonString) {
        CreditNoteItem o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, CreditNoteItem.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
