package my.edu.umk.pams.account.financialaid.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcSponsorshipImpl;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.springframework.beans.factory.annotation.Autowired;

@JGivenStage
public class WhenIGrantSponsorshipToStudent extends Stage<WhenIGrantSponsorshipToStudent>{

    @Autowired
    private IdentityService identityService;
    
    @ProvidedScenarioState
    AcSponsor sponsor;
    
    @ExpectedScenarioState
    AcStudent student;


    @As("I add student by sponsorship")
    public WhenIGrantSponsorshipToStudent I_grant_sponsorship_of_$_to_the_student(String sponsorNo){
    	
    	sponsor = identityService.findSponsorBySponsorNo(sponsorNo);
    	
    	AcSponsorship sponsorship = new AcSponsorshipImpl();
    	sponsorship.setSponsor(sponsor);
    	sponsorship.setStudent(identityService.findStudentById(2L));
    	identityService.addSponsorship(student, sponsorship);

    	return self();
    }
}
