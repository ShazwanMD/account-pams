package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenViewStudentTypeCharges extends Stage<ThenViewStudentTypeCharges> {

	private static final Logger LOG = LoggerFactory.getLogger(ThenViewStudentTypeCharges.class);

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAccountCharge accountCharge;

	@ProvidedScenarioState
	private AcStudent student;

	@Autowired
	private AccountService accountService;

	@ExpectedScenarioState
	private AcAccountChargeType chargeType;

	@ExpectedScenarioState
	List<AcAccountCharge> accountCharges;

	public ThenViewStudentTypeCharges I_can_view_student_academic_charges() {

		AcAccountChargeType chargeType = AcAccountChargeType.ACADEMIC;
		List<AcAccountCharge> accountCharge = accountService.findAccountCharges(chargeType);

		LOG.debug("===========Academic Charges============");
		for (AcAccountCharge charge : accountCharge) {

			Assert.notNull(charge, "Charges is empty");

			LOG.debug("Student Name :" + charge.getAccount().getActor().getName());
			LOG.debug("Charge Type :" + charge.getChargeType());
			LOG.debug("Reference No : " + charge.getReferenceNo());
			LOG.debug("Description : " + charge.getDescription());
			LOG.debug("                                       ");

		}
		return self();
	}

	public ThenViewStudentTypeCharges I_can_view_student_security_charges() {

		AcAccountChargeType chargeType = AcAccountChargeType.SECURITY;
		List<AcAccountCharge> accountCharge = accountService.findAccountCharges(chargeType);
		LOG.debug("============Security Charges============");
		for (AcAccountCharge charge : accountCharge) {

			Assert.notNull(charge, "Charges is empty");

			LOG.debug("Student Name :" + charge.getAccount().getActor().getName());
			LOG.debug("Charge Type :" + charge.getChargeType());
			LOG.debug("Reference No : " + charge.getReferenceNo());
			LOG.debug("Description : " + charge.getDescription());
			LOG.debug("                                       ");

		}
		return self();
	}

	public ThenViewStudentTypeCharges I_can_view_student_student_affair_charges() {

		AcAccountChargeType chargeType = AcAccountChargeType.STUDENT_AFFAIRS;
		List<AcAccountCharge> accountCharge = accountService.findAccountCharges(chargeType);
		LOG.debug("========Student Affair Charges=========");
		for (AcAccountCharge charge : accountCharge) {

			Assert.notNull(charge, "Charges is empty");

			LOG.debug("Student Name :" + charge.getAccount().getActor().getName());
			LOG.debug("Charge Type :" + charge.getChargeType());
			LOG.debug("Reference No : " + charge.getReferenceNo());
			LOG.debug("Description : " + charge.getDescription());
			LOG.debug("                                       ");

		}
		return self();
	}
}
