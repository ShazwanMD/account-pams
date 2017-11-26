package my.edu.umk.pams.account.billing.workflow.router;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.billing.model.AcWaiverFinanceApplication;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.router.RouterServiceSupport;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.financialaid.workflow.router.WaiverApplicationRouter;

@Component("waiverFinanceApplicationRouter")
public class WaiverFinanceApplicationRouter extends RouterServiceSupport {

	private static final Logger LOG = LoggerFactory.getLogger(WaiverFinanceApplicationRouter.class);
    
    @Autowired
    private BillingService billingService;

    public List<String> findRegistererCandidates(Long waiverFinanceApplicationId) {
        Validate.notNull(waiverFinanceApplicationId, "Id must not be null");

        String candidate = null;
        String kerani = null;
        String penPgw = null;
        
        AcWaiverFinanceApplication application = billingService.findWaiverFinanceApplicationById(waiverFinanceApplicationId);
        candidate = "GRP_ADM";
        kerani = "GRP_KRN_ADM_BEND";
        penPgw = "GRP_PEN_PGW_ADM_BEND";
        

        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, kerani, penPgw);
    }

    public List<String> findVerifierCandidates(Long waiverFinanceApplicationId) {
        Validate.notNull(waiverFinanceApplicationId, "Id must not be null");

        String candidate = null;
        String pegawai = null;
        
        AcWaiverFinanceApplication application = billingService.findWaiverFinanceApplicationById(waiverFinanceApplicationId);
        candidate = "GRP_ADM";
        pegawai = "GRP_PGW_ADM_BEND"; 

        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate, pegawai);
    }
}
