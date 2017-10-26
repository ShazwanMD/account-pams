package my.edu.umk.pams.account.billing.chain;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.identity.model.AcStudent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static java.math.BigDecimal.ZERO;

/**
 * @author PAMS
 */
@Component("admissionChargeAttachChain")
public class AdmissionChargeAttachChain extends ChainSupport<ChargeContext> {

    private static final Logger LOG = LoggerFactory.getLogger(AdmissionChargeAttachChain.class);

    @Autowired
    private AccountService accountService;
    
    @Autowired
    private BillingService billingService;    

    // TODO: update invoice total amount
    @Override
    public boolean process(ChargeContext context) throws Exception {
        // unwrap
        AcInvoice invoice = context.getInvoice();
        AcAccountCharge charge = context.getCharge();

        if (!AcAccountChargeType.ADMISSION.equals(charge.getChargeType()))
            return false;

        AcAccountCharge admissionCharge = charge;
        AcFeeSchedule feeSchedule = accountService
                .findFeeScheduleByCohortCodeAndResidencyCodeAndStudyMode(
                        admissionCharge.getCohortCode(),
                        ((AcStudent) invoice.getAccount().getActor()).getResidencyCode(),
                        admissionCharge.getStudyMode());

        List<AcFeeScheduleItem> scheduleItems = accountService.findFeeScheduleItems(feeSchedule);
        LOG.debug("found {} schedule items ", scheduleItems.size());
        BigDecimal totalAmount = ZERO;
        for (AcFeeScheduleItem scheduleItem : scheduleItems) {
        	
        	if(scheduleItem.getOrdinal() == admissionCharge.getOrdinal()){
            AcInvoiceItem item = new AcInvoiceItemImpl();
            item.setDescription(scheduleItem.getChargeCode().getDescription());
            item.setAmount(scheduleItem.getAmount());
            item.setChargeCode(scheduleItem.getChargeCode());
            item.setInvoice(invoice);
            billingService.calculateNetAmount(item);
            invoiceDao.addItem(invoice, item, securityService.getCurrentUser());
            sessionFactory.getCurrentSession().flush();
        	}
        }
        invoice.setTotalAmount(invoiceDao.sumTotalAmount(invoice, securityService.getCurrentUser()));
        invoice.setBalanceAmount(invoiceDao.sumTotalAmount(invoice, securityService.getCurrentUser()));
        admissionCharge.setInvoice(invoice);
        accountChargeDao.update(admissionCharge, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        return true;
    }
}
