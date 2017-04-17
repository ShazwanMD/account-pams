package my.edu.umk.pams.account.billing.stage;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.identity.service.IdentityService;

@JGivenStage
public class WhenIssueInvoicePerCriteria extends Stage<WhenIssueInvoicePerCriteria> {
	private static final Logger LOG = LoggerFactory
			.getLogger(WhenIssueInvoicePerCriteria.class);
	@Autowired
	private AccountService accountService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private BillingService billingService;

	@ProvidedScenarioState
	private AcAccount account;

    @As("I generate invoice per matric number")
	public WhenIssueInvoicePerCriteria I_generate_invoice_per_matric_no(String matricNo) {
		// 1) cari student by matric No,
		// 2) cari account student itu.
		// 3) tambah invoice utk account tu .

		AcStudent student = identityService.findStudentByMatricNo(matricNo);
		account = accountService.findAccountByActor(student);
		
		LOG.debug("Processing invoice with refNo {}", new Object[] { account });

		
		BigDecimal amount = new BigDecimal(1000.00);

		AcInvoice invoice = new AcInvoiceImpl();
		invoice.setReferenceNo("1234");
		invoice.setInvoiceNo("INV20170329");
		invoice.setTotalAmount(amount);
		invoice.setDescription("UDA DEWA");
		invoice.setAccount(account);
		billingService.startInvoiceTask(invoice);

		return self();
	}

}
