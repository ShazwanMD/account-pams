package my.edu.umk.pams.account.financialaid.workflow.router;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import my.edu.umk.pams.account.common.router.RouterServiceSupport;
import my.edu.umk.pams.account.financialaid.model.AcShortTermLoan;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;

@Component("shortTermLoanRouter")
public class ShortTermLoanRouter extends RouterServiceSupport{

    private static final Logger LOG = LoggerFactory.getLogger(ShortTermLoanRouter.class);

    @Autowired
    private FinancialAidService financialAidService;

    public List<String> findRegistererCandidates(Long shortTermLoanId) {
        Validate.notNull(shortTermLoanId, "Id must not be null");

        String candidate = null;
        AcShortTermLoan shortTermLoan = financialAidService.findShortTermLoanById(shortTermLoanId);
        candidate = "GRP_ADM";

        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate);
    }

    public List<String> findVerifierCandidates(Long shortTermLoanId) {
        Validate.notNull(shortTermLoanId, "Id must not be null");

        String candidate = null;
        AcShortTermLoan shortTermLoan = financialAidService.findShortTermLoanById(shortTermLoanId);
        candidate = "GRP_ADM";

        // publish access event
        // publishAccessEvent(creditNote, identityService.findGroupByName(candidate), AcPermission.VIEW);

        return Arrays.asList(candidate);
    }
}
