package my.edu.umk.pams.account.web.module.billing.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.account.vo.ChargeCode;
import my.edu.umk.pams.account.web.module.common.vo.TaxCode;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
public class InvoiceItem extends MetaObject {

    private String description;
    private BigDecimal amount;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private boolean readOnly;
    private ChargeCode chargeCode;
    private TaxCode taxCode;
    private BigDecimal taxAmount;
    private BigDecimal netAmount;
    private BigDecimal balanceAmount;

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
        return chargeCode;
    }

    public void setChargeCode(ChargeCode sodoCode) {
        this.chargeCode = sodoCode;
    }

    public TaxCode getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(TaxCode taxCode) {
        this.taxCode = taxCode;
    }

    public BigDecimal getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount;
    }

    public BigDecimal getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount;
    }

    public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	@JsonCreator
    public static InvoiceItem create(String jsonString) {
        InvoiceItem o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, InvoiceItem.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
