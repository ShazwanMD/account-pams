package my.edu.umk.pams.account.account.stage;

import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcChargeCode;


@JGivenStage
public class ThenPaymentCanProcess extends Stage<ThenPaymentCanProcess> {
	@ExpectedScenarioState
    private AcChargeCode code;
	
    public ThenPaymentCanProcess Payment_can_be_process() {
    	
    	//how to check for changes with assert?
    	Assert.isNull(code, "No payment");
    	
        return self();

    }

}
