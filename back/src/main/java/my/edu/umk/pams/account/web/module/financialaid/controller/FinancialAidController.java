package my.edu.umk.pams.account.web.module.financialaid.controller;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItemImpl;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.web.module.billing.controller.BillingController;
import my.edu.umk.pams.account.web.module.billing.controller.BillingTransformer;
import my.edu.umk.pams.account.web.module.financialaid.vo.Settlement;
import my.edu.umk.pams.account.web.module.financialaid.vo.SettlementItem;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.util.List;

/**
 * @author PAMS
 */
@RestController
@RequestMapping("/api/financialaid")
public class FinancialAidController {

    private static final Logger LOG = LoggerFactory.getLogger(BillingController.class);

    @Autowired
    private BillingService billingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private FinancialAidService financialAidService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private BillingTransformer billingTransformer;

    @Autowired
    private FinancialAidTransformer financialAidTransformer;

    @Autowired
    private AuthenticationManager authenticationManager;

    // ==================================================================================================== //
    //  SETTLEMENT
    // ==================================================================================================== //

    @RequestMapping(value = "/settlements/", method = RequestMethod.GET)
    public ResponseEntity<List<Settlement>> findSettlements() {
        List<AcSettlement> settlements = financialAidService.findSettlementes( 0, 100);
        return new ResponseEntity<List<Settlement>>(financialAidTransformer.toSettlementVos(settlements), HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<Settlement> findSettlementByReferenceNo(@PathVariable String referenceNo) {
        AcSettlement settlement = (AcSettlement) financialAidService.findSettlementByReferenceNo(referenceNo);
        return new ResponseEntity<Settlement>(financialAidTransformer.toSettlementVo(settlement), HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<Settlement> updateSettlement(@PathVariable String referenceNo, @RequestBody Settlement vo) {
        AcSettlement settlement = (AcSettlement) financialAidService.findSettlementByReferenceNo(referenceNo);
        return new ResponseEntity<Settlement>(financialAidTransformer.toSettlementVo(settlement), HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo:.+}/settlementItems", method = RequestMethod.GET)
    public ResponseEntity<List<SettlementItem>> findSettlementItems(@PathVariable String referenceNo) {
        dummyLogin();
        String decode = URLDecoder.decode(referenceNo);
        LOG.debug("decoded: {}", decode);
        AcSettlement settlement = financialAidService.findSettlementByReferenceNo(decode);
        return new ResponseEntity<List<SettlementItem>>(financialAidTransformer
                .toSettlementItemVos(financialAidService.findSettlementItems(settlement)), HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo}/settlementItems", method = RequestMethod.POST)
    public void updateSettlementItems(@PathVariable String referenceNo, @RequestBody SettlementItem item) {
        dummyLogin();
        AcSettlement settlement = financialAidService.findSettlementByReferenceNo(referenceNo);
        if (null == item.getId()) { // new
            AcSettlementItem e = new AcSettlementItemImpl();
            e.setAccount(accountService.findAccountById(item.getAccount().getId()));
            e.setBalanceAmount(item.getBalanceAmount());
            financialAidService.addSettlementItem(settlement, e);
        } else { // update
            AcSettlementItem e = financialAidService.findSettlementItemById(item.getId());
            e.setAccount(accountService.findAccountById(item.getAccount().getId()));
            e.setBalanceAmount(item.getBalanceAmount());
            financialAidService.updateSettlementItem(settlement, e);
        }
    }

    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================

    private void dummyLogin() {
        AcAutoLoginToken token = new AcAutoLoginToken("root");
        Authentication authed = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authed);
    }
}
