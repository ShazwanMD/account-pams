package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.common.model.AcPaymentMethod;
import my.edu.umk.pams.account.core.model.AcDocument;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcReceipt extends AcDocument{

    String getReceiptNo();

    void setReceiptNo(String receiptNo);

    AcReceiptType getReceiptType();

    void setReceiptType(AcReceiptType receiptType);

    BigDecimal getTotalApplied();

    void setTotalApplied(BigDecimal totalApplied);

    BigDecimal getTotalReceived();

    void setTotalReceived(BigDecimal totalReceived);

    BigDecimal getTotalAmountInCurrency();

    void setTotalAmountInCurrency(BigDecimal totalAmountInCurrency);

    BigDecimal getTotalAmount();

    Date getReceiveDate();

    void setReceiveDate(Date receiveDate);

    void setTotalAmount(BigDecimal totalAmount);

    AcPaymentMethod getPaymentMethod();

    void setPaymentMethod(AcPaymentMethod type);

    List<AcReceiptItem> getItems();

    void setItems(List<AcReceiptItem> items);

    AcAccount getAccount();

    void setAccount(AcAccount account);
}
