package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */

public interface AcCreditNoteItem extends AcMetaObject {

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

    AcCreditNote getCreditNote();

    void setCreditNote(AcCreditNote creditNote);
    
    Date getCreditNoteItemDate();
    
    void setCreditNoteItemDate(Date creditNoteItemDate);
}
