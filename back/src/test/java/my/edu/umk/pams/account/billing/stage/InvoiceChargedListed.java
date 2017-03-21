package my.edu.umk.pams.account.billing.stage;

import java.util.List;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.service.AccountService;

/*public class InvoiceChargedListed extends Stage<InvoiceChargedListed> {

	public InvoiceChargedListed the_invoice_charges_are_listed() {
		return self();
		
	}

}
*/

@JGivenStage
public class InvoiceChargedListed extends Stage<InvoiceChargedListed> {

    private static final Logger LOG = LoggerFactory.getLogger(ThenAccountIsInvoiced.class);

    @Autowired
    private AccountService accountService;

    @ExpectedScenarioState
    AcAcademicSession academicSession;

    @ExpectedScenarioState
    AcAccount account;

    // todo: use $ placeholder
    public InvoiceChargedListed student_account_is_invoiced() {
        LOG.debug("student account is charged {}");
        List<AcAccountCharge> charges = accountService.findAccountCharges(academicSession, account);
        Assert.assertTrue(!(charges.isEmpty()));
        return self();
    }
}
