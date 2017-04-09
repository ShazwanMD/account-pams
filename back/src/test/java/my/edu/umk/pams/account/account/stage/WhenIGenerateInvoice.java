package my.edu.umk.pams.account.account.stage;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementImpl;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.financialaid.stage.WhenIAddSettlementDetails;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.system.service.SystemService;

@JGivenStage
public class WhenIGenerateInvoice extends Stage<WhenIGenerateInvoice> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateInvoice.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private FinancialAidService financialAidService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private BillingService billingService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcInvoice invoice;

	@As("I want to generate invoice individual by student matric number")
	public WhenIGenerateInvoice I_want_to_generate_individual_by_student_$() {
		LOG.debug("session " + academicSession.getId());
		LOG.debug("sponsor " + student.getId());

		Assert.notNull(academicSession, "Academic Session was null");
		Assert.notNull(student, "Student was null");

		AcAccount account = accountService.findAccountByActor(student);

		// generate reference no
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("academicSession", accountService.findCurrentAcademicSession());
		String referenceNo = systemService.generateFormattedReferenceNo(AccountConstants.INVOICE_REFERENCE_NO, map);

		// generate reference no
		Map<String, Object> mapInvoice = new HashMap<String, Object>();
		mapInvoice.put("academicSession", accountService.findCurrentAcademicSession());
		String invoiceNo = systemService.generateFormattedReferenceNo(AccountConstants.INVOICE_REFERENCE_NO,
				mapInvoice);

		invoice = new AcInvoiceImpl();
		invoice.setReferenceNo(referenceNo);
		invoice.setAccount(account);
		invoice.setSession(academicSession);
		invoice.setIssuedDate(new Date());
		invoice.setTotalAmount(BigDecimal.ZERO);
		invoice.setBalanceAmount(BigDecimal.ZERO);
		invoice.setPaid(false);
		invoice.setInvoiceNo(invoiceNo);
		invoice.setDescription(academicSession + " " + account.getCode());
		invoice.setSession(academicSession);

		billingService.startInvoiceTask(invoice);
		return self();
	}
}
