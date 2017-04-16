package my.edu.umk.pams.account.billing.workflow.router;

import my.edu.umk.pams.account.billing.model.AcDebitNote;
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
@Component("debitNoteRouter")
public class DebitNoteRouter extends RouterServiceSupport{

    private static final Logger LOG = LoggerFactory.getLogger(DebitNoteRouter.class);

    @Autowired
    private BillingService billingService;

    public List<String> findRegistererCandidates(Long debitNoteId) {
        Validate.notNull(debitNoteId, "Id must not be null");

        String candidate = null;
        AcDebitNote debitNote = billingService.findDebitNoteById(debitNoteId);
        candidate = "GRP_ADM";

        // publish access event
        // publishAccessEvent(debitNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate);
    }

    public List<String> findVerifierCandidates(Long debitNoteId) {
        Validate.notNull(debitNoteId, "Id must not be null");

        String candidate = null;

        AcDebitNote debitNote = billingService.findDebitNoteById(debitNoteId);
        candidate = "GRP_ADM";

        // publish access event
        // publishAccessEvent(debitNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate);
    }
}
