package my.edu.umk.pams.account.web.module.billing.controller;

import my.edu.umk.pams.account.billing.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @RequestMapping(value = "/dosomething", method = RequestMethod.GET)
    public ResponseEntity<String> findSomething() {
        return new ResponseEntity<String>("something", HttpStatus.OK);
    }
}
