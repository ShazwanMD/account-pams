package my.edu.umk.pams.account.billing.stage;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;

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

@JGivenStage
public class WhenIGenerateInvoice extends Stage<WhenIGenerateInvoice> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateInvoice.class);

	@Autowired
	private IdentityService identityService;

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
			settlement.setSponsor(sponsor);

			financialAidService.initSettlement(settlement);

			invoice = new AcInvoiceImpl();
			invoice.setDescription(settlement.getId() + " " + settlement.getSession());
			invoice.setSession(academicSession);

			financialAidService.executeSettlement(settlement);
		}

		return self();

	}

	@As("I generate invoice by batch")
	public WhenIGenerateInvoice I_generate_invoice_by_batch() {
		LOG.debug("session " + academicSession.getId());

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
			settlement.setSponsor(sponsor);

			financialAidService.initSettlement(settlement);

			invoice = new AcInvoiceImpl();
			invoice.setDescription(settlement.getId() + " " + settlement.getSession());
			invoice.setSession(academicSession);

			financialAidService.executeSettlement(settlement);
		}

		return self();
	}
}
