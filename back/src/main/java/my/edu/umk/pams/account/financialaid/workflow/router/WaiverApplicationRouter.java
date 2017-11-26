package my.edu.umk.pams.account.financialaid.workflow.router;

import my.edu.umk.pams.account.common.router.RouterServiceSupport;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
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
@Component("waiverApplicationRouter")
public class WaiverApplicationRouter extends RouterServiceSupport{

    private static final Logger LOG = LoggerFactory.getLogger(WaiverApplicationRouter.class);

    @Autowired
    private FinancialAidService financialAidService;

    public List<String> findRegistererCandidates(Long waiverApplicationId) {
        Validate.notNull(waiverApplicationId, "Id must not be null");

        String candidate = null;
        String penPgwCps = null; 
        String penPgwMgseb = null; 
        
        
        AcWaiverApplication application = financialAidService.findWaiverApplicationById(waiverApplicationId);
        candidate = "GRP_ADM";
        penPgwCps = "GRP_PEN_PGW_PTJ_CPS";
        penPgwMgseb = "GRP_PEN_PGW_PTJ_MGSEB";
        

        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, penPgwCps, penPgwMgseb);
    }

    public List<String> findVerifierCandidates(Long waiverApplicationId) {
        Validate.notNull(waiverApplicationId, "Id must not be null");

        String candidate = null;
        String PgwCps = null; 
        String PgwMgseb = null; 
        
        AcWaiverApplication application = financialAidService.findWaiverApplicationById(waiverApplicationId);
        candidate = "GRP_ADM";
        PgwCps = "GRP_PGW_PTJ_CPS";
        PgwMgseb = "GRP_PGW_PTJ_MGSEB";

        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, PgwCps, PgwMgseb);
    }
}
