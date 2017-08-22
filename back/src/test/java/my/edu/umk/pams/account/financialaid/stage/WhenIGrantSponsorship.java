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
import org.springframework.util.Assert;

@JGivenStage
public class WhenIGrantSponsorship extends Stage<WhenIGrantSponsorship>{

    @Autowired
    private IdentityService identityService;
    
    @ProvidedScenarioState
    AcSponsor sponsor;
    
    @ExpectedScenarioState
    AcStudent student;

    @ExpectedScenarioState
    private String matricNo;
    
    @ExpectedScenarioState
    private String sponsorNo;

    @As("I add student by sponsorship")
    public WhenIGrantSponsorship I_grant_sponsorship_of_$_to_the_student(String sponsorNo){
    	
    	sponsor = identityService.findSponsorBySponsorNo(sponsorNo);
    	  	
    	AcSponsorship sponsorship = new AcSponsorshipImpl();
    	sponsorship.setSponsor(sponsor);
    	//sponsorship.setStudent(identityService.findStudentByMatricNo(matricNo));
    	identityService.addSponsorship(student, sponsorship);

		boolean hasSponsorship = identityService.hasSponsorship(sponsor);
		Assert.isTrue(hasSponsorship, "sponsor should have sponsorship");
    	    	
    	return self();
    }
    
    @As("I add sponsor by sponsorship")
    public WhenIGrantSponsorship I_grant_sponsorship_of_$_to_the_sponsor(String matricNo){
    	
    	student = identityService.findStudentByMatricNo(matricNo);
    	
    	AcSponsorship sponsorship = new AcSponsorshipImpl();
    	sponsorship.setSponsor(identityService.findSponsorBySponsorNo(sponsorNo));
    	//sponsorship.setStudent(student);
     	identityService.addSponsorship(sponsor, sponsorship);
     	
     	boolean hasSponsorship = identityService.hasSponsorship(student);
		Assert.isTrue(hasSponsorship, "student should have sponsorship");
     	
    	return self();
    }
}
