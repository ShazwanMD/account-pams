package my.edu.umk.pams.account.web.module.account.controller;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.billing.vo.InvoiceItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    private AccountTransformer accountTransformer;

    @RequestMapping(value = "/accounts/", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> findAccounts() {
        List<AcAccount> accounts =  accountService.findAccounts(0,100); // todo(uda): pagination
        return new ResponseEntity<List<Account>>(accountTransformer.toAccountVos(accounts), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{code}", method = RequestMethod.GET)
    public ResponseEntity<Account> findAccountByCode(@PathVariable String code) {
        AcAccount account =  accountService.findAccountByCode(code);
        return new ResponseEntity<Account>(accountTransformer.toAccountVo(account), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{code}", method = RequestMethod.POST)
    public ResponseEntity<Account> updateAccount(@PathVariable String code) {
        AcAccount account =  accountService.findAccountByCode(code);
        return new ResponseEntity<Account>(accountTransformer.toAccountVo(account), HttpStatus.OK);
    }

    @RequestMapping(value = "/accounts/{code:.+}/accountTransactions", method = RequestMethod.GET)
    public ResponseEntity<List<InvoiceItem>> findInvoiceItems(@PathVariable String referenceNo) {
        throw new UnsupportedOperationException();
    }

}
