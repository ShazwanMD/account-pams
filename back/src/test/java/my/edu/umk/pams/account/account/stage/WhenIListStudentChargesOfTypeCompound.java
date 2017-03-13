package my.edu.umk.pams.account.account.stage;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAcademicCharge;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.dao.AcStudentDao;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.bdd.stage.WhenIAddAStudent;

@JGivenStage
public class WhenIListStudentChargesOfTypeCompound extends Stage<WhenIListStudentChargesOfTypeCompound>{
	 private static final Logger LOG = LoggerFactory.getLogger(WhenIListStudentChargesOfTypeCompound.class);
	    
	 @Autowired
	    private AccountService accountService;

	    @Autowired
	    private IdentityService identityService;

	    @ExpectedScenarioState
	    AcAcademicSession academicSession;
	    
	    @ProvidedScenarioState
	    AcAccount account;

	    @ProvidedScenarioState
	    AcAccountCharge accountCharge;

	    @ProvidedScenarioState
	    AcStudent student;
	    
	    @Autowired
	    private SessionFactory sessionFactory;
	    

	    @Pending
	    public void I_list_student_charges_of_type_compound_$(String matricNo) {	
			
		}


}
