package my.edu.umk.pams.account.billing.workflow.router;

import my.edu.umk.pams.account.billing.model.AcReceipt;
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
@Component("receiptRouter")
public class ReceiptRouter extends RouterServiceSupport{

    private static final Logger LOG = LoggerFactory.getLogger(ReceiptRouter.class);

    @Autowired
    private BillingService billingService;

    public List<String> findRegistererCandidates(Long receiptId) {
        Validate.notNull(receiptId, "Id must not be null");

        String candidate = null;
        String pegawai = null;
        
        AcReceipt receipt = billingService.findReceiptById(receiptId);
        candidate = "GRP_ADM";
        pegawai = "GRP_PGW_ADM_BEND";

        // publish access event
        // publishAccessEvent(receipt, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, pegawai);
    }

    public List<String> findVerifierCandidates(Long receiptId) {
        Validate.notNull(receiptId, "Id must not be null");

        String candidate = null;
        String pegawai = null;

        AcReceipt receipt = billingService.findReceiptById(receiptId);
        candidate = "GRP_ADM";
        pegawai = "GRP_PGW_ADM_BEND";        

        // publish access event
        // publishAccessEvent(receipt, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, pegawai);
    }
}
