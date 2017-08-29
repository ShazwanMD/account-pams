package my.edu.umk.pams.account.billing.workflow.router;

import my.edu.umk.pams.account.billing.model.AcCreditNote;
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
@Component("creditNoteRouter")
public class CreditNoteRouter extends RouterServiceSupport{

    private static final Logger LOG = LoggerFactory.getLogger(CreditNoteRouter.class);

    @Autowired
    private BillingService billingService;

    public List<String> findRegistererCandidates(Long creditNoteId) {
        Validate.notNull(creditNoteId, "Id must not be null");

        String candidate = null;
        String kerani = null; 
        
        AcCreditNote creditNote = billingService.findCreditNoteById(creditNoteId);
        candidate = "GRP_ADM";
        kerani = "GRP_KRN_ADM_BEND";

        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, kerani);
    }

    public List<String> findVerifierCandidates(Long creditNoteId) {
        Validate.notNull(creditNoteId, "Id must not be null");

        String candidate = null;
        String pegawai = null; 

        AcCreditNote creditNote = billingService.findCreditNoteById(creditNoteId);
        candidate = "GRP_ADM";
        pegawai = "GRP_PGW_ADM_BEND";

        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, pegawai);
    }

}
