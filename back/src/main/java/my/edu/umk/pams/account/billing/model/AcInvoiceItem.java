package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcInvoiceItem extends AcMetaObject{

    String getDescription();

    void setDescription(String description);

    // todo(hajar): pretaxAmount
    // todo(hajar): taxAmount
    // todo(hajar): totalAmount
    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    BigDecimal getBalanceAmount();

    void setBalanceAmount(BigDecimal balanceAmount);

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

    AcTaxCode getTaxCode();

    void setTaxCode(AcTaxCode TaxCode);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
}
