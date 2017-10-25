package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcWaiverItem extends AcMetaObject {

    String getDescription();

    void setDescription(String description);

    BigDecimal getDueAmount();

    void setDueAmount(BigDecimal dueAmount);

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    BigDecimal getAppliedAmount();

    void setAppliedAmount(BigDecimal appliedAmount);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);

    AcWaiverFinanceApplication getWaiverFinanceApplication();

    void setWaiverFinanceApplication(AcWaiverFinanceApplication waiverFinanceApplication);

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

	AcAccountCharge getAccountCharge();

	void setAccountCharge(AcAccountCharge accountCharge);	
	
	void setDebitNote(AcDebitNote debitNote);
	
	AcDebitNote getDebitNote();

	AcWaiverItemType getWaiverItemType();

	void setWaiverItemType(AcWaiverItemType waiverItemType);


}
