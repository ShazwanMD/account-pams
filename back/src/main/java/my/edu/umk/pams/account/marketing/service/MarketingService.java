package my.edu.umk.pams.account.marketing.service;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;

import java.util.Date;
import java.util.List;

public interface MarketingService {

    AcPromoCode findPromoCodeById(Long id);

    AcPromoCode findPromoCodeByCode(String code);

    AcPromoCodeItem findPromoCodeItemById(Long id);

    AcPromoCodeItem findPromoCodeItemBySourceNo(String sourceNo);

    List<AcPromoCode> findPromoCodes(Integer offset, Integer limit);

    List<AcPromoCode> findByPromoCodeType(AcPromoCodeType promoCodeType, Integer offset, Integer limit);

    List<AcPromoCodeItem> findPromoCodeItems(AcPromoCode promoCode);

    List<AcPromoCodeItem> findPromoCodeItems(AcAccount account);

    boolean isPromoCodeExpired(Date now);

    void addPromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user);

    void updatePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user);

    void deletePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user);

}
