package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
public class WhenIPayCompound extends Stage<WhenIPayCompound> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIPayCompound.class);

    @ExpectedScenarioState
    private AcStudent student;

    @ProvidedScenarioState
    private AcAccount account;
    
    // todo: uda : boleh pakai WhenIssueInvoice tak?
    public WhenIPayCompound I_pay_the_compound(){
		return self();}


}
