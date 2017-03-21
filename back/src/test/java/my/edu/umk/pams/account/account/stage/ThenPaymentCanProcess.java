package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenPaymentCanProcess extends Stage<ThenPaymentCanProcess> {

    @Pending
    public ThenPaymentCanProcess Payment_can_be_process() {
        return self();

    }

}
