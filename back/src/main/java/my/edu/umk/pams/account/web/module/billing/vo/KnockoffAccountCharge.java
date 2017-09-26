package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.account.vo.AccountCharge;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

public class KnockoffAccountCharge extends MetaObject {
	
	private AccountCharge accountCharge;
	private Knockoff knockoff;
	
	public AccountCharge getAccountCharge() {
		return accountCharge;
	}
	public void setAccountCharge(AccountCharge accountCharge) {
		this.accountCharge = accountCharge;
	}
	public Knockoff getKnockoff() {
		return knockoff;
	}
	public void setKnockoff(Knockoff knockoff) {
		this.knockoff = knockoff;
	}
	
	@JsonCreator
    public static KnockoffAccountCharge create(String jsonString) {
		KnockoffAccountCharge o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, KnockoffAccountCharge.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
