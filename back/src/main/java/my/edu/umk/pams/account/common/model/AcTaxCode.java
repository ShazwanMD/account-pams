package my.edu.umk.pams.account.common.model;

import java.math.BigDecimal;

public interface AcTaxCode extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

	BigDecimal getTaxRate();

	void setTaxRate(BigDecimal taxRate);

	Boolean getActive();

	void setActive(Boolean active);

}
