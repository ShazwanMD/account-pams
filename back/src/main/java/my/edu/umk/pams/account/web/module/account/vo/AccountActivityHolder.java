package my.edu.umk.pams.account.web.module.account.vo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountActivityHolder {

	    private String sourceNo;
	    private AccountTransactionCode transactionCode;
	    private BigDecimal totalAmount;
	    private Date postedDate;
	    private String description;

	    public String getSourceNo() {
	        return sourceNo;
	    }

	    public void setSourceNo(String sourceNo) {
	        this.sourceNo = sourceNo;
	    }

	    public AccountTransactionCode getTransactionCode() {
	        return transactionCode;
	    }

	    public void setTransactionCode(AccountTransactionCode transactionCode) {
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
	    public static AccountActivityHolder create(String jsonString) {
	        AccountActivityHolder o = null;
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            o = mapper.readValue(jsonString, AccountActivityHolder.class);
	        } catch (IOException e) {
	            // handle
	        }
	        return o;
	    }

}
