package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.Document;

public class WaiverInvoice extends Document {
	
	private WaiverFinanceApplication waiverFinanceApplication;
	private Invoice invoice;

	public WaiverFinanceApplication getWaiverFinanceApplication() {
		return waiverFinanceApplication;
	}

	public void setWaiverFinanceApplication(WaiverFinanceApplication waiverFinanceApplication) {
		this.waiverFinanceApplication = waiverFinanceApplication;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	@JsonCreator
    public static WaiverInvoice create(String jsonString) {
		WaiverInvoice o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, WaiverInvoice.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
