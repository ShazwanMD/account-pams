package my.edu.umk.pams.account.web.module.financialaid.controller;

import my.edu.umk.pams.account.financialaid.service.FinancialAidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author PAMS
 */
@RestController
@RequestMapping("/api/financialaid")
public class FinancialaidController {

    @Autowired
    private FinancialAidService financialAidService;

    @RequestMapping(value = "/dosomething", method = RequestMethod.GET)
    public ResponseEntity<String> findSomething() {
        return new ResponseEntity<String>("something", HttpStatus.OK);
    }
}
