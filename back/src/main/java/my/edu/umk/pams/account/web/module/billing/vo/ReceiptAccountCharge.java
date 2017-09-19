package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.account.vo.AccountCharge;
import my.edu.umk.pams.account.web.module.core.vo.Document;

public class ReceiptAccountCharge extends Document {
	
	private Receipt receipt;
	private AccountCharge accountCharge;

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	
	public AccountCharge getAccountCharge() {
		return accountCharge;
	}

	public void setAccountCharge(AccountCharge accountCharge) {
		this.accountCharge = accountCharge;
	}
	
	@JsonCreator
    public static ReceiptAccountCharge create(String jsonString) {
		ReceiptAccountCharge o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ReceiptAccountCharge.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
