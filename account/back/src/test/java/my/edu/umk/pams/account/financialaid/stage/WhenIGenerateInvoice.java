package my.edu.umk.pams.account.financialaid.stage;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.service.BillingService;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@JGivenStage
public class WhenIGenerateInvoice extends Stage<WhenIGenerateInvoice>{

	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateInvoice.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private BillingService billingService;
	
    @Autowired
    private FinancialAidService financialAidService;
    
	@Autowired
	private CommonService commonService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;
	
	@ExpectedScenarioState
	private AcFacultyCode facultyCode;
	
	@ExpectedScenarioState
	private List<AcProgramCode> programCode;
	
	@ExpectedScenarioState
	private List<AcCohortCode> cohortCode;
	
	@ExpectedScenarioState
	private List<AcStudent> student;
	
	@ExpectedScenarioState
	private List<AcSponsorship> sponsorship;
	
    @ProvidedScenarioState
	private AcSettlement settlement;
    
	@ExpectedScenarioState
	private AcInvoice invoice;
	
	@ExpectedScenarioState
	private AcSponsor sponsor;
	
    @As("I generate invoice by faculty")
    public WhenIGenerateInvoice I_generate_invoice_by_faculty$(String code){
    	
    	facultyCode = commonService.findFacultyCodeByCode(code);
    	LOG.debug("Faculty Code " + facultyCode.getCode());
    	
    	programCode =  commonService.findProgramCodes(facultyCode);
    	   	
    	for(AcProgramCode programCode: programCode){
    		
    		//LOG.debug("Program Code " + programCode.getCode());
    		cohortCode = commonService.findCohortCodesByProgramCode(programCode);
    		
    		for(AcCohortCode cohortCode: cohortCode){
    			
    			//LOG.debug("Program Code " + cohortCode.getCode());
    			student = identityService.findByCohort(cohortCode);

    			for(AcStudent student: student){
    				
    				//LOG.debug("Student " + student.getMatricNo());
    				sponsorship = identityService.findSponsorships(student);
    				
    				for(AcSponsorship sponsorship : sponsorship ){
	    				
    					sponsor = identityService.findSponsorBySponsorNo(sponsorship.getSponsor().getIdentityNo());
    					//sponsor = identityService.findSponsorBySponsorNo(sponsorship.getStudent().getMatricNo());
    					LOG.debug("Sponsor " + sponsor.getName());
    					
	    				settlement = new AcSettlementImpl();
	    				settlement.setDescription(sponsorship.getSponsor().getName()+" "+sponsorship.getSponsor().getEmail());
	    				settlement.setSession(academicSession);
	    				settlement.setSponsor(sponsor);
	    				
	    				financialAidService.initSettlement(settlement);
	    				
	    				invoice = new AcInvoiceImpl();
	    				invoice.setDescription(settlement.getId()+" "+settlement.getSession());
	    				invoice.setSession(academicSession);
	    		 
	    				financialAidService.executeSettlement(settlement);
    				}
    			}
    		}
    	}
    	return self();
    }
    
    @As("I generate invoice by batch")
    public WhenIGenerateInvoice I_generate_invoice_by_batch(){
    	LOG.debug("session " + academicSession.getId());
    	    	
    	return self();
    }
    
    @As("I generate invoice by individually")
    public WhenIGenerateInvoice I_generate_invoice_by_individually(){
    	return self();
    }
}
