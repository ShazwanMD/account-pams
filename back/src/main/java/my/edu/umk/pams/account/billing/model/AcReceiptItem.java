package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcReceiptItem extends AcMetaObject{

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

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);

    AcReceipt getReceipt();

    void setReceipt(AcReceipt receipt);

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

	AcAccountCharge getAccountCharge();

	void setAccountCharge(AcAccountCharge accountCharge);

	AcDebitNote getDebitNote();
	
	void setDebitNote(AcDebitNote debitNote);

	AcReceiptItemType getReceiptItemType();

	void setReceiptItemType(AcReceiptItemType receiptItemType);

}
