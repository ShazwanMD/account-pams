package my.edu.umk.pams.account.financialaid.service;

import my.edu.umk.pams.account.account.dao.AcAccountDao;
import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.financialaid.dao.AcSettlementDao;
import my.edu.umk.pams.account.financialaid.model.AcSettlement;
import my.edu.umk.pams.account.financialaid.model.AcSettlementItem;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author PAMS
 */
@Service("financialaidService")
public class FinancialAidServiceImpl implements FinancialAidService {

    private static final Logger LOG = LoggerFactory.getLogger(FinancialAidServiceImpl.class);

    @Autowired
    private AcAccountDao accountDao;

//    @Autowired
//    private AcAccountChargeDao accountChargeDao;

    @Autowired
    private AcSettlementDao settlementDao;

//    @Autowired
//    private AcInvoiceDao invoiceDao;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private SessionFactory sessionFactory;

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
    public void executeSettlement(AcSettlement batch, List<AcSettlementItem> items) {
        // TODO: process qualification

        // TODO: process invoice

        // TODO:
    }

    @Override
    public void initSettlement(AcSettlement batch) {
        String referenceNo = null; // todo(uda): systemService.generateReferenceNo(PROCESS_BATCH_REFERENCE_NO);
        batch.setReferenceNo(referenceNo);
        LOG.debug("Processing process batch with refNo {}", new Object[]{referenceNo});

        // save
        settlementDao.save(batch, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();

//        // generate item
//        AcSponsor sponsor = batch.getSponsor();
//        List<AcSponsorship> sponsorships = sponsorshipDao.find(sponsor);
//        for (AcSponsorship sponsorship : sponsorships) {
//            AcSettlementItem item = new AcSettlementItemImpl();
//            item.setBatch(batch);
//            item.setAccount(actorAccountDao.findByActor(sponsorship.getStudent()));
//            item.setBatchStatus(AcSettlementStatus.NEW);
//            addSettlementItem(batch, item);
//        }
    }

    @Override
    public void saveSettlement(AcSettlement batch) {
        settlementDao.save(batch, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateSettlement(AcSettlement batch) {
        settlementDao.update(batch, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addSettlementItem(AcSettlement batch, AcSettlementItem item) {
        settlementDao.addItem(batch, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updateSettlementItem(AcSettlement batch, AcSettlementItem item) {
        settlementDao.updateItem(batch, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteSettlementItem(AcSettlement batch, AcSettlementItem item) {
        settlementDao.deleteItem(batch, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
}
