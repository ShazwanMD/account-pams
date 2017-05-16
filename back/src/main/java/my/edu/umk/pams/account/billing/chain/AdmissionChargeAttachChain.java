package my.edu.umk.pams.account.billing.chain;

import my.edu.umk.pams.account.account.model.AcAdmissionCharge;
import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author PAMS
 */
@Component("admissionChargeAttachChain")
public class AdmissionChargeAttachChain extends ChainSupport<ChargeContext> {

    private static final Logger LOG = LoggerFactory.getLogger(AdmissionChargeAttachChain.class);

    // TODO: update invoice total amount
    @Override
    public boolean process(ChargeContext context) throws Exception {
        // unwrap
        AcInvoice invoice = context.getInvoice();
        AcAccountCharge charge = context.getCharge();

        if (!AcAccountChargeType.ADMISSION.equals(charge.getChargeType()))
            return false;

        AcAdmissionCharge academicCharge = ((AcAdmissionCharge) charge);
            AcInvoiceItem item = new AcInvoiceItemImpl();
            item.setDescription(String.format("Admission Charge; %s; %s", invoice.getSession().getCode(), invoice.getAccount().getActor().getName()));
            item.setAmount(academicCharge.getAmount());
            item.setBalanceAmount(academicCharge.getAmount());
            // todo(uda): translate
//            item.setChargeCode(detail.getChargeCode());
            item.setInvoice(invoice);
            invoiceDao.addItem(invoice, item, securityService.getCurrentUser());
            sessionFactory.getCurrentSession().flush();

        invoice.setTotalAmount(invoice.getTotalAmount().add(academicCharge.getAmount()));
        invoice.setBalanceAmount(invoice.getBalanceAmount().add(academicCharge.getAmount()));
        academicCharge.setInvoice(invoice);
        accountChargeDao.update(academicCharge, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        return true;
    }
}
