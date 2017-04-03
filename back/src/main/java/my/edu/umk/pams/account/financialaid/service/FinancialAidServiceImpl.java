package my.edu.umk.pams.account.financialaid.service;

import my.edu.umk.pams.account.AccountConstants;
import my.edu.umk.pams.account.account.dao.AcAcademicSessionDao;
import my.edu.umk.pams.account.account.dao.AcAccountDao;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.service.AccountService;
import my.edu.umk.pams.account.billing.dao.AcInvoiceDao;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.billing.model.AcInvoiceImpl;
import my.edu.umk.pams.account.billing.service.BillingService;
import my.edu.umk.pams.account.common.service.CommonService;
import my.edu.umk.pams.account.financialaid.dao.AcSettlementDao;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItemImpl;
import my.edu.umk.pams.account.financialaid.model.AcSettlementStatus;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;
import my.edu.umk.pams.account.financialaid.dao.AcWaiverApplicationDao;
import my.edu.umk.pams.account.identity.dao.AcStudentDao;
import my.edu.umk.pams.account.identity.model.AcSponsor;
import my.edu.umk.pams.account.identity.model.AcStudent;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static my.edu.umk.pams.account.AccountConstants.SETTLEMENT_REFERENCE_NO;

/**
 * @author PAMS
 */
@Transactional
@Service("financialaidService")
public class FinancialAidServiceImpl implements FinancialAidService {

    private static final Logger LOG = LoggerFactory.getLogger(FinancialAidServiceImpl.class);

    @Autowired
    private AcAccountDao accountDao;

    @Autowired
    private AcStudentDao studentDao;

//    @Autowired
//    private AcAccountChargeDao accountChargeDao;

    @Autowired
    private AcSettlementDao settlementDao;
    
    @Autowired
    private AcWaiverApplicationDao waiverApplicationDao;
    
    @Autowired
    private AcInvoiceDao invoiceDao;

    @Autowired
    private AcAcademicSessionDao academicSessionDao;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private SessionFactory sessionFactory;
    
    @Autowired
    private BillingService billingService;

    // ==================================================================================================== //
    // SETTLEMENT
    // ==================================================================================================== //


    @Override
    public AcSettlement findSettlementById(Long id) {
        return settlementDao.findById(id);
    }

    @Override
    public AcSettlement findSettlementByReferenceNo(String referenceNo) {
        return settlementDao.findByReferenceNo(referenceNo);
    }

    @Override
    public AcSettlementItem findSettlementItemById(Long id) {
        return settlementDao.findItemById(id);
    }

    @Override
    public List<AcSettlement> findSettlementes(Integer offset, Integer limit) {
        return settlementDao.find(offset, limit);
    }

    @Override
    public List<AcSettlement> findSettlementes(AcAcademicSession academicSession, Integer offset, Integer limit) {
        return settlementDao.find(academicSession, offset, limit);
    }

    @Override
    public List<AcSettlementItem> findSettlementItems(AcSettlement settlement) {
        return settlementDao.findItems(settlement);
    }

    @Override
    public List<AcSettlementItem> findSettlementItems(AcSettlement settlement, Integer offset, Integer limit) {
        return settlementDao.findItems(settlement, offset, limit);
    }

    @Override
    public Integer countSettlement() {
        return settlementDao.count();
    }

    @Override
    public Integer countSettlement(AcAcademicSession academicSession) {
        return settlementDao.count(academicSession);
    }

    @Override
    public Integer countSettlementItem(AcSettlement settlement) {
        return settlementDao.countItem(settlement);
    }


    @Override
    public void initSettlement(AcSettlement settlement) {
        // prepare reference no generator
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("academicSession", academicSessionDao.findCurrentSession());
        map.put("sponsor", settlement.getSponsor());
        String referenceNo = systemService.generateFormattedReferenceNo(SETTLEMENT_REFERENCE_NO, map);
        settlement.setReferenceNo(referenceNo);
        LOG.debug("Processing process settlement with refNo {}", referenceNo);

        // save
        settlementDao.saveOrUpdate(settlement, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();

        // generate item
        AcSponsor sponsor = settlement.getSponsor();
        List<AcStudent> students = studentDao.find(sponsor);
        for (AcStudent student : students) {
            AcSettlementItem item = new AcSettlementItemImpl();
            item.setSettlement(settlement);
            item.setAccount(accountDao.findByActor(student));
            item.setStatus(AcSettlementStatus.NEW);
            item.setSettlement(settlement);
            addSettlementItem(settlement, item);
        }
        sessionFactory.getCurrentSession().flush();
    }


    @Override
    public void executeSettlement(AcSettlement settlement) {
        // find all items for settlement
        // then iterate all, skip AcSettlementStatus.DISQUALIFIED
     	List<AcSettlementItem> settlementItem = settlementDao.findItems(settlement);
     	for(AcSettlementItem item : settlementItem){
     		if(item.getStatus()!= AcSettlementStatus.DISQUALIFIED){
     		    // generate reference no
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("academicSession", settlement.getSession());
                String referenceNo = systemService.generateFormattedReferenceNo(AccountConstants.INVOICE_REFERENCE_NO, map);

                // generate reference no
                Map<String, Object> mapInvoice = new HashMap<String, Object>();
                mapInvoice.put("academicSession", settlement.getSession());
                String invoiceNo = systemService.generateFormattedReferenceNo(AccountConstants.INVOICE_REFERENCE_NO, mapInvoice);
                
                // draft invoice
     			AcInvoice invoice = new AcInvoiceImpl();
                invoice.setReferenceNo(referenceNo);
     			invoice.setAccount(item.getAccount());
     			invoice.setSession(settlement.getSession());
                invoice.setIssuedDate(new Date());
     			invoice.setTotalAmount(BigDecimal.ZERO);
                invoice.setBalanceAmount(BigDecimal.ZERO);
                invoice.setPaid(false);
                invoice.setInvoiceNo(invoiceNo);

                // create item here
//                AcInvoiceItem invoiceItem = new AcInvoiceItemImpl();
//                invoiceItem.setBalanceAmount(BigDecimal.ZERO);
//                invoiceItem.setAmount(BigDecimal.ZERO);
//                invoiceItem.setDescription("");
//                invoiceItem.setChargeCode(accountService.findChargeCodeByCode("TMGSEB-MBA-00-H79331"));

     	        // serialize to invoice DRAFT
     	     	billingService.startInvoiceTask(invoice);
     		}
     	}
     	sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void saveSettlement(AcSettlement settlement) {
        settlementDao.save(settlement, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateSettlement(AcSettlement settlement) {
        settlementDao.update(settlement, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addSettlementItem(AcSettlement settlement, AcSettlementItem item) {
        settlementDao.addItem(settlement, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateSettlementItem(AcSettlement settlement, AcSettlementItem item) {
        settlementDao.updateItem(settlement, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteSettlementItem(AcSettlement settlement, AcSettlementItem item) {
        settlementDao.deleteItem(settlement, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
    
    @Override
    public void saveWaiverApplication(AcWaiverApplication waiverApplication) {
        waiverApplicationDao.save(waiverApplication, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
}
