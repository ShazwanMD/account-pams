package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.Date;

import my.edu.umk.pams.account.core.model.AcDocument;

public interface AcAdvancePayment extends AcDocument{

    String getDescription();

    void setDescription(String description);
    
    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    Date getIssuedDate();

    void setIssuedDate(Date issuedDate);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);

}
