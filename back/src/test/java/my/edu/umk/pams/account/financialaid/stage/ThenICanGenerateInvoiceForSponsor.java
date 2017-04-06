package my.edu.umk.pams.account.financialaid.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;

public class ThenICanGenerateInvoiceForSponsor extends Stage<ThenICanGenerateInvoiceForSponsor>{

    @Autowired
    private FinancialAidService financialAidService;
    
	@ExpectedScenarioState
	private AcAcademicSession academicSession;
	
	@ExpectedScenarioState
	private AcSettlement settlement;
    
	@ExpectedScenarioState
	private AcInvoice invoice;
	
	@As("I can generate sponsor invoice")
	public ThenICanGenerateInvoiceForSponsor I_can_generate_sponsor_invoice() {

		Assert.notNull(settlement, "settlement was null");
		
		invoice = new AcInvoiceImpl();
		invoice.setDescription(settlement.getId()+" "+settlement.getSession());
		invoice.setSession(academicSession);
 
		financialAidService.executeSettlement(settlement);
		
		return self();
	}
}
