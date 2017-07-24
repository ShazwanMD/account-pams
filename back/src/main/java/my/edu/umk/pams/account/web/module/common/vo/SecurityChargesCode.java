package my.edu.umk.pams.account.web.module.common.vo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SecurityChargesCode {

	private Long id;
	private String section;
	private String description;
	private String offense;
	private String offenseDescription;
	private BigDecimal amount;
	private String amountDescription;
	private Boolean active;

	
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



	public void setAmountDescription(String amountDescription) {
		this.amountDescription = amountDescription;
	}



	public Boolean getActive() {
		return active;
	}



	public void setActive(Boolean active) {
		this.active = active;
	}



	@JsonCreator
    public static SecurityChargesCode create(String jsonString) {
        SecurityChargesCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, SecurityChargesCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
