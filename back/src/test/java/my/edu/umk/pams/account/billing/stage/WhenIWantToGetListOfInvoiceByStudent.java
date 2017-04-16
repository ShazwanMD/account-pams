package my.edu.umk.pams.account.billing.stage;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIWantToGetListOfInvoiceByStudent extends Stage<WhenIWantToGetListOfInvoiceByStudent> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToGetListOfInvoiceByStudent.class);

	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private FinancialAidService financialAidService;
	
	@Autowired
	private BillingService billingService;

	@ProvidedScenarioState
	private AcStudent student;
	
	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private List<AcSponsorship> sponsorship;
	
	@Autowired
	private AccountService accountService;
	
	@ProvidedScenarioState
	private AcSettlement settlement;

	@ProvidedScenarioState
	private List<AcInvoice> invoices;
	

	@As("I want to get list of invoice by student")
	public WhenIWantToGetListOfInvoiceByStudent I_want_to_get_list_of_invoice_by_student$(String matricNo) {

		// cari student untuk cari account
		List<AcStudent> students = identityService.findStudents(0, 100);
		for (AcStudent student : students) {

			AcAccount account = accountService.findAccountByActor(student);
			
			List<AcInvoice> invoices = billingService.findInvoices(account, 0, 100);
			
			for (AcInvoice invoice : invoices) {
				LOG.debug("Name : {}",  invoice.getAccount().getActor().getName());

				}
		}

	return self();
		
	}
}

