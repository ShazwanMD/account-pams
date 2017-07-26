package my.edu.umk.pams.account.billing.chain;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
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
            AcInvoiceItem item = new AcInvoiceItemImpl();
            item.setDescription(String.format("Admission Charge; %s; %s", invoice.getSession().getCode(), invoice.getAccount().getActor().getName()));
            // todo(hajar): pretax, tax, total
//            item.setPretaxAmount(ZERO);
//            item.setTaxAmount(ZERO);
//            item.setTotalAmount(ZERO);
            item.setAmount(scheduleItem.getAmount());
            item.setBalanceAmount(scheduleItem.getAmount());
            item.setChargeCode(scheduleItem.getChargeCode());
            item.setTaxCode(scheduleItem.getChargeCode().getTaxCode());
            item.setInvoice(invoice);
            totalAmount = totalAmount.add(scheduleItem.getAmount());
            invoiceDao.addItem(invoice, item, securityService.getCurrentUser());
            sessionFactory.getCurrentSession().flush();
        }
        invoice.setTotalAmount(invoice.getTotalAmount().add(totalAmount));
        invoice.setBalanceAmount(invoice.getBalanceAmount().add(totalAmount));
        admissionCharge.setInvoice(invoice);
        accountChargeDao.update(admissionCharge, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        return true;
    }
}
