package my.edu.umk.pams.account.billing.stage;

import java.util.Date;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
import my.edu.umk.pams.account.billing.model.AcCreditNoteImpl;
import my.edu.umk.pams.account.billing.service.BillingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class WhenCreateCreditNote extends Stage<WhenCreateCreditNote> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenCreateCreditNote.class);
	
	@Autowired
	BillingService billingService;
	
	//@ProvidedScenarioState
	//AcCreditNote creditNote;
	
	public WhenCreateCreditNote Create_credit_note(){
		
		String referenceNo = "crn";
		String description = "cde";
		Date issuedDate = new Date();
		
		AcCreditNote creditNote = new AcCreditNoteImpl();
		creditNote.setReferenceNo(referenceNo);
		creditNote.setDescription(description);
		creditNote.setIssuedDate(issuedDate);
		
		String refNo = billingService.startCreditNoteTask(creditNote);
		
		LOG.debug("Processed debitNote with refNo {}", refNo);
		return self();
	}
}
