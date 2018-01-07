package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.web.module.core.vo.Document;

public class RefundPayment extends Document {

	private String referenceNo;
	private String sourceNo;
	private String auditNo;
	private String description;
	private BigDecimal amount;
	private Date issuedDate;
	private AdvancePayment payments;
	private String voucherNo;

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getIssuedDate() {
		return issuedDate;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate;
	}

	public AdvancePayment getPayments() {
		return payments;
	}

	public void setPayments(AdvancePayment payments) {
		this.payments = payments;
	}

    public String getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(String voucherNo) {
		this.voucherNo = voucherNo;
	}

	@JsonCreator
    public static RefundPayment create(String jsonString) {
    	RefundPayment o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, RefundPayment.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
