package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccount;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcInvoice {


    Long getId();

    void setId(Long id);

    AcAccount getAccount();

    void setAccount(AcAccount account);

    List<AcInvoiceItem> getItems();

    void setItems(List<AcInvoiceItem> items);

    List<AcDebitNote> getDebitNotes();

    void setDebitNotes(List<AcDebitNote> notes);

    List<AcCreditNote> getCreditNotes();

    void setCreditNotes(List<AcCreditNote> notes);
}
