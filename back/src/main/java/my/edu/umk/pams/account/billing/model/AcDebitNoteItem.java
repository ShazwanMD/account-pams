package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcDebitNoteItem extends AcMetaObject {

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

    AcDebitNote getDebitNote();

    void setDebitNote(AcDebitNote debitNote);
}
