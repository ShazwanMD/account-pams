package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcMetaObject;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcInvoice extends AcMetaObject{

    AcAccount getAccount();

    void setAccount(AcAccount account);

    List<AcInvoiceItem> getItems();

    void setItems(List<AcInvoiceItem> items);

    List<AcDebitNote> getDebitNotes();

    void setDebitNotes(List<AcDebitNote> notes);

    List<AcCreditNote> getCreditNotes();

    void setCreditNotes(List<AcCreditNote> notes);
}
