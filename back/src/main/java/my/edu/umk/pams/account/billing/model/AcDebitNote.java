package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.core.model.AcDocument;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcDebitNote extends AcDocument{

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    Date getIssuedDate();

    void setIssuedDate(Date issuedDate);

    List<AcDebitNoteItem> getItems();

    void setItems(List<AcDebitNoteItem> items);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
    
    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

    Date getDebitNoteDate();
    
    void setDebitNoteDate(Date debitNoteDate);
    
    BigDecimal getBalanceAmount();

    void setBalanceAmount(BigDecimal balanceAmount);

    Boolean getPaid();

    void setPaid(Boolean paid);

}
