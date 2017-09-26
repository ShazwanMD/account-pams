package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

public class KnockoffDebitNote extends MetaObject {

	private DebitNote debitNote;
	private Knockoff knockoff;
	
	public DebitNote getDebitNote() {
		return debitNote;
	}
	public void setDebitNote(DebitNote debitNote) {
		this.debitNote = debitNote;
	}
	public Knockoff getKnockoff() {
		return knockoff;
	}
	public void setKnockoff(Knockoff knockoff) {
		this.knockoff = knockoff;
	}
	
	@JsonCreator
    public static KnockoffDebitNote create(String jsonString) {
		KnockoffDebitNote o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, KnockoffDebitNote.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
