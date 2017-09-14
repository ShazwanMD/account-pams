package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

public class KnockoffInvoice extends MetaObject {
	
	private Invoice invoice;
	private Knockoff knockoff;
	
	public Invoice getInvoice() {
		return invoice;
	}
	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	public Knockoff getKnockoff() {
		return knockoff;
	}
	public void setKnockoff(Knockoff knockoff) {
		this.knockoff = knockoff;
	}
	
	@JsonCreator
    public static KnockoffInvoice create(String jsonString) {
		KnockoffInvoice o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, KnockoffInvoice.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
