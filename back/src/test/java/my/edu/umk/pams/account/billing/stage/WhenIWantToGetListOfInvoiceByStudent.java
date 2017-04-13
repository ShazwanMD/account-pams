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

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
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

	@ProvidedScenarioState
	private AcStudent student;
	
	@ExpectedScenarioState
	private AcAcademicSession academicSession;
	
	@ExpectedScenarioState
	private AcAccount account;

	@ProvidedScenarioState
	private List<AcSponsorship> sponsorship;
	
	@ProvidedScenarioState
	private AcSettlement settlement;

	@ExpectedScenarioState
	private AcInvoice invoice;
	

	@As("I want to get list of invoice by student")
	public WhenIWantToGetListOfInvoiceByStudent I_want_to_get_list_of_invoice_by_student$(String matricNo) {

		// cari student untuk cari account
		student = identityService.findStudentByMatricNo(matricNo);
		LOG.debug("Student No " + student.getMatricNo());
		
		sponsorship = identityService.findSponsorships(student);
		
		for (AcSponsorship sponsorship : sponsorship) {

			LOG.debug("Sponsorship " + sponsorship.getSponsor().getName());
			AcSponsor sponsor = identityService.findSponsorBySponsorNo(sponsorship.getSponsor().getIdentityNo());

			settlement = new AcSettlementImpl();
			settlement.setDescription(sponsor.getName() + " " + sponsor.getId());
			settlement.setSession(academicSession);
			settlement.setSponsor(sponsor);
			settlement.setReferenceNo("daaa111");

			financialAidService.initSettlement(settlement);

			invoice = new AcInvoiceImpl();
			invoice.setDescription(settlement.getId() + " " + settlement.getSession());
			invoice.setSession(academicSession);
			invoice.setReferenceNo("dddd1111");
			invoice.setIssuedDate(new Date());
			

			financialAidService.executeSettlement(settlement);
		}
	

	return self();
		
	}

}
