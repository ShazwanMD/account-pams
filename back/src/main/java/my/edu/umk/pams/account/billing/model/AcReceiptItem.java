package my.edu.umk.pams.account.billing.model;

/**
 * @author PAMS
 */
public interface AcReceiptItem {


    Long getId();

    void setId(Long id);

    AcReceipt getReceipt();

    void setReceipt(AcReceipt receipt);

}
