package my.edu.umk.pams.account.web.module.account.controller;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.web.module.account.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static my.edu.umk.pams.account.util.Util.toLimit;
import static my.edu.umk.pams.account.util.Util.toOffset;

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
    private AccountTransformer accountTransformer;

    @Autowired
    private AuthenticationManager authenticationManager;

    // ==================================================================================================== //
    // ACADEMIC SESSION
    // ==================================================================================================== //
    @RequestMapping(value = "/academicSessions", method = RequestMethod.GET)
    public ResponseEntity<List<AcademicSession>> findAcademicSessions() {
        List<AcAcademicSession> academicSessions = accountService.findAcademicSessions("%", 0, 100);
        return new ResponseEntity<List<AcademicSession>>(accountTransformer.toAcademicSessionVos(academicSessions), HttpStatus.OK);
    }

    @RequestMapping(value = "/academicSessions/{code}", method = RequestMethod.GET)
    public ResponseEntity<AcademicSession> findAcademicSessionBySession(@PathVariable String code) {
        AcAcademicSession academicSession = accountService.findAcademicSessionByCode(code);
        return new ResponseEntity<AcademicSession>(accountTransformer.toAcademicSessionVo(academicSession), HttpStatus.OK);
    }

    // ==================================================================================================== //
    // FEE SCHEDULE
    // ==================================================================================================== //

    @RequestMapping(value = "/feeSchedules", method = RequestMethod.GET)
    public ResponseEntity<List<FeeSchedule>> findFeeSchedules() {
        List<AcFeeSchedule> feeSchedules = accountService.findFeeSchedules("%", 0, 1);
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
        return new ResponseEntity<List<FeeScheduleItem>>(accountTransformer
                .toFeeScheduleItemVos(accountService.findFeeScheduleItems(feeSchedule)), HttpStatus.OK);
    }

    @RequestMapping(value = "/feeSchedules", method = RequestMethod.POST    )
    public ResponseEntity<String> saveFeeSchedule(@RequestBody FeeSchedule vo) {
        AcFeeSchedule feeSchedule = new AcFeeScheduleImpl();
        feeSchedule.setCode(vo.getCode());
        feeSchedule.setDescription(vo.getDescription());
        feeSchedule.setCohortCode(commonService.findCohortCodeById(vo.getCohortCode().getId()));
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/feeSchedules/{code}", method = RequestMethod.PUT)
    public ResponseEntity<String> updateFeeSchedule(@PathVariable String code, @RequestBody FeeSchedule vo) {
        AcFeeSchedule feeSchedule = accountService.findFeeScheduleByCode(code);
        feeSchedule.setCode(vo.getCode());
        feeSchedule.setDescription(vo.getDescription());
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/feeSchedules/{code}/feeScheduleItems", method = RequestMethod.POST)
    public void addFeeScheduleItem(@PathVariable String code, @RequestBody FeeScheduleItem item) {
        dummyLogin();
        AcFeeSchedule feeSchedule = accountService.findFeeScheduleByCode(code);
        AcFeeScheduleItem e = new AcFeeScheduleItemImpl();
        e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
        e.setAmount(item.getAmount());
        accountService.addFeeScheduleItem(feeSchedule, e);
    }

    @RequestMapping(value = "/feeSchedules/{code}/feeScheduleItems", method = RequestMethod.PUT)
    public void updateFeeScheduleItems(@PathVariable String code, @RequestBody FeeScheduleItem item) {
        dummyLogin();
        AcFeeSchedule feeSchedule = accountService.findFeeScheduleByCode(code);
        AcFeeScheduleItem e = accountService.findFeeScheduleItemById(item.getId());
        e.setChargeCode(accountService.findChargeCodeById(item.getChargeCode().getId()));
        e.setAmount(item.getAmount());
        accountService.updateFeeScheduleItem(feeSchedule, e);
    }


    // ==================================================================================================== //
    // CHARGE CODE
    // ==================================================================================================== //
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


    // ==================================================================================================== //
    // ACCOUNT
    // ==================================================================================================== //

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> findAccounts() {
        List<AcAccount> accounts = accountService.findAccounts(0, 100);
        return new ResponseEntity<List<Account>>(accountTransformer.toAccountVos(accounts), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/page/{pageNo}", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> findAccounts(@PathVariable Integer pageNo) {
        List<AcAccount> accounts = accountService.findAccounts(toOffset(pageNo), toLimit(pageNo));
        return new ResponseEntity<List<Account>>(accountTransformer.toAccountVos(accounts), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{code}", method = RequestMethod.GET)
    public ResponseEntity<Account> findAccountByCode(@PathVariable String code) {
        AcAccount account = accountService.findAccountByCode(code);
        return new ResponseEntity<Account>(accountTransformer.toAccountVo(account), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{code}", method = RequestMethod.PUT)
    public ResponseEntity<Account> updateAccount(@PathVariable String code) {
        AcAccount account = accountService.findAccountByCode(code);
        return new ResponseEntity<Account>(accountTransformer.toAccountVo(account), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{code}/accountTransactions", method = RequestMethod.GET)
    public ResponseEntity<List<AccountTransaction>> findInvoiceItems(@PathVariable String code) {
        AcAccount account = accountService.findAccountByCode(code);
        return new ResponseEntity<List<AccountTransaction>>(accountTransformer
                .toAccountTransactionVos(accountService.findAccountTransactions(account)), HttpStatus.OK);
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
