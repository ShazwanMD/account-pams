package my.edu.umk.pams.account.marketing.service;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.marketing.dao.AcPromoCodeDao;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItemImpl;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;
import my.edu.umk.pams.account.security.service.SecurityService;
import my.edu.umk.pams.account.system.service.SystemService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static my.edu.umk.pams.account.AccountConstants.PROMO_CODE_REFERENCE_NO;

@Transactional
@Service("marketingService")
public class MarketingServiceImpl implements MarketingService {

    private static final Logger LOG = LoggerFactory.getLogger(MarketingServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private SystemService systemService;

    @Autowired
    private AcPromoCodeDao promoCodeDao;

    @Override
    public AcPromoCode findPromoCodeById(Long id) {
        return promoCodeDao.findById(id);
    }

    @Override
    public AcPromoCode findPromoCodeByReferenceNo(String referenceNo) {
        return promoCodeDao.findByReferenceNo(referenceNo);
    }

    @Override
    public AcPromoCodeItem findPromoCodeItemById(Long id) {
        return promoCodeDao.findItemById(id);
    }

    @Override
    public AcPromoCodeItem findPromoCodeItemBySourceNo(String sourceNo) {
        return promoCodeDao.findBySourceNo(sourceNo);
    }

    @Override
    public List<AcPromoCode> findPromoCodes(Integer offset, Integer limit) {
        return promoCodeDao.find(offset, limit);
    }

    @Override
    public List<AcPromoCode> findByPromoCodeType(AcPromoCodeType promoCodeType, Integer offset, Integer limit) {
        return promoCodeDao.find(promoCodeType, offset, limit);
    }

    @Override
    public List<AcPromoCodeItem> findPromoCodeItems(AcPromoCode promoCode) {
        return promoCodeDao.findItems(promoCode);
    }

    @Override
    public List<AcPromoCodeItem> findPromoCodeItems(AcAccount account) {
        return promoCodeDao.findItems(account);
    }

    @Override
    public boolean hasPromoCodeExpired(Date now) {
        return promoCodeDao.hasExpired(now);
    }

    @Override
    public String initPromoCode(AcPromoCode promoCode) {
        // prepare reference no generator
        Map<String, Object> map = new HashMap<String, Object>();
        String referenceNo = systemService.generateFormattedReferenceNo(PROMO_CODE_REFERENCE_NO, map);
        promoCode.setReferenceNo(referenceNo);
        LOG.debug("Processing promo code with refNo {}", referenceNo);

        // save
        promoCodeDao.saveOrUpdate(promoCode, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(promoCode);

        // add items
        for (int i = 0; i < promoCode.getQuantity(); i++) {
            AcPromoCodeItem item = new AcPromoCodeItemImpl();
            item.setCode(UUID.randomUUID().toString().substring(0,8));
            item.setApplied(false);
            promoCodeDao.addItem(promoCode, item, securityService.getCurrentUser());
        }
        sessionFactory.getCurrentSession().flush();

        return referenceNo;
    }

    @Override
    public void addPromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem item) {
        promoCodeDao.addItem(promoCode, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updatePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem item) {
        promoCodeDao.updateItem(promoCode, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deletePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem item) {
        promoCodeDao.deleteItem(promoCode, item, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }
}
