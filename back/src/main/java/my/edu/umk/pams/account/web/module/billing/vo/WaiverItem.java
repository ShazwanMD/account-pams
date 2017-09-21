package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.account.vo.AccountCharge;
import my.edu.umk.pams.account.web.module.account.vo.ChargeCode;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

public class WaiverItem extends MetaObject {

	private String description;
	private BigDecimal dueAmount;
	private BigDecimal totalAmount;
	private BigDecimal appliedAmount;
	private ChargeCode chargeCode;
	private Invoice invoice;
	private AccountCharge accountCharge;
	private WaiverFinanceApplication waiverFinanceApplication;

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

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public BigDecimal getAppliedAmount() {
		return appliedAmount;
	}

	public void setAppliedAmount(BigDecimal appliedAmount) {
		this.appliedAmount = appliedAmount;
	}

	public ChargeCode getChargeCode() {
		return chargeCode;
	}

	public void setChargeCode(ChargeCode chargeCode) {
		this.chargeCode = chargeCode;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public AccountCharge getAccountCharge() {
		return accountCharge;
	}

	public void setAccountCharge(AccountCharge accountCharge) {
		this.accountCharge = accountCharge;
	}

	public WaiverFinanceApplication getWaiverFinanceApplication() {
		return waiverFinanceApplication;
	}

	public void setWaiverFinanceApplication(WaiverFinanceApplication waiverFinanceApplication) {
		this.waiverFinanceApplication = waiverFinanceApplication;
	}

	@JsonCreator
    public static WaiverItem create(String jsonString) {
		WaiverItem o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, WaiverItem.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
