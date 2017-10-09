package my.edu.umk.pams.account.billing.workflow.router;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.model.AcRefundPayment;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.router.RouterServiceSupport;

@Component("refundRouter")
public class RefundRouter extends RouterServiceSupport{

    private static final Logger LOG = LoggerFactory.getLogger(RefundRouter.class);

    @Autowired
    private BillingService billingService;

    public List<String> findRegistererCandidates(Long refundId) {
        Validate.notNull(refundId, "Id must not be null");

        String candidate = null;
        String kerani = null;
        
        AcRefundPayment refundPayment = billingService.findRefundPaymentById(refundId);
        candidate = "GRP_ADM";
        kerani = "GRP_KRN_ADM_BEND";

        // publish access event
        // publishAccessEvent(receipt, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, kerani);
    }

    public List<String> findVerifierCandidates(Long refundId) {
        Validate.notNull(refundId, "Id must not be null");

        String candidate = null;
        String pegawai = null;

        AcRefundPayment refundPayment = billingService.findRefundPaymentById(refundId);
        candidate = "GRP_ADM";
        pegawai = "GRP_PGW_ADM_BEND";   

        // publish access event
        // publishAccessEvent(receipt, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, pegawai);
    }
}
