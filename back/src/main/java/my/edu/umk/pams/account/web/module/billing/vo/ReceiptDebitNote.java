package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.Document;

public class ReceiptDebitNote extends Document {
	
	private Receipt receipt;
	private DebitNote debitNote;

	public Receipt getReceipt() {
		return receipt;
	}

	public void setReceipt(Receipt receipt) {
		this.receipt = receipt;
	}
	
	public DebitNote getDebitNote() {
		return debitNote;
	}

	public void setDebitNote(DebitNote debitNote) {
		this.debitNote = debitNote;
	}

	@JsonCreator
    public static ReceiptDebitNote create(String jsonString) {
		ReceiptDebitNote o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, ReceiptDebitNote.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
