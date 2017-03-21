package my.edu.umk.pams.account.financialaid.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.financialaid.US_AC_FNA_1000;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.model.AcCoverage;
import my.edu.umk.pams.account.identity.model.AcCoverageImpl;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.service.IdentityService;


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
    AcSponsor sponsor;
 
    @ProvidedScenarioState
    AcSettlement settlement;
    
	@ProvidedScenarioState
	AcCoverage coverage;

	@ProvidedScenarioState
	Long coverageId;

	public WhenIAddASponsorAndSettlementDetails() {
		coverage = new AcCoverageImpl();
	}

	@As("I add sponsor coverage")
	public WhenIAddASponsorAndSettlementDetails I_add_sponsor_$_with_coverage(String sponsorNo) {

		sponsor = identityService.findSponsorBySponsorNo(sponsorNo);
		
		coverage = new AcCoverageImpl();
		coverage.setSponsor(sponsor);
    	coverage.setChargeCode(accountService.findChargeCodeById(2L));
    	
    	AcCoverage coverage2 = new AcCoverageImpl();
    	coverage2.setSponsor(sponsor);
    	coverage2.setChargeCode(accountService.findChargeCodeById(3L));
    	identityService.addCoverage(sponsor, coverage2);

    	return self();
		
	}
	
	@As("I want to add settlement process for sponsor")
	public WhenIAddASponsorAndSettlementDetails I_want_to_add_settlement_process_for_sponsor$(String sponsorNo, String academicSessionCode) {
		
		AcAcademicSession sessionCode = accountService.findAcademicSessionByCode(academicSessionCode);
		AcSponsor SponsorNo = identityService.findSponsorBySponsorNo(sponsorNo);
		
		LOG.debug("session " + sessionCode.getId());
		LOG.debug("sponsor " + SponsorNo.getId());
		
		settlement = new AcSettlementImpl();
		settlement.setDescription(SponsorNo.getIdentityNo() + ";" + SponsorNo.getEmail());
		settlement.setSession(sessionCode);
		settlement.setSponsor(SponsorNo);
		
		financialAidService.initSettlement(settlement);
		
		return self();
	}

}
