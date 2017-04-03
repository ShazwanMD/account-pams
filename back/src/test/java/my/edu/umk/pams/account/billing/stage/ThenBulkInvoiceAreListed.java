package my.edu.umk.pams.account.billing.stage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;

@JGivenStage
public class ThenBulkInvoiceAreListed extends Stage<ThenBulkInvoiceAreListed> {
	private static final Logger LOG = LoggerFactory.getLogger(ThenBulkInvoiceAreListed.class);
	@Autowired
	private BillingService billingService;

	@ExpectedScenarioState
	private AcAccount account;

	public ThenBulkInvoiceAreListed I_can_list_invoice_by_matric_no() {

		List<AcInvoice> invoices = billingService.findInvoices(account, 0, 100);
		Assert.isTrue(!invoices.isEmpty());

	/*	for (AcInvoice acInvoice : invoices) {
			LOG.debug(acInvoice.getReferenceNo());
			LOG.debug(acInvoice.getDescription());
		}*/

		return self();

	}

}
