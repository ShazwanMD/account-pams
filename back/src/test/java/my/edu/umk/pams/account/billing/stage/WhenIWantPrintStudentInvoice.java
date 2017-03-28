package my.edu.umk.pams.account.billing.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.service.IdentityService;


@JGivenStage
public class WhenIWantPrintStudentInvoice extends Stage<WhenIWantPrintStudentInvoice>{

	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantPrintStudentInvoice.class);
	
	@Autowired
	private BillingService billingService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private IdentityService identityService;
	
	@ExpectedScenarioState
	private List<AcInvoice> invoice;
	
	@ProvidedScenarioState
	private List<AcActor> actor;
	
	@ProvidedScenarioState
	private AcAccount account;
	
	@As("I want to print student invoice")
	public WhenIWantPrintStudentInvoice I_want_to_print_student_invoice() {
		
		actor = identityService.findActors(AcActorType.STUDENT, 0, 100);
		for(AcActor actor: actor){
			account = accountService.findAccountByActor(actor);
			invoice = billingService.findUnpaidInvoices(account, 0, 100);
			for(AcInvoice invoice: invoice){
				LOG.debug("Invoice No: "+invoice.getInvoiceNo());
				LOG.debug("Reference No: "+invoice.getReferenceNo());
				LOG.debug("Matric No: "+invoice.getAccount().getActor().getIdentityNo());
				LOG.debug("Student Name: "+invoice.getAccount().getActor().getName());
				LOG.debug("Academic Session : "+invoice.getSession().getDescription());
				LOG.debug("Balance Amount : "+invoice.getBalanceAmount());
			}
		}
		return self();
	}
}
