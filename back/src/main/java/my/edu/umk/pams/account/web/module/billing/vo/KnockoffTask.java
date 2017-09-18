package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.Task;

public class KnockoffTask extends Task {

	private Knockoff knockoff;
	private AdvancePayment payments;
	private Date issuedDate;

	public AdvancePayment getPayments() {
		return payments;
	}

	public void setPayments(AdvancePayment payments) {
		this.payments = payments;
	}

	public Knockoff getKnockoff() {
		return knockoff;
	}

	public void setKnockoff(Knockoff knockoff) {
		this.knockoff = knockoff;
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
