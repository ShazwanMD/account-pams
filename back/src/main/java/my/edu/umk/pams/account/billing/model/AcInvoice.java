package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.model.AcDocument;

import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcInvoice extends AcDocument{

    Date getIssuedDate();

    void setIssuedDate(Date issuedDate);

    AcAccount getAccount();

    void setAccount(AcAccount account);

    List<AcInvoiceItem> getItems();

    void setItems(List<AcInvoiceItem> items);

    // todo(uda): todo
//    List<AcDebitNote> getDebitNotes();
//
//    void setDebitNotes(List<AcDebitNote> notes);
//
//    List<AcCreditNote> getCreditNotes();
//
//    void setCreditNotes(List<AcCreditNote> notes);
}
