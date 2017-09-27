package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.account.vo.ChargeCode;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

public class KnockoffItem extends MetaObject {

	private String description;
	private BigDecimal dueAmount;
	private BigDecimal appliedAmount;
	private BigDecimal totalAmount;
	private Invoice invoice;
	private ChargeCode chargeCode;
	private Knockoff knockoff;
	private DebitNote debitNote;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getDueAmount() {
		return dueAmount;
	}

	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}

	public BigDecimal getAppliedAmount() {
		return appliedAmount;
	}

	public void setAppliedAmount(BigDecimal appliedAmount) {
		this.appliedAmount = appliedAmount;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public ChargeCode getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(ChargeCode chargeCode) {
		this.chargeCode = chargeCode;
	}

	public Knockoff getKnockoff() {
		return knockoff;
	}

	public void setKnockoff(Knockoff knockoff) {
		this.knockoff = knockoff;
	}
	
	public DebitNote getDebitNote() {
		return debitNote;
	}

	public void setDebitNote(DebitNote debitNote) {
		this.debitNote = debitNote;
	}
	
	@JsonCreator
    public static KnockoffItem create(String jsonString) {
		KnockoffItem o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, KnockoffItem.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
