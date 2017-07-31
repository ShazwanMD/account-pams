package my.edu.umk.pams.account.web.module.account.vo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AccountActivityHolder {

	    private String sourceNo;
	    private AccountTransactionCode transactionCode;
	    private BigDecimal totalAmount;

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
