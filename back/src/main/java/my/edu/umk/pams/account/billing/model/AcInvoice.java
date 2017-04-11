package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.model.AcDocument;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcInvoice extends AcDocument{

    String getInvoiceNo();

    void setInvoiceNo(String invoiceNo);

    BigDecimal getTotalAmount();

    void setTotalAmount(BigDecimal totalAmount);

    BigDecimal getBalanceAmount();

    void setBalanceAmount(BigDecimal balanceAmount);

    boolean isPaid();

    void setPaid(boolean paid);

    Date getIssuedDate();

    void setIssuedDate(Date issuedDate);

    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);

    AcAccount getAccount();

    void setAccount(AcAccount account);

    List<AcInvoiceItem> getItems();

    void setItems(List<AcInvoiceItem> items);

    List<AcDebitNote> getDebitNotes();

    void setDebitNotes(List<AcDebitNote> notes);

    List<AcCreditNote> getCreditNotes();

    void setCreditNotes(List<AcCreditNote> notes);
}
