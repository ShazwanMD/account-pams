package my.edu.umk.pams.account.billing.stage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.identity.model.AcActor;

@JGivenStage
public class ThenICanGiveValidInvoice extends Stage<ThenICanGiveValidInvoice>{

	@Autowired
	private AccountService accountService;

	@ExpectedScenarioState
	private List<AcActor> actor;

	@As("I can give valid invoice")
	public ThenICanGiveValidInvoice I_can_give_valid_invoice() {

		for(AcActor actor:actor){
			boolean hasAccount = accountService.hasAccount(actor);
			Assert.isTrue(hasAccount, "actor should have an account");
		}
		return self();
		
	}
}
