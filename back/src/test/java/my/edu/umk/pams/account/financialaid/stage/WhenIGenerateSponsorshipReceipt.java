package my.edu.umk.pams.account.financialaid.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptImpl;
import my.edu.umk.pams.account.billing.model.AcReceiptType;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcPaymentMethod;
import my.edu.umk.pams.account.identity.model.AcSponsor;

@JGivenStage
public class WhenIGenerateSponsorshipReceipt extends Stage<WhenIGenerateSponsorshipReceipt> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateSponsorshipReceipt.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private BillingService billingService;

	@ExpectedScenarioState
	private AcAcademicSession academicSession;

	@ExpectedScenarioState
	private AcSponsor sponsor;

	@ProvidedScenarioState
	private AcAccount account;

	@As("I want to generate sponsorship receipt to sponsor")
	public WhenIGenerateSponsorshipReceipt I_want_to_generate_sponsorship_receipt_to_sponsor$() {

		LOG.debug("session " + academicSession.getId());
		LOG.debug("sponsor " + sponsor.getId());

		Assert.notNull(academicSession, "Academic Session was null");
		Assert.notNull(sponsor, "Sponsor was null");

		AcAccount account = accountService.findAccountByActor(sponsor);
		LOG.debug("Account " + account.getCode());

		AcReceipt receipt = new AcReceiptImpl();
		receipt.setDescription(sponsor.getIdentityNo() + ";" + sponsor.getEmail());
		receipt.setPaymentMethod(AcPaymentMethod.BANK_DRAFT);
		receipt.setReceiptType(AcReceiptType.BILLING);
		receipt.setAccount(account);
		billingService.initReceipt(receipt);
		
		billingService.startReceiptTask(receipt);

		return self();

	}
}
