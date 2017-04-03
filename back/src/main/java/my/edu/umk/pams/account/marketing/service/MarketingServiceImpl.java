package my.edu.umk.pams.account.marketing.service;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.marketing.dao.AcPromoCodeDao;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

@Transactional
@Service("marketingService")
public class MarketingServiceImpl implements MarketingService {

    private static final Logger LOG = LoggerFactory.getLogger(MarketingServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private AcPromoCodeDao promoCodeDao;

    @Override
    public AcPromoCode findPromoCodeById(Long id) {
        return promoCodeDao.findById(id);
    }

    @Override
    public AcPromoCode findPromoCodeByCode(String code) {
        return promoCodeDao.findByCode(code);
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
    public boolean isPromoCodeExpired(Date now) {
        return promoCodeDao.isExpired(now);
    }

    @Override
    public void addPromoCode(AcPromoCode promoCode) {
        promoCodeDao.save(promoCode, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void addPromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem detail) {
        promoCodeDao.addItem(promoCode, detail, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void updatePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem detail) {
        promoCodeDao.updateItem(promoCode, detail, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deletePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem detail) {
        promoCodeDao.deleteItem(promoCode, detail, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Deprecated
    public void addPromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user) {
        promoCodeDao.addItem(promoCode, detail, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Deprecated
    public void updatePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user) {
        promoCodeDao.updateItem(promoCode, detail, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }

    @Deprecated
    public void deletePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user) {
        promoCodeDao.deleteItem(promoCode, detail, securityService.getCurrentUser());
        sessionFactory.getCurrentSession().flush();
    }


}
