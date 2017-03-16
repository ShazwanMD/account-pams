package my.edu.umk.pams.account.account.stage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.stage.ThenStudentAccountIsCharged;

public class ThenStudentAffairCompoundDetailsAreRecord extends Stage<ThenStudentAffairCompoundDetailsAreRecord>{
	@ExpectedScenarioState
	private AcAccount account;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@Autowired
	private AccountService accountService;
	
	   public ThenStudentAffairCompoundDetailsAreRecord student_charge_details_is_record() {
			List<AcAccountCharge> charges = accountService.findAccountCharges(academicSession, account);
			Assert.isTrue(!charges.isEmpty());
	        return self();
	    }
	
}
