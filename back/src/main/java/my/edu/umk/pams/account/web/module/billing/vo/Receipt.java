package my.edu.umk.pams.account.web.module.billing.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.common.vo.PaymentMethod;
import my.edu.umk.pams.account.web.module.core.vo.Document;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
public class Receipt extends Document {

	private String referenceNo;
	private String receiptNo;
	private String sourceNo;
	private String auditNo;
	private String description;
	private BigDecimal totalApplied = BigDecimal.ZERO;
	private BigDecimal totalReceived = BigDecimal.ZERO;
	private BigDecimal totalAmount = new BigDecimal(0.00);
	private Date receivedDate;
	private Account account;
	private PaymentMethod paymentMethod;
	private ReceiptType receiptType;
	
	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getSourceNo() {
		return sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	public String getAuditNo() {
		return auditNo;
	}

	public void setAuditNo(String auditNo) {
		this.auditNo = auditNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getTotalApplied() {
		return totalApplied;
	}

	public void setTotalApplied(BigDecimal totalApplied) {
		this.totalApplied = totalApplied;
	}

	public BigDecimal getTotalReceived() {
		return totalReceived;
	}

	public void setTotalReceived(BigDecimal totalReceived) {
		this.totalReceived = totalReceived;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
	public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
	public ReceiptType getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(ReceiptType receiptType) {
        this.receiptType = receiptType;
    }

	@JsonCreator
    public static Receipt create(String jsonString) {
        Receipt o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, Receipt.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
