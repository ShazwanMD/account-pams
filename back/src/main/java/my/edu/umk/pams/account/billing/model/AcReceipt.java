package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.common.model.AcPaymentMethod;
import my.edu.umk.pams.account.core.model.AcDocument;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    BigDecimal getTotalAmount();

    Date getReceivedDate();

    void setReceivedDate(Date receivedDate);

    void setTotalAmount(BigDecimal totalAmount);

    AcPaymentMethod getPaymentMethod();

    void setPaymentMethod(AcPaymentMethod type);

    List<AcReceiptItem> getItems();

    void setItems(List<AcReceiptItem> items);

    AcAccount getAccount();

    void setAccount(AcAccount account);
    
    List<AcInvoice> getInvoices();
    
    AcAcademicSession getSession();

	void setSession(AcAcademicSession session);
	
	void setTotalPayment(BigDecimal totalPayment);

	BigDecimal getTotalPayment();
}
