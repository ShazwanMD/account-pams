package my.edu.umk.pams.account.identity.stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcSponsorship;
import my.edu.umk.pams.account.identity.model.AcSponsorshipImpl;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIAddStudentSponsorship extends Stage<WhenIAddStudentSponsorship>{

    @Autowired
    private IdentityService identityService;
    
    @ProvidedScenarioState
    AcSponsor sponsor;
    
    @ProvidedScenarioState
    AcSponsorship sponsorship;

    
    public WhenIAddStudentSponsorship(){
    	sponsorship = new AcSponsorshipImpl();
    }
    
    @As("I add student by sponsorship")
    public WhenIAddStudentSponsorship I_add_student_by_sponsorship$(Long id){

    	//its = 
    	
    	sponsor = identityService.findSponsorById(id);

    	Assert.notNull(sponsor, "sponsor was null");
    	
    	sponsorship.setSponsor(sponsor);
    	sponsorship.setStudent(identityService.findStudentById(2L));
 
    	identityService.addSponsorship(sponsor, sponsorship);
    	
    	Assert.notNull(sponsorship, "sponsorship was null");;
    	
    	return self();
    }

}
