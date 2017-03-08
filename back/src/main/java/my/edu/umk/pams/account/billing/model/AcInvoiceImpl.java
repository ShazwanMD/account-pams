package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * @author PAMS
 */
@Entity(name = "AcInvoice")
@Table(name = "AC_INVC")
public class AcInvoiceImpl implements AcInvoice{

    @Override
    public Long getId() {
        return null;
    }

    @Override
    public AcMetadata getMetadata() {
        return null;
    }

    @Override
    public void setMetadata(AcMetadata metadata) {

    }

    @Override
    public Class<?> getInterfaceClass() {
        return null;
    }

    @Override
    public AcAccount getAccount() {
        return null;
    }

    @Override
    public void setAccount(AcAccount account) {

    }

    @Override
    public List<AcInvoiceItem> getItems() {
        return null;
    }

    @Override
    public void setItems(List<AcInvoiceItem> items) {

    }

    @Override
    public List<AcDebitNote> getDebitNotes() {
        return null;
    }

    @Override
    public void setDebitNotes(List<AcDebitNote> notes) {

    }

    @Override
    public List<AcCreditNote> getCreditNotes() {
        return null;
    }

    @Override
    public void setCreditNotes(List<AcCreditNote> notes) {

    }
}
