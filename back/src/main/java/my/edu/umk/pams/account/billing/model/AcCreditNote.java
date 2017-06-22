package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.model.AcDocument;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcCreditNote extends AcDocument {
    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    Date getIssuedDate();

    void setIssuedDate(Date issuedDate);

    List<AcCreditNoteItem> getItems();

    void setItems(List<AcCreditNoteItem> items);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
    
    Date getCreditNoteDate();
    
    void setCreditNoteDate(Date creditNoteDate);

}
