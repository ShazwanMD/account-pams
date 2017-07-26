package my.edu.umk.pams.account.web.module.account.vo;

import java.io.IOException;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.account.model.AcAccountTransactionCode;

public class AccountActivity {

	    private Long id;
	    private String sourceNo;
	    private AcAccountTransactionCode transactionCode;
	    private BigDecimal totalAmount;

	    //transient
	    private Integer transactionCodeOrdinal;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getSourceNo() {
	        return sourceNo;
	    }

	    public void setSourceNo(String sourceNo) {
	        this.sourceNo = sourceNo;
	    }

	    public AcAccountTransactionCode getTransactionCode() {
	        return transactionCode;
	    }

	    public void setTransactionCode(AcAccountTransactionCode transactionCode) {
	        this.transactionCode = transactionCode;
	    }

	    public Integer getTransactionCodeOrdinal() {
	        return transactionCodeOrdinal;
	    }

	    public void setTransactionCodeOrdinal(Integer transactionCodeOrdinal) {
	        this.transactionCodeOrdinal = transactionCodeOrdinal;
	    }

	    public BigDecimal getTotalAmount() {
	        return totalAmount;
	    }

	    public void setTotalAmount(BigDecimal totalAmount) {
	        this.totalAmount = totalAmount;
	    }
	    
	    @JsonCreator
	    public static AccountActivity create(String jsonString) {
	        AccountActivity o = null;
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            o = mapper.readValue(jsonString, AccountActivity.class);
	        } catch (IOException e) {
	            // handle
	        }
	        return o;
	    }

}
