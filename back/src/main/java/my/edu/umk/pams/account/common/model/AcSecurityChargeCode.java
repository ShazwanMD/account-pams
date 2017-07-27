package my.edu.umk.pams.account.common.model;

import java.math.BigDecimal;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcSecurityChargeCode extends AcMetaObject {

	void setId(Long id);

	String getSection();

	void setSection(String section);

	String getDescription();

	void setDescription(String description);

	String getOffense();

	void setOffense(String offense);

	String getOffenseDescription();

	void setOffenseDescription(String offenseDescription);

	BigDecimal getAmount();

	void setAmount(BigDecimal amount);

	String getAmountDescription();

	void setAmountDescription(String amountDescription);

	Boolean getActive();

	void setActive(Boolean active);

    
}
