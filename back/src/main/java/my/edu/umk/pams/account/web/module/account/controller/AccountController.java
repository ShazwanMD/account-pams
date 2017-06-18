package my.edu.umk.pams.account.web.module.account.controller;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.identity.model.AcActorType;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.web.module.account.vo.*;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
@RestController
@RequestMapping("/api/account")
public class AccountController {

	private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

	@Autowired
	private AccountService accountService;

	@Autowired
	private BillingService billingService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private AccountTransformer accountTransformer;

	@Autowired
	private AuthenticationManager authenticationManager;

	// ====================================================================================================
	// ADMISSION SESSION
	// ====================================================================================================
	@RequestMapping(value = "/academicSessions", method = RequestMethod.GET)
	public ResponseEntity<List<AcademicSession>> findAcademicSessions() {
		List<AcAcademicSession> academicSessions = accountService.findAcademicSessions("%", 0, 100);
		return new ResponseEntity<List<AcademicSession>>(accountTransformer.toAcademicSessionVos(academicSessions),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/academicSessions/{code}", method = RequestMethod.GET)
	public ResponseEntity<AcademicSession> findAcademicSessionBySession(@PathVariable String code) {
		AcAcademicSession academicSession = accountService.findAcademicSessionByCode(code);
		return new ResponseEntity<AcademicSession>(accountTransformer.toAcademicSessionVo(academicSession),
				HttpStatus.OK);
	}

	// ====================================================================================================
	// FEE SCHEDULE
	// ====================================================================================================

	@RequestMapping(value = "/feeSchedules", method = RequestMethod.GET)
	public ResponseEntity<List<FeeSchedule>> findFeeSchedules() {
		List<AcFeeSchedule> feeSchedules = accountService.findFeeSchedules("%", 0, 100);
		return new ResponseEntity<List<FeeSchedule>>(accountTransformer.toFeeScheduleVos(feeSchedules), HttpStatus.OK);
	}

	@RequestMapping(value = "/feeSchedules/{code}", method = RequestMethod.GET)
	public ResponseEntity<FeeSchedule> findScheduleScheduleBySchedule(@PathVariable String code) {
		AcFeeSchedule feeSchedule = accountService.findFeeScheduleByCode(code);
		return new ResponseEntity<FeeSchedule>(accountTransformer.toFeeScheduleVo(feeSchedule), HttpStatus.OK);
	}

	@RequestMapping(value = "/feeSchedules/{code}/feeScheduleItems", method = RequestMethod.GET)
	public ResponseEntity<List<FeeScheduleItem>> findFeeScheduleItems(@PathVariable String code) {
		AcFeeSchedule feeSchedule = accountService.findFeeScheduleByCode(code);
		return new ResponseEntity<List<FeeScheduleItem>>(
				accountTransformer.toFeeScheduleItemVos(accountService.findFeeScheduleItems(feeSchedule)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/feeSchedules", method = RequestMethod.POST)
	public ResponseEntity<String> saveFeeSchedule(@RequestBody FeeSchedule vo) {
		dummyLogin();
		AcFeeSchedule feeSchedule = new AcFeeScheduleImpl();
		feeSchedule.setCode(vo.getCode());
		feeSchedule.setDescription(vo.getDescription());
		feeSchedule.setTotalAmount(BigDecimal.ZERO); // todo
		feeSchedule.setResidencyCode(commonService.findResidencyCodeById(vo.getResidencyCode().getId()));
		feeSchedule.setCohortCode(commonService.findCohortCodeById(vo.getCohortCode().getId()));
		feeSchedule.setStudyMode(commonService.findStudyModeById(vo.getStudyMode().getId()));
		accountService.saveFeeSchedule(feeSchedule);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/feeSchedules/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateFeeSchedule(@PathVariable String code, @RequestBody FeeSchedule vo) {
		dummyLogin();
		AcFeeSchedule feeSchedule = accountService.findFeeScheduleByCode(code);
		feeSchedule.setCode(vo.getCode());
		feeSchedule.setDescription(vo.getDescription());
		feeSchedule.setTotalAmount(BigDecimal.ZERO); // todo
		accountService.updateFeeSchedule(feeSchedule);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/feeSchedules/{code}/feeScheduleItems", method = RequestMethod.POST)
	public ResponseEntity<String> addFeeScheduleItem(@PathVariable String code, @RequestBody FeeScheduleItem item) {
		dummyLogin();
		AcFeeSchedule feeSchedule = accountService.findFeeScheduleByCode(code);
		AcFeeScheduleItem e = new AcFeeScheduleItemImpl();
		e.setDescription(item.getDescription());
		e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
		e.setOrdinal(item.getOrdinal());
		e.setAmount(item.getAmount());
		accountService.addFeeScheduleItem(feeSchedule, e);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/feeSchedules/{code}/feeScheduleItems", method = RequestMethod.PUT)
	public ResponseEntity<String> updateFeeScheduleItem(@PathVariable String code, @RequestBody FeeScheduleItem item) {
		dummyLogin();
		AcFeeSchedule feeSchedule = accountService.findFeeScheduleByCode(code);
		AcFeeScheduleItem e = accountService.findFeeScheduleItemById(item.getId());
		e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
		e.setOrdinal(item.getOrdinal());
		e.setAmount(item.getAmount());
		accountService.updateFeeScheduleItem(feeSchedule, e);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/feeSchedules/{code}/feeScheduleItems/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteFeeScheduleItem(@PathVariable String code, @PathVariable Long id) {
		dummyLogin();
		AcFeeSchedule feeSchedule = accountService.findFeeScheduleByCode(code);
		AcFeeScheduleItem e = accountService.findFeeScheduleItemById(id);
		accountService.deleteFeeScheduleItem(feeSchedule, e);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}


	@RequestMapping(value = "/feeSchedules/{code}/upload", method = RequestMethod.POST)
	public ResponseEntity<String> uploadFeeSchedule(@PathVariable String code, @RequestParam("file") MultipartFile file) {
		dummyLogin();
		LOG.debug("BackEnd:{}", file.getName());
		// todo(faizal): parse excel
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/feeSchedules/{code}/download", method = RequestMethod.GET)
	public ResponseEntity downloadGradebook(@PathVariable String code) {
		dummyLogin();
		ByteArrayResource resource = null;
		return ResponseEntity.ok()
				.header("Content-Disposition", "attachment; filename=" + code + ".xlsx")
				.body(resource);
	}

	// ====================================================================================================
	// CHARGE CODE
	// ====================================================================================================
	@RequestMapping(value = "/chargeCodes", method = RequestMethod.GET)
	public ResponseEntity<List<ChargeCode>> findChargeCodes() {
		List<AcChargeCode> chargeCodes = accountService.findChargeCodes("%", 0, 100);
		return new ResponseEntity<List<ChargeCode>>(accountTransformer.toChargeCodeVos(chargeCodes), HttpStatus.OK);
	}

	@RequestMapping(value = "/chargeCodes/{code}", method = RequestMethod.GET)
	public ResponseEntity<ChargeCode> findChargeCodeByCode(@PathVariable String code) {
		AcChargeCode chargeCode = accountService.findChargeCodeByCode(code);
		return new ResponseEntity<ChargeCode>(accountTransformer.toChargeCodeVo(chargeCode), HttpStatus.OK);
	}

	@RequestMapping(value = "/chargeCodes", method = RequestMethod.POST)
	public ResponseEntity<String> saveChargeCode(@RequestBody ChargeCode vo) {
		dummyLogin();

		AcChargeCode chargeCode = new AcChargeCodeImpl();
		chargeCode.setCode(vo.getCode());
		chargeCode.setDescription(vo.getDescription());
		chargeCode.setPriority(vo.getPriority());
		accountService.saveChargeCode(chargeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/chargeCodes/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateChargeCode(@PathVariable String code, @RequestBody ChargeCode vo) {
		dummyLogin();
		// what can we update?
		AcChargeCode chargeCode = accountService.findChargeCodeById(vo.getId());
		chargeCode.setCode(vo.getCode());
		chargeCode.setDescription(vo.getDescription());
		chargeCode.setPriority(vo.getPriority());
		accountService.updateChargeCode(chargeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/chargeCodes/{code}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeChargeCode(@PathVariable String code) {
		dummyLogin();

		AcChargeCode chargeCode = accountService.findChargeCodeByCode(code);
		accountService.removeChargeCode(chargeCode);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	// ====================================================================================================
	// ACCOUNT
	// ====================================================================================================

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> findAccounts() {
		List<AcAccount> accounts = accountService.findAccounts(0, 100);
		List<Account> vos = accountTransformer.toAccountVos(decorateAccounts(accounts));
		return new ResponseEntity<List<Account>>(vos, HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts?/byFilter/{filter}", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> findAccounts(@PathVariable String filter) {
		List<AcAccount> accounts = accountService.findAccounts(filter, 0, Integer.MAX_VALUE);
		return new ResponseEntity<List<Account>>(accountTransformer.toAccountVos(accounts), HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{code}", method = RequestMethod.GET)
	public ResponseEntity<Account> findAccountByCode(@PathVariable String code) {
		AcAccount account = decorateAccount(accountService.findAccountByCode(code));
		return new ResponseEntity<Account>(accountTransformer.toAccountVo(account), HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/byActor/student", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> findAccountsByActor() {
		List<AcAccount> accounts = accountService.findAccounts("%", AcActorType.STUDENT, 0, 100);
		List<Account> vos = accountTransformer.toAccountVos(decorateAccounts(accounts));
		return new ResponseEntity<List<Account>>(vos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts/byActor/sponsor", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> findAccountsByActorSponsor() {
		List<AcAccount> accounts = accountService.findAccounts("%", AcActorType.SPONSOR, 0, 100);
		List<Account> vos = accountTransformer.toAccountVos(decorateAccounts(accounts));
		return new ResponseEntity<List<Account>>(vos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts/byActor/staff", method = RequestMethod.GET)
	public ResponseEntity<List<Account>> findAccountsByActorStaff() {
		List<AcAccount> accounts = accountService.findAccounts("%", AcActorType.STAFF, 0, 100);
		List<Account> vos = accountTransformer.toAccountVos(decorateAccounts(accounts));
		return new ResponseEntity<List<Account>>(vos, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public ResponseEntity<String> saveAccount(@RequestBody Account vo) {
		dummyLogin();
		AcAccount account = new AcAccountImpl();
		account.setCode(vo.getCode());
		account.setDescription(vo.getActor().getName());
		account.setActor(identityService.findActorById(vo.getActor().getId())); // todo
		accountService.saveAccount(account);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{code}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateAccount(@PathVariable String code, @RequestBody Account vo) {
		dummyLogin();
		// what can we update?
		AcAccount account = accountService.findAccountById(vo.getId());
		account.setCode(vo.getCode());
		account.setDescription(vo.getActor().getName());
		accountService.updateAccount(account);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{code}/accountTransactions", method = RequestMethod.GET)
	public ResponseEntity<List<AccountTransaction>> findAccountTransactions(@PathVariable String code) {
		AcAccount account = accountService.findAccountByCode(code);
		return new ResponseEntity<List<AccountTransaction>>(
				accountTransformer.toAccountTransactionVos(accountService.findAccountTransactions(account)),
				HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{code}/accountCharges", method = RequestMethod.GET)
	public ResponseEntity<List<AccountCharge>> findAccountCharges(@PathVariable String code) {
		AcAccount account = accountService.findAccountByCode(code);
		return new ResponseEntity<List<AccountCharge>>(
				accountTransformer.toAccountChargeVos(accountService.findAccountCharges(account)), HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{code}/enrollmentCharge", method = RequestMethod.POST)
	public ResponseEntity<String> addEnrollmentCharge(@PathVariable String code, @RequestBody EnrollmentCharge vo) {
		AcAccount account = accountService.findAccountByCode(code);
		AcEnrollmentCharge enrollmentCharge = new AcEnrollmentChargeImpl();
		enrollmentCharge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		enrollmentCharge.setSourceNo(vo.getSourceNo());
		enrollmentCharge.setDescription(vo.getDescription());
		enrollmentCharge.setAmount(vo.getAmount());
		enrollmentCharge.setSession(accountService.findCurrentAcademicSession()); // todo:
		accountService.addAccountCharge(account, enrollmentCharge);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{code}/accountWaivers", method = RequestMethod.GET)
	public ResponseEntity<List<AccountWaiver>> findAccountWaivers(@PathVariable String code) {
		AcAccount account = accountService.findAccountByCode(code);
		return new ResponseEntity<List<AccountWaiver>>(
				accountTransformer.toAccountWaiverVos(accountService.findAccountWaivers(account)), HttpStatus.OK);
	}

	@RequestMapping(value = "/account/{code}/accountCharges", method = RequestMethod.POST)
	public ResponseEntity<String> addAccountCharge(@PathVariable String code, @RequestBody AccountCharge vo) {
		dummyLogin();
		AcAccount account = accountService.findAccountByCode(code);
		AcAccountCharge charge = new AcAccountChargeImpl();
		charge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		charge.setSourceNo(vo.getSourceNo());
		charge.setDescription(vo.getDescription());
		charge.setAmount(vo.getAmount());
		charge.setCohortCode(commonService.findCohortCodeById(vo.getCohortCode().getId()));
		charge.setStudyMode(commonService.findStudyModeById(vo.getStudyMode().getId()));
		charge.setSession(accountService.findCurrentAcademicSession()); // todo:
		charge.setChargeType(AcAccountChargeType.get(vo.getChargeType().ordinal()));
		charge.setDoc(vo.getDoc());
		charge.setOrdinal(vo.getOrdinal());
		accountService.addAccountCharge(account, charge);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/account/{code}/accountTransactions", method = RequestMethod.POST)
	public void addAccountTransaction(@PathVariable String code, @RequestBody AccountTransaction vo) {
		dummyLogin();
		AcAccount account = accountService.findAccountByCode(code);
		AcAccountTransaction transaction = new AcAccountTransactionImpl();
		transaction.setChargeCode(accountService.findChargeCodeById(vo.getChargeCode().getId()));
		transaction.setAmount(vo.getAmount());
		transaction.setPostedDate(vo.getPostedDate());
		transaction.setSession(accountService.findAcademicSessionById(vo.getSession().getId()));
		transaction.setTransactionCode(AcAccountTransactionCode.ADHOC);
		accountService.addAccountTransaction(account, transaction);
	}

	// ====================================================================================================
	// ADMISSION CHARGE
	// ====================================================================================================

	@RequestMapping(value = "/accounts/{code}/admissionCharges", method = RequestMethod.POST)
	public ResponseEntity<String> addAdmissionCharge(@PathVariable String code, @RequestBody AdmissionCharge vo) {
		dummyLogin();

		AcAccount account = accountService.findAccountByCode(code);
		AcAdmissionCharge admissionCharge = new AcAdmissionChargeImpl();
		admissionCharge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		admissionCharge.setSourceNo(vo.getSourceNo());
		admissionCharge.setDescription(vo.getDescription());
		admissionCharge.setAmount(vo.getAmount());
		admissionCharge.setCohortCode(commonService.findCohortCodeById(vo.getCohortCode().getId()));
		admissionCharge.setStudyMode(commonService.findStudyModeById(vo.getStudyMode().getId()));
		admissionCharge.setSession(accountService.findCurrentAcademicSession()); // todo:
		accountService.addAccountCharge(account, admissionCharge);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{code}/admissionCharges/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateAdmissionCharge(@PathVariable String code, @RequestBody AdmissionCharge vo) {
		dummyLogin();
		// what can we update
		AcAccount account = accountService.findAccountByCode(code);
		AcAccountCharge admissionCharge = accountService.findAccountChargeById(vo.getId());
		admissionCharge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		admissionCharge.setSourceNo(vo.getSourceNo());
		admissionCharge.setDescription(vo.getDescription());
		admissionCharge.setAmount(vo.getAmount());
		((AcAdmissionCharge) admissionCharge).setCohortCode(commonService.findCohortCodeById(vo.getCohortCode().getId()));
		((AcAdmissionCharge) admissionCharge).setStudyMode(commonService.findStudyModeById(vo.getStudyMode().getId()));
		admissionCharge.setSession(accountService.findCurrentAcademicSession());
		accountService.updateAccountCharge(account, admissionCharge);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	
	@RequestMapping(value = "/accounts/{code}/admissionCharges/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeAdmissionCharge(@PathVariable String code, @PathVariable Long id) {
		dummyLogin();

		AcAccount account = accountService.findAccountByCode(code);
		AcAccountCharge admissionCharge = accountService.findAccountChargeById(id);
		accountService.deleteAccountCharge(account, admissionCharge);
		LOG.debug("admissionCharge " + id + " is deleted");
		return new ResponseEntity<>("Removed", HttpStatus.OK);
	}
	

	// ====================================================================================================
	// COMPOUND CHARGE
	// ====================================================================================================

	@RequestMapping(value = "/accounts/{code}/compoundCharges", method = RequestMethod.POST)
	public ResponseEntity<String> addCompoundCharge(@PathVariable String code, @RequestBody CompoundCharge vo) {
		dummyLogin();

		AcAccount account = accountService.findAccountByCode(code);
		AcCompoundCharge compoundCharge = new AcCompoundChargeImpl();
		compoundCharge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		compoundCharge.setSourceNo(vo.getSourceNo());
		compoundCharge.setDescription(vo.getDescription());
		compoundCharge.setAmount(vo.getAmount());
		compoundCharge.setCompoundCode(vo.getCompoundCode());
		compoundCharge.setCompoundDescription(vo.getCompoundDescription());
		compoundCharge.setSession(accountService.findCurrentAcademicSession());
		compoundCharge.setChargeType(AcAccountChargeType.get(vo.getChargeType().ordinal()));
		accountService.addAccountCharge(account, compoundCharge);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{code}/compoundCharges/{id}", method = RequestMethod.PUT)
	public ResponseEntity<String> updateCompoundCharge(@PathVariable Long id, @PathVariable String code, @RequestBody CompoundCharge vo) {
		dummyLogin();
		// what can we update
		AcAccount account = accountService.findAccountByCode(code);
		AcAccountCharge compoundCharge = accountService.findAccountChargeById(vo.getId());
		compoundCharge.setReferenceNo("REFNO/" + System.currentTimeMillis());
		compoundCharge.setSourceNo(vo.getSourceNo());
		compoundCharge.setDescription(vo.getDescription());
		compoundCharge.setAmount(vo.getAmount());
		((AcCompoundCharge) compoundCharge).setCompoundCode(vo.getCompoundCode());
		((AcCompoundCharge) compoundCharge).setCompoundDescription(vo.getCompoundDescription());
		compoundCharge.setSession(accountService.findCurrentAcademicSession());
		compoundCharge.setChargeType(AcAccountChargeType.get(vo.getChargeType().ordinal()));
		accountService.updateAccountCharge(account, compoundCharge);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	
	@RequestMapping(value = "/accounts/{code}/compoundCharges/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> removeCompoundCharge(@PathVariable String code, @PathVariable Long id) {
		dummyLogin();

		AcAccount account = accountService.findAccountByCode(code);
		AcAccountCharge compoundCharge = accountService.findAccountChargeById(id);
		accountService.deleteAccountCharge(account, compoundCharge);
		LOG.debug("compoundCharge " + id + " is deleted");
		return new ResponseEntity<>("Removed", HttpStatus.OK);
	}


	// ====================================================================================================
	// PRIVATE METHODS
	// ====================================================================================================
	private List<AcAccount> decorateAccounts(List<AcAccount> accounts) {
		for (AcAccount a : accounts) {
			decorateAccount(a);
		}
		return accounts;
	}

	private AcAccount decorateAccount(AcAccount account) {
		account.setBalance(accountService.sumBalanceAmount(account));
		account.setEffectiveBalance(
				accountService.sumEffectiveBalanceAmount(account, accountService.findCurrentAcademicSession()));
		return account;
	}

	private void dummyLogin() {
		AcAutoLoginToken token = new AcAutoLoginToken("root");
		Authentication authed = authenticationManager.authenticate(token);
		SecurityContextHolder.getContext().setAuthentication(authed);
	}
}
