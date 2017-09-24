package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcReceiptAccountChargeItem extends AcMetaObject{

    String getDescription();

    void setDescription(String description);

    BigDecimal getDueAmount();

    void setDueAmount(BigDecimal dueAmount);

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    BigDecimal getAppliedAmount();

    void setAppliedAmount(BigDecimal appliedAmount);

    BigDecimal getAdjustedAmount();

    void setAdjustedAmount(BigDecimal adjustedAmount);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Integer getUnit();

    void setUnit(Integer unit);

    AcReceipt getReceipt();

    void setReceipt(AcReceipt receipt);

	AcAccountCharge getAccountCharge();

	void setAccountCharge(AcAccountCharge accountCharge);

}
