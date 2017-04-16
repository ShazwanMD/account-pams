package my.edu.umk.pams.account.billing.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class ThenICanPrintReport extends Stage<ThenICanPrintReport> {

	private static final Logger LOG = LoggerFactory.getLogger(ThenICanPrintReport.class);

	@Autowired
	private IdentityService identityService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private BillingService billingService;

	@ProvidedScenarioState
	private AcSettlement settlement;

	@ExpectedScenarioState
	private List<AcStudent> students;

	@As("I can print report")
	public ThenICanPrintReport I_can_print_report() {

		// LOG.debug("Name : {}", students :{}, settlement);

		// cari student untuk cari account
		List<AcStudent> students = identityService.findStudents(0, 100);

		for (AcStudent student : students) {

			AcAccount account = accountService.findAccountByActor(student);

			List<AcInvoice> invoices = billingService.findInvoices(account, 0, 100);

			for (AcInvoice invoice : invoices) {

				LOG.debug("======================================");
				LOG.debug("Invoice Number : {}", invoice.getInvoiceNo());
				// LOG.debug("Name : {}", invoice.getDescription());
				LOG.debug("Student Email : {}", invoice.getAccount().getActor().getEmail());
				// LOG.debug("Name : {}", invoice.getAuditNo());
				LOG.debug("Balance Amount : {}", invoice.getBalanceAmount());
				// LOG.debug("Name : {}", invoice.getCreditNotes());
				// LOG.debug("Name : {}", invoice.getDebitNotes());
				LOG.debug("======================================");

			}

		}
		return self();

	}
}
