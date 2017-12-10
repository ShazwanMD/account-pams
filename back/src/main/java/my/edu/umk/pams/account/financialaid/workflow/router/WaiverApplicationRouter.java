package my.edu.umk.pams.account.financialaid.workflow.router;
import my.edu.umk.pams.account.common.router.RouterServiceSupport;
import my.edu.umk.pams.account.common.router.RouterStrategy;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author PAMS
 */
@Component("waiverApplicationRouter")
public class WaiverApplicationRouter extends RouterServiceSupport{

    private static final Logger LOG = LoggerFactory.getLogger(WaiverApplicationRouter.class);

    private static Map<Integer, RouterStrategy> strategies = new HashMap<Integer, RouterStrategy>();

    static {
        strategies.put(1, new MGSEBAccountRouterStrategy());
        strategies.put(0, new CPSAccountRouterStrategy());
    }
       
    @Autowired
    private FinancialAidService financialAidService;

    public List<String> findRegistererCandidates(Long waiverApplicationId) {
        Validate.notNull(waiverApplicationId, "Id must not be null");
       
        AcWaiverApplication application = financialAidService.findWaiverApplicationById(waiverApplicationId);
        
        RouterStrategy strategy = strategies.get(application.getGraduateCenterType().ordinal());
        List<String> candidates = strategy.findRegistererCandidates();
        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return candidates;
    }


    public List<String> findVerifierCandidates(Long waiverApplicationId) {
        Validate.notNull(waiverApplicationId, "Id must not be null");

        
        AcWaiverApplication application = financialAidService.findWaiverApplicationById(waiverApplicationId);

        
        RouterStrategy strategy = strategies.get(application.getGraduateCenterType().ordinal());
        List<String> candidates = strategy.findVerifierCandidates();
        
        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return candidates;
    }
}
