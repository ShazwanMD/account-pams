package my.edu.umk.pams.account.billing.stage;

import java.util.Date;

import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcDebitNoteImpl;
import my.edu.umk.pams.account.billing.service.BillingService;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

public class WhenCreateDebitNote extends Stage<WhenCreateDebitNote> {
	
	//private static final Logger LOG = LoggerFactory.getLogger(WhenCreateDebitNote.class);
	
	@Autowired
	BillingService billingService;
	
	@ProvidedScenarioState
	AcDebitNote debitNote;
	
	public WhenCreateDebitNote Create_debit_note(){
		
		String referenceNo = "rn1";
		String description = "de1";
		Date issuedDate = new Date();
		
		debitNote = new AcDebitNoteImpl();
		debitNote.setReferenceNo(referenceNo);
		debitNote.setDescription(description);
		debitNote.setIssuedDate(issuedDate);
		System.out.println(debitNote.toString());
		billingService.startDebitNoteTask(debitNote);
		
		return self();
	}
}
