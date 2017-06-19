package my.edu.umk.pams.account.billing.chain;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeType;
import my.edu.umk.pams.account.account.model.AcSecurityCharge;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceItem;
import my.edu.umk.pams.account.billing.model.AcInvoiceItemImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 */
@Component("securityChargeAttachChain")
public class CompoundChargeAttachChain extends ChainSupport<ChargeContext> {

    private static final Logger LOG = LoggerFactory.getLogger(CompoundChargeAttachChain.class);

    @Override
    public boolean process(ChargeContext context) throws Exception {
        // unwrap
        AcInvoice invoice = context.getInvoice();
        AcAccountCharge charge = context.getCharge();

        if (!AcAccountChargeType.SECURITY.equals(charge.getChargeType()))
            return false;

        LOG.debug("Attaching {} to {} ", charge.getReferenceNo(), invoice.getReferenceNo());

        AcSecurityCharge securityCharge = ((AcSecurityCharge) charge);
        AcInvoiceItem item = new AcInvoiceItemImpl();
        item.setDescription(String.format("Security Charge; %s; %s", invoice.getSession().getCode(), invoice.getAccount().getActor().getName()));
        item.setAmount(charge.getAmount());
        item.setChargeCode(securityCharge.getChargeCode());
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
