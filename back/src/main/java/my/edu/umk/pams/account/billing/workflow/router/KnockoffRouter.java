package my.edu.umk.pams.account.billing.workflow.router;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.billing.model.AcKnockoff;
import my.edu.umk.pams.account.billing.model.AcReceipt;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.router.RouterServiceSupport;

@Component("knockoffRouter")
public class KnockoffRouter extends RouterServiceSupport{

    private static final Logger LOG = LoggerFactory.getLogger(KnockoffRouter.class);

    @Autowired
    private BillingService billingService;

    public List<String> findRegistererCandidates(Long knockoffId) {
        Validate.notNull(knockoffId, "Id must not be null");

        String candidate = null;
        AcKnockoff knockoff = billingService.findKnockoffById(knockoffId);
        candidate = "GRP_ADM";

        // publish access event
        // publishAccessEvent(receipt, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate);
    }

    public List<String> findVerifierCandidates(Long knockoffId) {
        Validate.notNull(knockoffId, "Id must not be null");

        String candidate = null;

        AcKnockoff knockoff = billingService.findKnockoffById(knockoffId);
        candidate = "GRP_ADM";

        // publish access event
        // publishAccessEvent(receipt, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate);
    }
}
