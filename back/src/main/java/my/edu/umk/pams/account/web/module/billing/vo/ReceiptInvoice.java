package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.Document;

public class ReceiptInvoice extends Document {
	
	private Receipt receipt;
	private Invoice invoice;

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}
	
	@JsonCreator
    public static ReceiptInvoice create(String jsonString) {
		ReceiptInvoice o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ReceiptInvoice.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
