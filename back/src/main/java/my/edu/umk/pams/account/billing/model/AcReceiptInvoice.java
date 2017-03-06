package my.edu.umk.pams.account.billing.model;

/**
 * @author PAMS
 */
public interface AcReceiptInvoice {


    Long getId();

    void setId(Long id);

    AcReceipt getReceipt();

    void setReceipt(AcReceipt receipt);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
}
