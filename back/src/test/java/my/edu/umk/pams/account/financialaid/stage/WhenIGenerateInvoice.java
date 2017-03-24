package my.edu.umk.pams.account.financialaid.stage;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenIGenerateInvoice extends Stage<WhenIGenerateInvoice>{

	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateInvoice.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private BillingService billingService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;
	
	@ExpectedScenarioState
	private AcFacultyCode facultyCode;
	
    @As("I generate invoice by faculty")
    public WhenIGenerateInvoice I_generate_invoice_by_faculty$(String FACULTY_CODE){
    	LOG.debug("session " + facultyCode.getCode());

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
