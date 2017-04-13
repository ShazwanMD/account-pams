package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class ThenICanViewStudentCharges extends Stage<ThenICanViewStudentCharges> {
	
	private static final Logger LOG = LoggerFactory.getLogger(ThenICanViewStudentCharges.class);

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
	private AcChargeCode chargeCode;

	@ExpectedScenarioState
	private AcAccountChargeType chargeType;

	@ExpectedScenarioState
	List<AcAccountCharge> accountCharges;
	
	public ThenICanViewStudentCharges I_can_view_student_charges() {

		Assert.notNull(academicSession, "academic session is a prerequisite");
		return self();
	}

	public ThenICanViewStudentCharges I_can_view_student_academic_compound_charges_$(String code) {
		LOG.debug("========List Student Charges of Type compound by account=========");
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

	public ThenICanViewStudentCharges I_can_view_student_security_compound_charges_$(String code) {

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

	public ThenICanViewStudentCharges I_can_view_student_student_affair_compound_charges_$(String code) {

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
	
	public ThenICanViewStudentCharges I_can_view_student_academic_charges() {

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

	public ThenICanViewStudentCharges I_can_view_student_security_charges() {

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

	public ThenICanViewStudentCharges I_can_view_student_student_affair_charges() {

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
	
	public ThenICanViewStudentCharges I_can_view_student_compound_charges() {

		AcAccountChargeType chargeType = AcAccountChargeType.SECURITY;
		List<AcAccountCharge> accountCharge = accountService.findAccountCharges(chargeType);
		
		for (AcAccountCharge charge : accountCharge) {
			
			Assert.notNull(charge, "Charges is empty");

			LOG.debug("Student Name :" + charge.getAccount().getActor().getName());
			LOG.debug("Charge Type :" + charge.getChargeType());
			LOG.debug("Reference No : " + charge.getReferenceNo());
			LOG.debug("Description : " + charge.getDescription());
			
		}
		return self();
	}
	
	@As("can view students compound charges of type Academic")
	public ThenICanViewStudentCharges can_view_students_compound_charges_of_type_academic_$(String matricNo) {

		AcAccountChargeType chargeType = AcAccountChargeType.ACADEMIC;
		List<AcAccountCharge> accountCharge = accountService.findAccountCharges(chargeType);

		for (AcAccountCharge charge : accountCharge) {

			Assert.notNull(charge, "Charges is empty");

			LOG.debug("Student Name :" + charge.getAccount().getActor().getName());
			LOG.debug("Charge Type :" + charge.getChargeType());
			LOG.debug("Reference No : " + charge.getReferenceNo());
			LOG.debug("Description : " + charge.getDescription());

		}
		return self();
	}
}
