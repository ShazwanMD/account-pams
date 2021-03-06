package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcProgramCode;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItemImpl;
import my.edu.umk.pams.account.financialaid.model.AcSettlementStatus;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@JGivenStage
public class WhenIGenerateInvoice extends Stage<WhenIGenerateInvoice> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateInvoice.class);

	@Autowired
	private IdentityService identityService;

	@Autowired
	private FinancialAidService financialAidService;

	@Autowired
	private CommonService commonService;
	
	@Autowired
	private BillingService billingService;
	
	@Autowired
	private AccountService accountService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ExpectedScenarioState
	private AcFacultyCode facultyCode;

	@ExpectedScenarioState
	private AcProgramCode programCode;

	@ExpectedScenarioState
	private List<AcCohortCode> cohortCode;

	@ExpectedScenarioState
	private List<AcSponsorship> sponsorship;

	@ProvidedScenarioState
	private AcSettlement settlement;

	@ExpectedScenarioState
	private AcInvoice invoice;

	@As("I generate invoice by faculty")
	public WhenIGenerateInvoice I_generate_invoice_by_faculty$(String code) {

		facultyCode = commonService.findFacultyCodeByCode(code);
		LOG.debug("Faculty Code " + facultyCode.getCode());

		sponsorship = identityService.findSponsorships(facultyCode);

		for (AcSponsorship sponsorship : sponsorship) {

			LOG.debug("Sponsorship " + sponsorship.getSponsor().getName());
			AcSponsor sponsor = identityService.findSponsorBySponsorNo(sponsorship.getSponsor().getIdentityNo());

			settlement = new AcSettlementImpl();
			settlement.setDescription(sponsor.getName() + " " + sponsor.getId());
			settlement.setSession(academicSession);

			financialAidService.initSettlementByFacultyCode(settlement, facultyCode); /// todo: by?? byCohortCOde, byFacultyCode

			invoice = new AcInvoiceImpl();
			invoice.setDescription(settlement.getId() + " " + settlement.getSession());
			invoice.setSession(academicSession);

			financialAidService.executeSettlement(settlement);
		}

		return self();

	}

	@As("I generate invoice by cohort")
	public WhenIGenerateInvoice I_generate_invoice_by_cohort() {

		LOG.debug("session " + academicSession.getId());
		
		List<AcCohortCode> cohortCode = commonService.findCohortCodes();

		for (AcCohortCode cohortCodes : cohortCode) {

			settlement = new AcSettlementImpl();
			settlement.setDescription(cohortCodes.getCode());
			settlement.setSession(academicSession);

			financialAidService.initSettlementByCohortCode(settlement, cohortCodes);
			
			List<AcStudent> students = identityService.findStudentByCohortCode(cohortCodes);
	        for (AcStudent student : students) {
	            AcSettlementItem item = new AcSettlementItemImpl();
	            item.setSettlement(settlement);
	            item.setAccount(accountService.findAccountByActor(student));
	            item.setStatus(AcSettlementStatus.NEW);
	            financialAidService.addSettlementItem(settlement, item);
	        }

			invoice = new AcInvoiceImpl();
			invoice.setDescription(settlement.getId() + " " + settlement.getSession());
			invoice.setSession(academicSession);

			financialAidService.executeSettlement(settlement);
		}

		return self();
	}

	@As("I generate invoice by individually")
	public WhenIGenerateInvoice I_generate_invoice_by_individually$(String matricNo) {

		AcStudent student = identityService.findStudentByMatricNo(matricNo);
		LOG.debug("Student matric No " + student.getMatricNo());

		sponsorship = identityService.findSponsorships(student);

		for (AcSponsorship sponsorship : sponsorship) {

			LOG.debug("Sponsorship " + sponsorship.getSponsor().getName());
			AcSponsor sponsor = identityService.findSponsorBySponsorNo(sponsorship.getSponsor().getIdentityNo());

			settlement = new AcSettlementImpl();
			settlement.setDescription(sponsor.getName() + " " + sponsor.getId());
			settlement.setSession(academicSession);

			financialAidService.initSettlement(settlement);

			invoice = new AcInvoiceImpl();
			invoice.setDescription(settlement.getId() + " " + settlement.getSession());
			invoice.setSession(academicSession);

			financialAidService.executeSettlement(settlement);
		}

		return self();
	}
	
	@As("I generate invoice by program")
	public WhenIGenerateInvoice I_generate_invoice_by_program$(String code) {
		
		programCode = commonService.findProgramCodeByCode(code);
		LOG.debug("Program Code " + programCode.getDescription());
				
		sponsorship = identityService.findSponsorships(programCode);
		
		for (AcSponsorship sponsorship : sponsorship) {

			LOG.debug("Sponsorship " + sponsorship.getSponsor().getName());
			AcSponsor sponsor = identityService.findSponsorBySponsorNo(sponsorship.getSponsor().getIdentityNo());

			settlement = new AcSettlementImpl();
			settlement.setDescription(sponsor.getName() + " " + sponsor.getId());
			settlement.setSession(academicSession);

			financialAidService.initSettlement(settlement);

			invoice = new AcInvoiceImpl();
			invoice.setDescription(settlement.getId() + " " + settlement.getSession());
			invoice.setSession(academicSession);

			financialAidService.executeSettlement(settlement);
		}

		return self();
	}

}
