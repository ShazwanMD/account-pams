package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcCoverageImpl;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


@JGivenStage
public class WhenIAddASponsorAndSettlementDetails extends Stage<WhenIAddASponsorAndSettlementDetails>{
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenIAddASponsorAndSettlementDetails.class);
	
    @Autowired
    private IdentityService identityService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private FinancialAidService financialAidService;
    
    @ProvidedScenarioState
	private AcSettlement settlement;
    
	@ProvidedScenarioState
	private AcCoverage coverage;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ExpectedScenarioState
	private AcSponsor sponsor;

	public WhenIAddASponsorAndSettlementDetails() {
		coverage = new AcCoverageImpl();
	}

	@As("I add sponsor coverage")
	public WhenIAddASponsorAndSettlementDetails I_add_sponsor_with_coverages() {
		coverage = new AcCoverageImpl();
		coverage.setSponsor(sponsor);
    	coverage.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
    	
    	AcCoverage coverage2 = new AcCoverageImpl();
    	coverage2.setSponsor(sponsor);
    	coverage2.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79331"));
    	identityService.addCoverage(sponsor, coverage2);

    	return self();
	}
	
	@As("I want to start settlement process for sponsor")
	public WhenIAddASponsorAndSettlementDetails I_want_to_start_settlement_process_for_sponsor$() {
		LOG.debug("session " + academicSession.getId());
		LOG.debug("sponsor " + sponsor.getId());
		
		settlement = new AcSettlementImpl();
		settlement.setDescription(sponsor.getIdentityNo() + ";" + sponsor.getEmail());
		settlement.setSession(academicSession);
		settlement.setSponsor(sponsor);
		
		financialAidService.initSettlement(settlement);
		
		return self();
	}

}
