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

	Long getId();

	void setId(Long Id);

	String getCode();

	void setCode(String code);

	String getDescription();

	void setDescription(String description);

	BigDecimal getValue();

	void setValue(BigDecimal value);

	AcPromoCodeType getPromoCodeType();

	void setPromoCodeType(AcPromoCodeType promoCodeType);

	Integer getQuantity();

	void setQuantity(Integer quantity);

	Date getExpiryDate();

	void setExpiryDate(Date expiryDate);

	List<AcPromoCodeItemImpl> getItems();

	void setItems(List<AcPromoCodeItemImpl> items);

}
