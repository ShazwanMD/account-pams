package my.edu.umk.pams.account.web.module.financialaid.controller;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.financialaid.model.*;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.web.module.financialaid.vo.Settlement;
import my.edu.umk.pams.account.web.module.financialaid.vo.SettlementItem;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplication;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplicationTask;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
@RestController
@RequestMapping("/api/financialaid")
public class FinancialAidController {

    private static final Logger LOG = LoggerFactory.getLogger(FinancialAidController.class);

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

    // ====================================================================================================
    // SETTLEMENT
    // ====================================================================================================
    @RequestMapping(value = "/settlements", method = RequestMethod.GET)
    public ResponseEntity<List<Settlement>> findSettlements() {
        List<AcSettlement> settlements = financialAidService.findSettlements(0, 100);
        return new ResponseEntity<List<Settlement>>(financialAidTransformer.toSettlementVos(settlements), HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<Settlement> findSettlementByReferenceNo(@PathVariable String referenceNo) {
        AcSettlement settlement = (AcSettlement) financialAidService.findSettlementByReferenceNo(referenceNo);
        return new ResponseEntity<Settlement>(financialAidTransformer.toSettlementVo(settlement), HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo}/settlementItems", method = RequestMethod.GET)
    public ResponseEntity<List<SettlementItem>> findSettlementItems(@PathVariable String referenceNo) {
        dummyLogin();
        AcSettlement settlement = financialAidService.findSettlementByReferenceNo(referenceNo);
        return new ResponseEntity<List<SettlementItem>>(financialAidTransformer
                .toSettlementItemVos(financialAidService.findSettlementItems(settlement)), HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo}/settlementItems", method = RequestMethod.POST)
    public void updateSettlementItems(@PathVariable String referenceNo, @RequestBody SettlementItem item) {
        dummyLogin();
        AcSettlement settlement = financialAidService.findSettlementByReferenceNo(referenceNo);
        if (null == item.getId()) { // new
            AcSettlementItem e = new AcSettlementItemImpl();
            e.setBalanceAmount(item.getBalanceAmount());
            // todo: e.setAccount();
            // todo: e.setStatus();
            financialAidService.addSettlementItem(settlement, e);
        } else { // update
            AcSettlementItem e = financialAidService.findSettlementItemById(item.getId());
            e.setBalanceAmount(item.getBalanceAmount());
            // todo: e.setAccount();
            // todo: e.setStatus();
            financialAidService.updateSettlementItem(settlement, e);
        }
    }

    @Deprecated
    @RequestMapping(value = "/settlements/init", method = RequestMethod.POST)
    public ResponseEntity<String> initSettlements(@RequestBody Settlement vo) {
    	dummyLogin();
    	
    	AcAcademicSession acAcademicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
    	AcSettlement acSettlement = new AcSettlementImpl();
    	
    	acSettlement.setReferenceNo(vo.getReferenceNo());
    	acSettlement.setDescription(vo.getDescription());
    	acSettlement.setSession(acAcademicSession);

    	String referenceNo = financialAidService.initSettlement(acSettlement);
        return new ResponseEntity<String>(referenceNo, HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/initBySponsor/{sponsorNo}", method = RequestMethod.POST)
    public ResponseEntity<String> initSettlementBySponsor(@PathVariable String sponsorNo,  @RequestBody Settlement vo) {
    	dummyLogin();

    	AcAcademicSession acAcademicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
    	AcSponsor sponsor = identityService.findSponsorBySponsorNo(sponsorNo);
    	AcSettlement settlement = new AcSettlementImpl();
    	settlement.setDescription(vo.getDescription());
    	settlement.setSession(acAcademicSession);

        String referenceNo = financialAidService.initSettlementBySponsor(settlement, sponsor);
        return new ResponseEntity<String>(referenceNo, HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/initByCohortCode/{code}", method = RequestMethod.POST)
    public ResponseEntity<String> initSettlementByCohortCode(@PathVariable String code,  @RequestBody Settlement vo) {
    	dummyLogin();

    	AcAcademicSession acAcademicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
    	AcCohortCode cohortCode = commonService.findCohortCodeByCode(code);
    	AcSettlement settlement = new AcSettlementImpl();
    	settlement.setDescription(vo.getDescription());
    	settlement.setSession(acAcademicSession);
        String referenceNo = financialAidService.initSettlementByCohortCode(settlement, cohortCode);
        return new ResponseEntity<String>(referenceNo, HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/initByFacultyCode/{code}", method = RequestMethod.POST)
    public ResponseEntity<String> initSettlementByFacultyCode(@PathVariable String code,  @RequestBody Settlement vo) {
    	dummyLogin();

    	AcAcademicSession acAcademicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
    	AcFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
    	AcSettlement settlement = new AcSettlementImpl();
    	settlement.setDescription(vo.getDescription());
    	settlement.setSession(acAcademicSession);
        String referenceNo = financialAidService.initSettlementByFacultyCode(settlement, facultyCode);
        return new ResponseEntity<String>(referenceNo, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/settlements/{referenceNo}/execute", method = RequestMethod.POST)
    public void executeSettlement(@PathVariable String referenceNo) {
    	dummyLogin();
    	LOG.debug("referenceNo {}",referenceNo);
    	AcSettlement settlement = financialAidService.findSettlementByReferenceNo(referenceNo);
    	financialAidService.executeSettlement(settlement);
    }

    // ====================================================================================================
    // WAIVER APPLICATION
    // ====================================================================================================

    @RequestMapping(value = "/waiverApplications", method = RequestMethod.GET)
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

    @RequestMapping(value = "/waiverApplications/assignedTasks", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverApplicationTask>> findAssignedWaiverApplications() {
        dummyLogin();
        List<Task> tasks = financialAidService.findAssignedWaiverApplicationTasks(0, 100);
        return new ResponseEntity<List<WaiverApplicationTask>>(financialAidTransformer.toWaiverApplicationTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverApplicationTask>> findPooledWaiverApplications() {
        dummyLogin();
        List<Task> tasks = financialAidService.findPooledWaiverApplicationTasks(0, 100);
        return new ResponseEntity<List<WaiverApplicationTask>>(financialAidTransformer.toWaiverApplicationTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/startTask", method = RequestMethod.POST)
    public void startWaiverApplicationTask(@RequestBody WaiverApplication vo) throws Exception {
        dummyLogin();

        AcAccount account = accountService.findAccountById(vo.getAccount().getId());
        AcWaiverApplication waiverApplication = new AcWaiverApplicationImpl();
        waiverApplication.setDescription(vo.getDescription());
        waiverApplication.setWaivedAmount(BigDecimal.ZERO);
        waiverApplication.setGracedAmount(BigDecimal.ZERO);
        waiverApplication.setEffectiveBalance(BigDecimal.ZERO);
        waiverApplication.setBalance(BigDecimal.ZERO);
        waiverApplication.setAccount(account);
        financialAidService.startWaiverApplicationTask(waiverApplication);
    }

    @RequestMapping(value = "/waiverApplications/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<WaiverApplicationTask> findWaiverApplicationTaskByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<WaiverApplicationTask>(financialAidTransformer
                .toWaiverApplicationTaskVo(
                        financialAidService.findWaiverApplicationTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/claimTask", method = RequestMethod.POST)
    public void claimWaiverApplicationTask(@RequestBody WaiverApplicationTask vo) {
        dummyLogin();
        Task task = financialAidService.findWaiverApplicationTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
    }

    @RequestMapping(value = "/waiverApplications/completeTask", method = RequestMethod.POST)
    public void completeWaiverApplicationTask(@RequestBody WaiverApplicationTask vo) {
        dummyLogin();
        Task task = financialAidService.findWaiverApplicationTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
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
