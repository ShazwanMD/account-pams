package my.edu.umk.pams.account.billing.stage;

import io.jsonwebtoken.lang.Assert;

import java.math.BigDecimal;





import org.springframework.beans.factory.annotation.Autowired;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.model.AcStudent;

@JGivenStage
public class WhenIMakeInvoiceWithChargeCode extends
		Stage<WhenIMakeInvoiceWithChargeCode> {

	@Autowired
	private AccountService accountService;

	@Autowired
	private BillingService billingService;

	@ExpectedScenarioState
	private AcStudent student;

	@ProvidedScenarioState
	private AcAccount account;

	@ProvidedScenarioState
	private AcChargeCode chargeCode;

	public WhenIMakeInvoiceWithChargeCode I_make_invoice_given_charge_code(
			String code) {
		
		Assert.notNull(student,"Student cannot be null");
		String noStudent = student.getIdentityNo();
		BigDecimal a = new BigDecimal("2.00");

		AcInvoice invoice = new AcInvoiceImpl();
		invoice.setDescription("Inv Desc 1");
		invoice.setTotalAmount(a);
		invoice.setInvoiceNo("INV_" + noStudent + 001);
		billingService.saveInvoice(invoice);

		
		AcInvoiceItem invoiceItem = new AcInvoiceItemImpl();
		invoiceItem.setAmount(a);
		invoiceItem.setDescription("Sub Item 1");
		
		chargeCode = accountService.findChargeCodeByCode(code);
		invoiceItem.setChargeCode(chargeCode);
		billingService.addInvoiceItem(invoice, invoiceItem);

		return self();
	}

}
