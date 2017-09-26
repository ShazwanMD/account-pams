package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.account.vo.AccountCharge;
import my.edu.umk.pams.account.web.module.core.vo.Document;

public class WaiverAccountCharge extends Document {

	private WaiverFinanceApplication waiverFinanceApplication;
	private AccountCharge accountCharge;
	
	public WaiverFinanceApplication getWaiverFinanceApplication() {
		return waiverFinanceApplication;
	}
	public void setWaiverFinanceApplication(WaiverFinanceApplication waiverFinanceApplication) {
		this.waiverFinanceApplication = waiverFinanceApplication;
	}
	public AccountCharge getAccountCharge() {
		return accountCharge;
	}
	public void setAccountCharge(AccountCharge accountCharge) {
		this.accountCharge = accountCharge;
	}
	
	@JsonCreator
	public static WaiverAccountCharge create(String jsonString) {
		WaiverAccountCharge o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, WaiverAccountCharge.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}
}
