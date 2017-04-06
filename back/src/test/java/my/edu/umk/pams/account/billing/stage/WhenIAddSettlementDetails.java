package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.model.AcSponsor;

public class WhenIAddSettlementDetails extends Stage<WhenIAddSettlementDetails>{

	private static final Logger LOG = LoggerFactory.getLogger(WhenIAddSettlementDetails.class);
	
    @Autowired
    private FinancialAidService financialAidService;
    
	@ExpectedScenarioState
	private AcAcademicSession academicSession;
	
	@ExpectedScenarioState
	private AcSponsor sponsor;
	
    @ProvidedScenarioState
	private AcSettlement settlement;
    
    @ProvidedScenarioState
	private AcInvoice invoice;
	
	@As("I want to start settlement process for sponsor")
	public WhenIAddSettlementDetails I_want_to_start_settlement_process_for_sponsor$() {
		LOG.debug("session " + academicSession.getId());
		LOG.debug("sponsor " + sponsor.getId());
		
		Assert.notNull(academicSession, "Academic Session was null");
		Assert.notNull(sponsor, "Sponsor was null");
		
		settlement = new AcSettlementImpl();
		settlement.setDescription(sponsor.getIdentityNo() + ";" + sponsor.getEmail());
		settlement.setSession(academicSession);
		settlement.setSponsor(sponsor);
		
		financialAidService.initSettlement(settlement);
		
		return self();
	}
}
