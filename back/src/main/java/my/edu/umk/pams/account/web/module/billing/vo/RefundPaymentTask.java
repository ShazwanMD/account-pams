package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.Task;

public class RefundPaymentTask extends Task {

	private RefundPayment refundPayment;
	private Date issuedDate;
	private BigDecimal amount;
	private String description;
	private String paymentNo;
	
	private String accountCode;
	private String accountName;

	public RefundPayment getRefundPayment() {
		return refundPayment;
	}

	public void setRefundPayment(RefundPayment refundPayment) {
		this.refundPayment = refundPayment;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPaymentNo() {
		return paymentNo;
	}

	public void setPaymentNo(String paymentNo) {
		this.paymentNo = paymentNo;
	}
	
    public String getAccountCode() {
		return accountCode;
	}

	public void setAccountCode(String accountCode) {
		this.accountCode = accountCode;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@JsonCreator
    public static RefundPaymentTask create(String jsonString) {
    	RefundPaymentTask o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, RefundPaymentTask.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
