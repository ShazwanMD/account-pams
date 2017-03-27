package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcInvoiceItem extends AcMetaObject{

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    BigDecimal getBalanceAmount();

    void setBalanceAmount(BigDecimal balanceAmount);

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);

    // todo(uda): balance
    // transient 
    //    boolean hasBalance();
}
