package my.edu.umk.pams.account.web.module.account.vo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.account.model.AcAccountChargeType;

public class ActivityChargeHolder {

	private String sourceNo;
    private AccountChargeType transactionCode;
    private BigDecimal totalAmount;
    private Integer transactionCodeOrdinal;
    private Date postedDate;
    private String description;

    public Integer getTransactionCodeOrdinal() {
    	return transactionCodeOrdinal;
    }
    
    public void setTransactionCodeOrdinal(Integer ordinal){
    	this.transactionCodeOrdinal = ordinal;
    }
    
    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public AccountChargeType getTransactionCode() {
        return transactionCode;
    }

    public void setTransactionCode(AccountChargeType transactionCode) {
        this.transactionCode = transactionCode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

	public Date getPostedDate() {
		return postedDate;
	}

	public void setPostedDate(Date postedDate) {
		this.postedDate = postedDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JsonCreator
    public static ActivityChargeHolder create(String jsonString) {
		ActivityChargeHolder o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ActivityChargeHolder.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
