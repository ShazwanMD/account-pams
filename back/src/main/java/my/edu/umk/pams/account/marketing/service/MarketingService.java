package my.edu.umk.pams.account.marketing.service;

import java.util.Date;
import java.util.List;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;

public interface MarketingService {

	AcPromoCode findById(Long id);

	AcPromoCode findByCode(String code);

	AcPromoCodeItem findBySourceNo(String sourceNo);
	
	List<AcPromoCode> findByPromoCodeType(AcPromoCodeType promoCodeType, Integer offset, Integer limit);

	List<AcPromoCodeItem> findItems(AcPromoCode promoCode);

	List<AcPromoCodeItem> findItems(AcAccount account);
	
	boolean isExpired(Date now);

	void addItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user);

	void updateItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user);

	void deleteItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user);
}
