package my.edu.umk.pams.account.account.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.model.AcStudent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@JGivenStage
public class WhenIWantToViewFeesStatementAndCharges extends Stage<WhenIWantToViewFeesStatementAndCharges> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantToViewFeesStatementAndCharges.class);

	@ExpectedScenarioState
	private AcStudent student;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ProvidedScenarioState
	private AcAccount account;

	@Autowired
	private BillingService billingService;

	@ProvidedScenarioState
	List<AcInvoice> invoices;

	@As("I want to view fees statement and charges")
	public WhenIWantToViewFeesStatementAndCharges I_want_to_view_fees_statement_and_charges() {
		invoices = billingService.findUnpaidInvoices(account, 0, 100);
		for (AcInvoice invoice : invoices) {
			LOG.debug(invoice.getDescription() + " " + invoice.getReferenceNo());
		}

		return self();
	}

}
