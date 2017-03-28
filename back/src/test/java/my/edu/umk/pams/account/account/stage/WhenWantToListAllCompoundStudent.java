package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class WhenWantToListAllCompoundStudent extends Stage<WhenWantToListAllCompoundStudent> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenWantToListAllCompoundStudent.class);

	@Autowired
	private AccountService accountService;

	public WhenWantToListAllCompoundStudent I_want_to_list_all_compound_student() {

		AcAccount account = accountService.findAccountByCode("A17P001");
		List<AcAccountCharge> charges = accountService.findAccountCharges(account);
		for (AcAccountCharge accountcharge : charges) {
			LOG.debug(accountcharge.getDescription());
		}

		return self();

	}

}
