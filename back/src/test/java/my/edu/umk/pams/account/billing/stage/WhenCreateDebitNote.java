package my.edu.umk.pams.account.billing.stage;

import java.util.Date;

import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcDebitNoteImpl;
import my.edu.umk.pams.account.billing.service.BillingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class WhenCreateDebitNote extends Stage<WhenCreateDebitNote> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenCreateDebitNote.class);
	
	@Autowired
	BillingService billingService;
	
	//@ProvidedScenarioState
	//AcDebitNote debitNote;
	
	@As("create debit note")
	public WhenCreateDebitNote Create_debit_note(){
		
		String referenceNo = "drn";
		String description = "dde";
		Date issuedDate = new Date();
		
		AcDebitNote debitNote = new AcDebitNoteImpl();
		debitNote.setReferenceNo(referenceNo);
		debitNote.setDescription(description);
		debitNote.setIssuedDate(issuedDate);
		
		String refNo = billingService.startDebitNoteTask(debitNote);
		
		LOG.debug("Processed debitNote with refNo {}", refNo);
		
		return self();
	}
}
