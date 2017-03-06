package my.edu.umk.pams.account.billing.model;

/**
 * @author PAMS
 */
public interface AcDebitNote {


    Long getId();

    void setId(Long id);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);

}
