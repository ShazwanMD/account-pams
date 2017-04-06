package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.billing.model.AcInvoice;


@JGivenStage
public class ThenICanGenerateInvoiceForSponsor extends Stage<ThenICanGenerateInvoiceForSponsor>{
    
	@ExpectedScenarioState
	private AcAcademicSession academicSession;
    
	@ExpectedScenarioState
	private AcInvoice invoice;
	
	@As("I can generate sponsor invoice")
	public ThenICanGenerateInvoiceForSponsor I_can_generate_sponsor_invoice() {

		Assert.notNull(invoice, "invoice was null");
		
		return self();
	}
}
