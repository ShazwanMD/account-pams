package my.edu.umk.pams.account.marketing.model;

import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author PAMS
 */
public interface AcPromoCode extends AcMetaObject {

	/**
	 * todo(uda): referenceNo?
	 * @return
	 */
	String getCode();

	void setCode(String code);

	/**
	 *
	 * @return
	 */
	String getDescription();

	void setDescription(String description);

	/**
	 *
	 * @return
	 */
	BigDecimal getValue();

	void setValue(BigDecimal value);

	/**
	 *
	 * @return
	 */
	Integer getQuantity();

	void setQuantity(Integer quantity);

	/**
	 *
	 * @return
	 */
	Date getExpiryDate();

	void setExpiryDate(Date expiryDate);

	/**
	 *
	 * @return
	 */
	AcPromoCodeType getPromoCodeType();

	void setPromoCodeType(AcPromoCodeType promoCodeType);

	/**
	 *
	 * @return
	 */
	List<AcPromoCodeItem> getItems();

	void setItems(List<AcPromoCodeItem> items);

}
