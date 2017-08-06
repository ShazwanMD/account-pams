package my.edu.umk.pams.account.web.module.financialaid.controller;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.model.AcDebitNote;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.core.AcFlowState;
import my.edu.umk.pams.account.financialaid.model.*;
import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.web.module.billing.vo.DebitNote;
import my.edu.umk.pams.account.web.module.billing.vo.Invoice;
import my.edu.umk.pams.account.web.module.financialaid.vo.Settlement;
import my.edu.umk.pams.account.web.module.financialaid.vo.SettlementItem;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplication;
import my.edu.umk.pams.account.web.module.financialaid.vo.WaiverApplicationTask;
import my.edu.umk.pams.account.workflow.service.WorkflowService;
import org.activiti.engine.task.Task;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
@Transactional
@RestController
@RequestMapping("/api/financialaid")
public class FinancialAidController {

    private static final Logger LOG = LoggerFactory.getLogger(FinancialAidController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private BillingService billingService;

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
        
        AcSettlement settlement = financialAidService.findSettlementByReferenceNo(referenceNo);
        return new ResponseEntity<List<SettlementItem>>(financialAidTransformer
                .toSettlementItemVos(financialAidService.findSettlementItems(settlement)), HttpStatus.OK);
    }

    @Deprecated
    @RequestMapping(value = "/settlements/init", method = RequestMethod.POST)
    public ResponseEntity<String> initSettlements(@RequestBody Settlement vo) {
        

        AcAcademicSession acAcademicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
        AcSettlement acSettlement = new AcSettlementImpl();

        acSettlement.setReferenceNo(vo.getReferenceNo());
        acSettlement.setDescription(vo.getDescription());
        acSettlement.setSession(acAcademicSession);
        acSettlement.setIssuedDate(vo.getIssuedDate());
        String referenceNo = financialAidService.initSettlement(acSettlement);
        return new ResponseEntity<String>(referenceNo, HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/initBySponsor/{sponsorNo}", method = RequestMethod.POST)
    public ResponseEntity<String> initSettlementBySponsor(@PathVariable String sponsorNo, @RequestBody Settlement vo) {
        

        AcAcademicSession acAcademicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
        AcSponsor sponsor = identityService.findSponsorBySponsorNo(sponsorNo);
        AcSettlement settlement = new AcSettlementImpl();
        settlement.setDescription(vo.getDescription());
        settlement.setSession(acAcademicSession);
        settlement.setIssuedDate(vo.getIssuedDate());
        String referenceNo = financialAidService.initSettlementBySponsor(settlement, sponsor);
        return new ResponseEntity<String>(referenceNo, HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/initByCohortCode/{code}", method = RequestMethod.POST)
    public ResponseEntity<String> initSettlementByCohortCode(@PathVariable String code, @RequestBody Settlement vo) {
        

        AcAcademicSession acAcademicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
        AcCohortCode cohortCode = commonService.findCohortCodeByCode(code);
        AcSettlement settlement = new AcSettlementImpl();
        settlement.setDescription(vo.getDescription());
        settlement.setSession(acAcademicSession);
        settlement.setIssuedDate(vo.getIssuedDate());
        String referenceNo = financialAidService.initSettlementByCohortCode(settlement, cohortCode);
        return new ResponseEntity<String>(referenceNo, HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/initByFacultyCode/{code}", method = RequestMethod.POST)
    public ResponseEntity<String> initSettlementByFacultyCode(@PathVariable String code, @RequestBody Settlement vo) {
        

        AcAcademicSession acAcademicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
        AcFacultyCode facultyCode = commonService.findFacultyCodeByCode(code);
        AcSettlement settlement = new AcSettlementImpl();
        settlement.setDescription(vo.getDescription());
        settlement.setSession(acAcademicSession);
        settlement.setIssuedDate(vo.getIssuedDate());
        String referenceNo = financialAidService.initSettlementByFacultyCode(settlement, facultyCode);
        return new ResponseEntity<String>(referenceNo, HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo}/execute", method = RequestMethod.POST)
    public void executeSettlement(@PathVariable String referenceNo) {
        
        LOG.debug("referenceNo {}", referenceNo);
        AcSettlement settlement = financialAidService.findSettlementByReferenceNo(referenceNo);
        financialAidService.executeSettlement(settlement);
    }
    
    @RequestMapping(value = "/settlements/{referenceNo}/uploadSettlement", method = RequestMethod.POST)
	public ResponseEntity<String> uploadSettlement(@PathVariable String referenceNo,
			@RequestParam("file") MultipartFile file) {
		LOG.debug("BackEnd:{}", file.getName());

		try {
			Workbook workbook = WorkbookFactory.create(file.getInputStream());
			//Sheet sheet = workbook.getSheetAt(0); // first sheet
			//AdOffering offering = termService.findOfferingByCanonicalCode(canonicalCode);
			//List<AdEnrollment> enrollments = toEnrollments(sheet, offering);
			//List<AdAssessment> assessments = toAssessments(sheet);
			//LOG.debug("Assessments:{}", assessments);
			//AdSection section = toSection(sheet);
			int i = 1; // skip row 0
			/*while (i <= sheet.getLastRowNum()) {
				Row row = sheet.getRow(i);
				int j = 1;
				while (j < row.getLastCellNum()) {
					Cell cell = row.getCell(j);
					AdEnrollment enrollment = enrollments.get(i - 1);
					AdAssessment assessment = assessments.get(j - 1);
					LOG.debug("Assessment:{}", assessment);
					AdGradebook gradebook = termService.findGradebookByAssessmentAndEnrollment(assessment, enrollment);
					boolean update = true;
					if (gradebook == null) {
						gradebook = new AdGradebookImpl();
						update = false;
					}
					;
					gradebook.setEnrollment(enrollment);
					gradebook.setSection(section);
					gradebook.setAssessment(assessment);
					;
					gradebook.setScore(new BigDecimal(cell.getNumericCellValue()));

					BigDecimal score = gradebook.getScore();
					String assessmentDescription = assessment.getDescription();
					String name = enrollment.getAdmission().getStudent().getName();
					String message = score + ", " + assessmentDescription + ", " + name + ".";
					if (update) {
						termService.updateGradebook(gradebook);
						LOG.debug("Gradebook UPDATED {}", message);
					} else {
						termService.saveGradebook(gradebook);
						LOG.debug("Gradebook SAVED: {}", message);
					}
					j++;
				}
				i++;
			}*/
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

    @RequestMapping(value = "/settlements/{referenceNo}/settlementItems", method = RequestMethod.POST)
    public ResponseEntity<String> addSettlementItem(@PathVariable String referenceNo, @RequestBody SettlementItem item) {
        
        AcSettlement settlement = financialAidService.findSettlementByReferenceNo(referenceNo);
        AcSettlementItem e = new AcSettlementItemImpl();

        e.setSettlement(settlement);
        e.setLoanAmount(item.getLoanAmount());
        e.setFeeAmount(item.getFeeAmount());
        e.setNettAmount(item.getNettAmount());
        e.setBalanceAmount(item.getBalanceAmount());
        //e.setStatus(AcSettlementStatus.NEW);

        if (null != item.getInvoice() && null != item.getInvoice().getId())
            e.setInvoice(billingService.findInvoiceById(item.getInvoice().getId()));

        if (null != item.getAccount() && null != item.getAccount().getId())
            e.setAccount(accountService.findAccountById(item.getAccount().getId()));

        financialAidService.addSettlementItem(settlement, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo}/settlementItems/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateSettlementItems(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody SettlementItem item) {
        
        AcSettlement settlement = financialAidService.findSettlementByReferenceNo(referenceNo);
        AcSettlementItem e = financialAidService.findSettlementItemById(item.getId());

        e.setSettlement(settlement);
        e.setBalanceAmount(item.getBalanceAmount());

        if (null != item.getInvoice() && null != item.getInvoice().getId())
            e.setInvoice(billingService.findInvoiceById(item.getInvoice().getId()));

        if (null != item.getAccount() && null != item.getAccount().getId())
            e.setAccount(accountService.findAccountById(item.getAccount().getId()));

        financialAidService.updateSettlementItem(settlement, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/settlements/{referenceNo}/settlementItems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteSettlementItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcSettlement settlement = financialAidService.findSettlementByReferenceNo(referenceNo);
        AcSettlementItem e = financialAidService.findSettlementItemById(id);
        financialAidService.deleteSettlementItem(settlement, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
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
        
        List<Task> tasks = financialAidService.findAssignedWaiverApplicationTasks(0, 100);
        return new ResponseEntity<List<WaiverApplicationTask>>(financialAidTransformer.toWaiverApplicationTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/pooledTasks", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverApplicationTask>> findPooledWaiverApplications() {
        
        List<Task> tasks = financialAidService.findPooledWaiverApplicationTasks(0, 100);
        return new ResponseEntity<List<WaiverApplicationTask>>(financialAidTransformer.toWaiverApplicationTaskVos(tasks), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/state/{state}", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverApplication>> findWaiverApplicationsByFlowState(@PathVariable String state) {
        List<AcWaiverApplication> waiverApplications = financialAidService.findWaiverApplicationsByFlowState(AcFlowState.valueOf(state));
        return new ResponseEntity<List<WaiverApplication>>(financialAidTransformer.toWaiverApplicationVos(waiverApplications), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/archived", method = RequestMethod.GET)
    public ResponseEntity<List<WaiverApplication>> findArchivedWaiverApplications() {
        List<AcWaiverApplication> waiverApplications = financialAidService
                .findWaiverApplicationsByFlowStates(AcFlowState.COMPLETED, AcFlowState.CANCELLED, AcFlowState.REMOVED);
        return new ResponseEntity<List<WaiverApplication>>(financialAidTransformer
                .toWaiverApplicationVos(waiverApplications), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/startTask", method = RequestMethod.POST)
    public ResponseEntity<String> startWaiverApplicationTask(@RequestBody WaiverApplication vo) throws Exception {
        

        AcAcademicSession academicSession = accountService.findAcademicSessionById(vo.getAcademicSession().getId());
        AcAccount account = accountService.findAccountById(vo.getAccount().getId());
        AcWaiverApplication waiverApplication = new AcWaiverApplicationImpl();
        waiverApplication.setDescription(vo.getDescription());
        waiverApplication.setWaivedAmount(vo.getWaivedAmount());
        waiverApplication.setGracedAmount(BigDecimal.ZERO);
        waiverApplication.setEffectiveBalance(accountService.sumEffectiveBalanceAmount(account, academicSession));
        waiverApplication.setBalance(accountService.sumBalanceAmount(account));
        waiverApplication.setAccount(account);
        waiverApplication.setSession(academicSession);
        return new ResponseEntity<String>(financialAidService.startWaiverApplicationTask(waiverApplication), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/viewTask/{taskId}", method = RequestMethod.GET)
    public ResponseEntity<WaiverApplicationTask> findWaiverApplicationTaskByTaskId(@PathVariable String taskId) {
        return new ResponseEntity<WaiverApplicationTask>(financialAidTransformer
                .toWaiverApplicationTaskVo(
                        financialAidService.findWaiverApplicationTaskByTaskId(taskId)), HttpStatus.OK);
    }

    @RequestMapping(value = "/waiverApplications/claimTask", method = RequestMethod.POST)
    public void claimWaiverApplicationTask(@RequestBody WaiverApplicationTask vo) {
        
        Task task = financialAidService.findWaiverApplicationTaskByTaskId(vo.getTaskId());
        workflowService.claimTask(task);
    }

    @RequestMapping(value = "/waiverApplications/completeTask", method = RequestMethod.POST)
    public void completeWaiverApplicationTask(@RequestBody WaiverApplicationTask vo) {
        
        Task task = financialAidService.findWaiverApplicationTaskByTaskId(vo.getTaskId());
        workflowService.completeTask(task);
    }

    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================
}
