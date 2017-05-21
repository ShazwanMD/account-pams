package my.edu.umk.pams.account.billing.chain;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author PAMS
 */
@Component("admissionChargeAttachChain")
public class AdmissionChargeAttachChain extends ChainSupport<ChargeContext> {

    private static final Logger LOG = LoggerFactory.getLogger(AdmissionChargeAttachChain.class);

    @Autowired
    private AccountService accountService;

    // TODO: update invoice total amount
    @Override
    public boolean process(ChargeContext context) throws Exception {
        // unwrap
        AcInvoice invoice = context.getInvoice();
        AcAccountCharge charge = context.getCharge();

        if (!AcAccountChargeType.ADMISSION.equals(charge.getChargeType()))
            return false;

        AcAdmissionCharge admissionCharge = ((AcAdmissionCharge) charge);
        AcFeeSchedule feeSchedule = accountService
                .findFeeScheduleByCohortCodeAndStudyMode(admissionCharge.getCohortCode(), admissionCharge.getStudyMode());

        List<AcFeeScheduleItem> scheduleItems = accountService.findFeeScheduleItems(feeSchedule);
        LOG.debug("found {} schedule items ", scheduleItems.size());
        for (AcFeeScheduleItem scheduleItem : scheduleItems) {
            AcInvoiceItem item = new AcInvoiceItemImpl();
            item.setDescription(String.format("Admission Charge; %s; %s", invoice.getSession().getCode(), invoice.getAccount().getActor().getName()));
            item.setAmount(scheduleItem.getAmount());
            item.setBalanceAmount(scheduleItem.getAmount());
            item.setChargeCode(scheduleItem.getChargeCode());
            item.setInvoice(invoice);
            invoiceDao.addItem(invoice, item, securityService.getCurrentUser());
            sessionFactory.getCurrentSession().flush();
        }

        invoice.setTotalAmount(invoice.getTotalAmount().add(admissionCharge.getAmount()));
        invoice.setBalanceAmount(invoice.getBalanceAmount().add(admissionCharge.getAmount()));
        admissionCharge.setInvoice(invoice);
        accountChargeDao.update(admissionCharge, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        return true;
    }
}
