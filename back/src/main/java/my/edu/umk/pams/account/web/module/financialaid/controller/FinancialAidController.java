package my.edu.umk.pams.account.web.module.financialaid.controller;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplication;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author PAMS
 */
@RestController
@RequestMapping("/api/financialaid")
public class FinancialAidController {

    private static final Logger LOG = LoggerFactory.getLogger(FinancialAidController.class);

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
    private FinancialAidTransformer financialAidTransformer;

    @Autowired
    private AuthenticationManager authenticationManager;

    @RequestMapping(value = "/waiverApplications/", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverApplication>> findWaiverApplications() {
        List<AcWaiverApplication> waiverApplications = financialAidService.findWaiverApplications("%", 0, 100);
        return new ResponseEntity<List<WaiverApplication>>(financialAidTransformer.toWaiverApplicationVos(waiverApplications), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<WaiverApplication> findWaiverApplicationByReferenceNo(@PathVariable String referenceNo) {
        AcWaiverApplication waiverApplication = (AcWaiverApplication) financialAidService.findWaiverApplicationByReferenceNo(referenceNo);
        return new ResponseEntity<WaiverApplication>(financialAidTransformer.toWaiverApplicationVo(waiverApplication), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<WaiverApplication> updateWaiverApplication(@PathVariable String referenceNo, @RequestBody WaiverApplication vo) {
        AcWaiverApplication waiverApplication = (AcWaiverApplication) financialAidService.findWaiverApplicationByReferenceNo(referenceNo);
        return new ResponseEntity<WaiverApplication>(financialAidTransformer.toWaiverApplicationVo(waiverApplication), HttpStatus.OK);
    }
}
