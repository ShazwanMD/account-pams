package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.Task;

public class KnockoffTask extends Task {

	private Knockoff knockoff;
	private Date issuedDate;
	private BigDecimal amount;
	private String description;
	private String paymentNo;
	private Invoice invoice;

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
	
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
	
    @JsonCreator
    public static KnockoffTask create(String jsonString) {
    	KnockoffTask o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, KnockoffTask.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
