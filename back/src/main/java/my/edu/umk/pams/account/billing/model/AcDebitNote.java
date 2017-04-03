package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.core.model.AcDocument;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
public interface AcDebitNote extends AcDocument{

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    Date getIssuedDate();

    void setIssuedDate(Date issuedDate);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);

}
