package my.edu.umk.pams.account.web.module.marketing.controller;

import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.identity.service.IdentityService;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItemImpl;
import my.edu.umk.pams.account.marketing.service.MarketingService;
import my.edu.umk.pams.account.security.integration.AcAutoLoginToken;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
import my.edu.umk.pams.account.web.module.billing.controller.BillingTransformer;
import my.edu.umk.pams.account.web.module.marketing.vo.PromoCode;
import my.edu.umk.pams.account.web.module.marketing.vo.PromoCodeItem;
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

import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
@RestController
@RequestMapping("/api/marketing")
public class MarketingController {

    private static final Logger LOG = LoggerFactory.getLogger(MarketingController.class);

    @Autowired
    private MarketingService marketingService;

    @Autowired
    private BillingService billingService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private IdentityService identityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private WorkflowService workflowService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private BillingTransformer billingTransformer;

    @Autowired
    private MarketingTransformer marketingTransformer;

    @Autowired
    private AuthenticationManager authenticationManager;


    // ==================================================================================================== //
    //  PromoCode
    // ==================================================================================================== //

    @RequestMapping(value = "/promoCodes", method = RequestMethod.GET)
    public ResponseEntity<List<PromoCode>> findPromoCodes() {
        List<AcPromoCode> promoCodes = marketingService.findPromoCodes(0, 100);
        return new ResponseEntity<List<PromoCode>>(marketingTransformer.toPromoCodeVos(promoCodes), HttpStatus.OK);
    }

    @RequestMapping(value = "/promoCodes/init", method = RequestMethod.POST)
    public ResponseEntity<String> initPromoCode(@RequestBody PromoCode vo) {
        
        AcPromoCode promoCode = new AcPromoCodeImpl();
        promoCode.setDescription(vo.getDescription());
        promoCode.setQuantity(vo.getQuantity());
        promoCode.setValue(vo.getValue());
        promoCode.setExpiryDate(new Date());
        String referenceNo = marketingService.initPromoCode(promoCode);
        return new ResponseEntity<String>(referenceNo, HttpStatus.OK);
    }

    @RequestMapping(value = "/promoCodes/{referenceNo}", method = RequestMethod.GET)
    public ResponseEntity<PromoCode> findPromoCodeByReferenceNo(@PathVariable String referenceNo) {
        AcPromoCode promoCode = marketingService.findPromoCodeByReferenceNo(referenceNo);
        return new ResponseEntity<PromoCode>(marketingTransformer.toPromoCodeVo(promoCode), HttpStatus.OK);
    }

    @RequestMapping(value = "/promoCodes/{referenceNo}", method = RequestMethod.PUT)
    public ResponseEntity<PromoCode> updatePromoCode(@PathVariable String referenceNo, @RequestBody PromoCode vo) {
        AcPromoCode promoCode = marketingService.findPromoCodeByReferenceNo(referenceNo);
        return new ResponseEntity<PromoCode>(marketingTransformer.toPromoCodeVo(promoCode), HttpStatus.OK);
    }

    @RequestMapping(value = "/promoCodes/{referenceNo}/promoCodeItems", method = RequestMethod.GET)
    public ResponseEntity<List<PromoCodeItem>> findPromoCodeItems(@PathVariable String referenceNo) {
        
        AcPromoCode promoCode = marketingService.findPromoCodeByReferenceNo(referenceNo);
        return new ResponseEntity<List<PromoCodeItem>>(marketingTransformer
                .toPromoCodeItemVos(marketingService.findPromoCodeItems(promoCode)), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/promoCodes/{referenceNo}/promoCodeItems", method = RequestMethod.POST)
    public ResponseEntity<String> addPromoCodeItem(@PathVariable String referenceNo, @RequestBody PromoCodeItem item) {
        
        AcPromoCode promoCode = marketingService.findPromoCodeByReferenceNo(referenceNo);
        AcPromoCodeItem e = new AcPromoCodeItemImpl();
        e.setApplied(false);
        e.setCode(item.getCode());
        e.setSourceNo(item.getSourceNo());
        e.setPromoCode(promoCode);
        if(null != item.getAccount() && null != item.getAccount().getId())
            e.setAccount(accountService.findAccountById(item.getAccount().getId()));
        marketingService.addPromoCodeItem(promoCode, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/promoCodes/{referenceNo}/promoCodeItems/{id}", method = RequestMethod.PUT)
    public ResponseEntity<String> updatePromoCodeItems(@PathVariable String referenceNo, @PathVariable Long id, @RequestBody PromoCodeItem item) {
        
        AcPromoCode promoCode = marketingService.findPromoCodeByReferenceNo(referenceNo);
        AcPromoCodeItem e = marketingService.findPromoCodeItemById(item.getId());
        e.setApplied(item.isApplied());
        e.setCode(item.getCode());
        e.setSourceNo(item.getSourceNo());
        e.setPromoCode(promoCode);
        e.setAccount(accountService.findAccountById(item.getAccount().getId()));
        marketingService.updatePromoCodeItem(promoCode, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    @RequestMapping(value = "/promoCodes/{referenceNo}/promoCodeItems/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deletePromoCodeItems(@PathVariable String referenceNo, @PathVariable Long id) {
        
        AcPromoCode promoCode = marketingService.findPromoCodeByReferenceNo(referenceNo);
        AcPromoCodeItem e = marketingService.findPromoCodeItemById(id);
        marketingService.deletePromoCodeItem(promoCode, e);
        return new ResponseEntity<String>("Success", HttpStatus.OK);
    }

    // ====================================================================================================
    // PRIVATE METHODS
    // ====================================================================================================

}
