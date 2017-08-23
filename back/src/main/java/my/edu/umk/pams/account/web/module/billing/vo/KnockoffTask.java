package my.edu.umk.pams.account.web.module.billing.vo;

import java.math.BigDecimal;
import java.util.Date;

import my.edu.umk.pams.account.web.module.core.vo.Task;

public class KnockoffTask extends Task {

	private Knockoff knockoff;
	private Date issuedDate;
	private BigDecimal amount;
	private String description;
	private String paymentNo;

	public Knockoff getKnockoff() {
		return knockoff;
	}

	public void setKnockoff(Knockoff knockoff) {
		this.knockoff = knockoff;
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

}
