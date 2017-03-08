package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcReceiptItem extends AcMetaObject{

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmountDue();

    void setAmountDue(BigDecimal amountDue);

    BigDecimal getAmountApplied();

    void setAmountApplied(BigDecimal amountApplied);

    BigDecimal getAdjustmentAmount();

    void setAdjustmentAmount(BigDecimal adjustmentAmount);

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    BigDecimal getPrice();

    void setPrice(BigDecimal price);

    Integer getUnit();

    void setUnit(Integer unit);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);

    AcReceipt getReceipt();

    void setReceipt(AcReceipt receipt);

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

}
