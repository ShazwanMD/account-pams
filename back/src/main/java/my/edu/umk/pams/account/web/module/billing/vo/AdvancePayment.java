package my.edu.umk.pams.account.web.module.billing.vo;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.core.vo.Document;

public class AdvancePayment extends Document {

	private String referenceNo;
	private String description;
	private BigDecimal amount;
	private BigDecimal balanceAmount;
	private Boolean status;
	private Receipt receipt;
	private List<Knockoff> knockoff;
	private Account account;

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
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

	public BigDecimal getBalanceAmount() {
		return balanceAmount;
	}

	public void setBalanceAmount(BigDecimal balanceAmount) {
		this.balanceAmount = balanceAmount;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public List<Knockoff> getKnockoff() {
		return knockoff;
	}

	public void setKnockoff(List<Knockoff> knockoff) {
		this.knockoff = knockoff;
	}
	
	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@JsonCreator
	public static AdvancePayment create(String jsonString) {
		AdvancePayment o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, AdvancePayment.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}
}
