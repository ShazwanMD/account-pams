package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcActorImpl;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.web.module.identity.vo.Actor;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
@JGivenStage
public class WhenIssueInvoice extends Stage<WhenIssueInvoice> {

	private static final Logger LOG = LoggerFactory
			.getLogger(WhenIssueInvoice.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private BillingService billingService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ProvidedScenarioState
	private AcChargeCode chargeCode;

	@Autowired
	private SessionFactory sessionFactory;

	public WhenIssueInvoice I_charge_student_with_academic_fees() {

		/*
		 * LOG.debug("when i add account charge on " +
		 * academicSession.getCode());
		 * 
		 * account = accountService.findAccountByActor(student);
		 * 
		 * // charge AcAcademicCharge charge = new AcAcademicChargeImpl();
		 * charge.setReferenceNo("CHRG-" + System.currentTimeMillis());
		 * charge.setSourceNo("abc123");
		 * charge.setAmount(BigDecimal.valueOf(200.00));
		 * charge.setDescription("This is a test");
		 * charge.setSession(academicSession);
		 * charge.setChargeCode(accountService
		 * .findChargeCodeByCode("TMGSEB-MBA-00-H79321"));
		 * accountService.addAccountCharge(this.account, charge);
		 */

		return self();
	}

	// create student
	// create charge code
	// create invoice.
	// then - cari invoice based charge tadi
	public void I_generate_invoice_per_matric_no(String MatricNo) {
		
	
		AcStudent student = identityService.findStudentByMatricNo(MatricNo);
		account = accountService.findAccountByActor(student);
		System.out.println(account);
		BigDecimal amount = new BigDecimal(1000.00);

		AcInvoice invoice = new AcInvoiceImpl();
		invoice.setReferenceNo("1234");
		invoice.setInvoiceNo("INV20170329");
		invoice.setTotalAmount(amount);
		invoice.setDescription("INVOICE");
		invoice.setAccount(account);
		billingService.startInvoiceTask(invoice);

	}

	public WhenIssueInvoice I_create_security_charge_to_student(String matricNo) {
		AcStudent student = identityService.findStudentByMatricNo(matricNo);
		account = accountService.findAccountByActor(student);

		AcSecurityCharge charge = new AcSecurityChargeImpl();
		charge.setReferenceNo("CHRG-" + System.currentTimeMillis());
		charge.setSourceNo("abc123");
		charge.setAmount(BigDecimal.valueOf(20.00));
		charge.setDescription("chargeA");
		charge.setSession(academicSession);
		charge.setReferenceNo("SEC_CHRGE"+System.currentTimeMillis());

		chargeCode = accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79321");
		Assert.notNull(chargeCode, "chargeCode cannot be null");

		accountService.addAccountCharge(account, charge);
		return self();


	}
}
