package my.edu.umk.pams.account.web.module.billing.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.account.vo.ChargeCode;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
public class ReceiptItem extends MetaObject {

    private String description;
    private BigDecimal dueAmount;
    private BigDecimal totalAmount;
    private BigDecimal adjustedAmount;
    private BigDecimal appliedAmount;
    private BigDecimal price;
    private Integer unit;
    private ChargeCode chargeCode;
    private Invoice invoice;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ChargeCode getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(ChargeCode sodoCode) {
        this.chargeCode = sodoCode;
    }

    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getAdjustedAmount() {
        return adjustedAmount;
    }

    public void setAdjustedAmount(BigDecimal adjustedAmount) {
        this.adjustedAmount = adjustedAmount;
    }

    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	@JsonCreator
    public static ReceiptItem create(String jsonString) {
        ReceiptItem o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ReceiptItem.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
