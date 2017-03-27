package my.edu.umk.pams.account.billing.chain;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcEnrollmentLateCharge;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 */
@Component("enrollmentLateChargeAttachChain")
public class EnrollmentLateChargeAttachChain extends ChainSupport<ChargeContext> {

    private static final Logger LOG = LoggerFactory.getLogger(EnrollmentLateChargeAttachChain.class);

    @Override
    public boolean process(ChargeContext context) throws Exception {
        // unwrap
        AcInvoice invoice = context.getInvoice();
        AcAccountCharge charge = context.getCharge();

        if (!AcAccountChargeType.ENROLLMENT_LATE.equals(charge.getChargeType()))
            return false;

        LOG.debug("Attaching {} to {} ", charge.getReferenceNo(), invoice.getReferenceNo());

        AcEnrollmentLateCharge enrollmentLateCharge = ((AcEnrollmentLateCharge) charge);

        AcInvoiceItem item = new AcInvoiceItemImpl();
        item.setDescription(String.format("Enrollment Late Charge; %s; %s", invoice.getSession().getCode(), invoice.getAccount().getActor().getName()));
        item.setAmount(charge.getAmount());
        // todo(uda): translate
        // item.setChargeCode(enrollmentLateCharge.getChargeCode());
        item.setInvoice(invoice);
        invoiceDao.addItem(invoice, item, securityService.getCurrentUser());

        invoice.setTotalAmount(invoice.getTotalAmount().add(charge.getAmount()));
        invoice.setBalanceAmount(invoice.getBalanceAmount().add(charge.getAmount()));
        charge.setInvoice(invoice);
        accountChargeDao.update(charge, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        return true;
    }
}
