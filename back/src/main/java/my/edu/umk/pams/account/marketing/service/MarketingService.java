package my.edu.umk.pams.account.marketing.service;

import my.edu.umk.pams.account.account.model.AcAccount;
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

    boolean hasPromoCodeExpired(Date now);

    String initPromoCode(AcPromoCode promoCode);

    void addPromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem item);

    void updatePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem item);

    void deletePromoCodeItem(AcPromoCode promoCode, AcPromoCodeItem item);
}
