package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
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
public class WhenListStudentCharges extends Stage<WhenListStudentCharges> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenListStudentCharges.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;
	
	@ProvidedScenarioState
    private List<AcAccountCharge> accountCharges;

	
	public WhenListStudentCharges I_want_to_list_student_charges_of_type_academic_by_account_$(String matricNo) {
		//cari student untuk cari account 
				student = identityService.findStudentByMatricNo(matricNo);
				
				//cari akaun yg dpt dr student
				account = accountService.findAccountByActor(student);
				
				//senarai acc charge dari akaun yg kita jmp utk student ni
				accountCharges= accountService.findAccountCharges(account);
				for(AcAccountCharge accountCharges : accountCharges){
					LOG.debug("Description "+ accountCharges.getDescription());
					LOG.debug("Session "+ accountCharges.getSession().getCode());
				}
		return self();
	}
}
