package my.edu.umk.pams.account.billing.stage;

import com.tngtech.jgiven.integration.spring.JGivenStage;

import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.model.AcStudent;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;

@JGivenStage
public class WhenISearchAdvancePaymentById extends Stage<WhenISearchAdvancePaymentById>{

	private static final Logger LOG = LoggerFactory.getLogger(WhenISearchAdvancePaymentById.class);
	
	@Autowired
	private BillingService billingService;

	public WhenISearchAdvancePaymentById I_search_advance_payment() {

		return self();
	}
	
	@As("I search advance payment using ID")
	public WhenISearchAdvancePaymentById I_search_advance_payment_by_id(Long id) {
		
		AcAdvancePayment payment = billingService.findAdvancePaymenById(id);
		Assert.notNull(payment, "payment cannot be null");

		LOG.debug("Student :--> " + payment.getReferenceNo());

		return self();

	}
}
