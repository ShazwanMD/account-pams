package my.edu.umk.pams.account.billing.workflow.router;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.router.RouterServiceSupport;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author PAMS
 */
@Component("invoiceRouter")
public class InvoiceRouter extends RouterServiceSupport{

    private static final Logger LOG = LoggerFactory.getLogger(InvoiceRouter.class);

    @Autowired
    private BillingService billingService;

    public List<String> findRegistererCandidates(Long invoiceId) {
        Validate.notNull(invoiceId, "Id must not be null");

        String candidate = null;
        String penPgw = null;
        
        AcInvoice invoice = billingService.findInvoiceById(invoiceId);
        candidate = "GRP_ADM";
        penPgw = "GRP_PEN_PGW_ADM_BEND";
        

        // publish access event
        // publishAccessEvent(invoice, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, penPgw);
    }

    public List<String> findVerifierCandidates(Long invoiceId) {
        Validate.notNull(invoiceId, "Id must not be null");

        String candidate = null;
        String pegawai = null;

        AcInvoice invoice = billingService.findInvoiceById(invoiceId);
        candidate = "GRP_ADM";
        pegawai = "GRP_PGW_ADM_BEND";

        // publish access event
        // publishAccessEvent(invoice, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, pegawai);
    }
}
