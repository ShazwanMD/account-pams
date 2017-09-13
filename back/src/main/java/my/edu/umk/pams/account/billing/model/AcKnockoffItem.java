package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcKnockoffItem extends AcMetaObject{

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
    
    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);
    
    AcKnockoff getKnockoff();

    void setKnockoff(AcKnockoff knockoff);
}
