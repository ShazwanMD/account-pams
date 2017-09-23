package my.edu.umk.pams.account.web.module.common.vo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecurityChargeCode {

	private Long id;
	private String section;
	private String description;
	private String offense;
	private String offenseDescription;
	private BigDecimal amount;
	private BigDecimal netAmount;
	private BigDecimal taxAmount;
	private String amountDescription;
	private Boolean inclusive;
	private Boolean active;

	private TaxCode taxCode;
	
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOffense() {
		return offense;
	}

	public void setOffense(String offense) {
		this.offense = offense;
	}

	public String getOffenseDescription() {
		return offenseDescription;
	}

	public void setOffenseDescription(String offenseDescription) {
		this.offenseDescription = offenseDescription;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAmountDescription() {
		return amountDescription;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public BigDecimal getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

	public Boolean getInclusive() {
		return inclusive;
	}

	public void setInclusive(Boolean inclusive) {
		this.inclusive = inclusive;
	}

	public void setAmountDescription(String amountDescription) {
		this.amountDescription = amountDescription;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public TaxCode getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(TaxCode taxCode) {
		this.taxCode = taxCode;
	}

	@JsonCreator
    public static SecurityChargeCode create(String jsonString) {
        SecurityChargeCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, SecurityChargeCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
