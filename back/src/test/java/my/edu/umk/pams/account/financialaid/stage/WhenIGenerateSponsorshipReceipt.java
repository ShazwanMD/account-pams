package my.edu.umk.pams.account.financialaid.stage;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.system.service.SystemService;
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
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcReceiptImpl;
import my.edu.umk.pams.account.billing.model.AcReceiptItem;
import my.edu.umk.pams.account.billing.model.AcReceiptItemImpl;
import my.edu.umk.pams.account.billing.model.AcReceiptType;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcPaymentMethod;
import my.edu.umk.pams.account.identity.model.AcSponsor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JGivenStage
public class WhenIGenerateSponsorshipReceipt extends Stage<WhenIGenerateSponsorshipReceipt> {

	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateSponsorshipReceipt.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private BillingService billingService;

	@Autowired
	private SystemService systemService;

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

		List<AcInvoice> invoice = billingService.findPaidInvoices(account, 0, 0);
		for (AcInvoice invc : invoice) {

			List<AcInvoiceItem> invoiceItem = billingService.findInvoiceItems(invc);
			for (AcInvoiceItem invcItem : invoiceItem) {

				// prepare reference no generator
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("academicSession", accountService.findCurrentAcademicSession());
				String referenceNo = systemService.generateFormattedReferenceNo(AccountConstants.RECEIPT_REFERENCE_NO,
						map);

				Map<String, Object> maps = new HashMap<String, Object>();
				maps.put("academicSession", accountService.findCurrentAcademicSession());
				String receiptNo = systemService.generateFormattedReferenceNo(AccountConstants.RECEIPT_RECEIPT_NO,
						maps);

				LOG.debug("Processing process receipting with refNo {}", referenceNo);
				LOG.debug("Processing process receipting with receiptNo {}", receiptNo);

				AcReceipt receipt = new AcReceiptImpl();
				receipt.setReferenceNo(referenceNo);
				receipt.setDescription(sponsor.getIdentityNo() + ";" + sponsor.getEmail());
				receipt.setPaymentMethod(AcPaymentMethod.BANK_DRAFT);
				receipt.setReceiptType(AcReceiptType.BILLING);
				receipt.setAccount(account);
				receipt.setReceiptNo(receiptNo);
				receipt.setTotalApplied(BigDecimal.ZERO);
				receipt.setTotalReceived(BigDecimal.ZERO);
				receipt.setTotalAmount(invcItem.getAmount());
				receipt.setReceivedDate(new Timestamp(System.currentTimeMillis()));

				billingService.startReceiptTask(receipt);

				AcReceiptItem item = new AcReceiptItemImpl();
				item.setAdjustedAmount(BigDecimal.ZERO);
				item.setAppliedAmount(BigDecimal.ZERO);
				item.setChargeCode(invcItem.getChargeCode());
				item.setDueAmount(BigDecimal.ZERO);
				item.setTotalAmount(BigDecimal.ZERO);
				item.setReceipt(receipt);
				item.setInvoice(invc);

				billingService.addReceiptItem(receipt, item);

			}

		}
		return self();

	}
}
