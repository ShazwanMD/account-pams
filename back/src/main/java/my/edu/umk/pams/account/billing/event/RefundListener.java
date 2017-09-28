package my.edu.umk.pams.account.billing.event;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.dao.AcReceiptDao;
import my.edu.umk.pams.account.billing.model.AcAdvancePayment;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcRefundPayment;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.security.event.AccessListener;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;

@Component("refundListener")
public class RefundListener implements ApplicationListener<RefundEvent> {

	private static final Logger LOG = LoggerFactory.getLogger(AccessListener.class);

	@Autowired
	private BillingService billingService;

	@Autowired
	private AccountService accountService;

	@Autowired
	private SystemService systemService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private AcReceiptDao receiptDao;

	@Override
	public void onApplicationEvent(RefundEvent event) {
		if (event instanceof RefundApprovedEvent) {
			AcRefundPayment refund = event.getRefundPayment();
			
			AcAdvancePayment advance = billingService.findAdvancePaymentByReferenceNo(refund.getPayments().getReferenceNo());
			
			BigDecimal balanceAmount = advance.getBalanceAmount().subtract(refund.getAmount());
			advance.setBalanceAmount(balanceAmount);
			billingService.updateAdvancePayment(advance);
		}
	}
}
