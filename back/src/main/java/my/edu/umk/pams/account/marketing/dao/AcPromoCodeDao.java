package my.edu.umk.pams.account.marketing.dao;

import java.util.Date;
import java.util.List;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.marketing.model.AcPromoCode;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeItem;
import my.edu.umk.pams.account.marketing.model.AcPromoCodeType;

/**
 * @author PAMS
 */
public interface AcPromoCodeDao extends GenericDao<Long, AcPromoCode> {

	// ====================================================================================================
	// PROMO CODE
	// ====================================================================================================

	AcPromoCode findById(Long id);

	AcPromoCode findByCode(String code);

	List<AcPromoCode> find(AcPromoCodeType promoCodeType, Integer offset, Integer limit);

	boolean isExpired(Date now);

	// ====================================================================================================
	// PROMO CODE ITEM
	// ====================================================================================================

	AcPromoCodeItem findBySourceNo(String sourceNo);

	List<AcPromoCodeItem> findItems(AcPromoCode promoCode);

	List<AcPromoCodeItem> findItems(AcAccount account);

	void addItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user);

	void updateItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user);

	void deleteItem(AcPromoCode promoCode, AcPromoCodeItem detail, AcUser user);
}
