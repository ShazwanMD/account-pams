package my.edu.umk.pams.account.billing.chain;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcEnrollmentCharge;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author PAMS
 */
@Component("enrollmentChargeAttachChain")
public class EnrollmentChargeAttachChain extends ChainSupport<ChargeContext> {

    private static final Logger LOG = LoggerFactory.getLogger(EnrollmentChargeAttachChain.class);

    @Override
    public boolean process(ChargeContext context) throws Exception {
        // unwrap
        AcInvoice invoice = context.getInvoice();
        AcAccountCharge charge = context.getCharge();

        if (!AcAccountChargeType.ENROLLMENT.equals(charge.getChargeType()))
            return false;

        LOG.debug("Attaching {} to {} ", charge.getReferenceNo(), invoice.getReferenceNo());

        AcEnrollmentCharge enrollmentCharge = ((AcEnrollmentCharge) charge);
        AcInvoiceItem item = new AcInvoiceItemImpl();
        item.setDescription(String.format("Enrollment Charge; %s; %s", invoice.getSession().getCode(), invoice.getAccount().getActor().getName()));
        item.setAmount(enrollmentCharge.getAmount());
        item.setBalanceAmount(enrollmentCharge.getAmount());
        // todo(uda): translate
        // item.setChargeCode(enrollmentCharge.getChargeCode());
        item.setInvoice(invoice);
        invoiceDao.addItem(invoice, item, securityService.getCurrentUser());

        invoice.setTotalAmount(invoice.getTotalAmount().add(enrollmentCharge.getAmount()));
        invoice.setBalanceAmount(invoice.getBalanceAmount().add(enrollmentCharge.getAmount()));
        enrollmentCharge.setInvoice(invoice);
        accountChargeDao.update(enrollmentCharge, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        return true;
    }
}
