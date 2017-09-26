package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.Document;

public class WaiverDebitNote extends Document {

	private WaiverFinanceApplication waiverFinanceApplication;
	private DebitNote debitNote;

	public WaiverFinanceApplication getWaiverFinanceApplication() {
		return waiverFinanceApplication;
	}

	public void setWaiverFinanceApplication(WaiverFinanceApplication waiverFinanceApplication) {
		this.waiverFinanceApplication = waiverFinanceApplication;
	}

	public DebitNote getDebitNote() {
		return debitNote;
	}

	public void setDebitNote(DebitNote debitNote) {
		this.debitNote = debitNote;
	}

	@JsonCreator
	public static WaiverDebitNote create(String jsonString) {
		WaiverDebitNote o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, WaiverDebitNote.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}
}
