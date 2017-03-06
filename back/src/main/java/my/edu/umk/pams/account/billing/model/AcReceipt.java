package my.edu.umk.pams.account.billing.model;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcReceipt {


    Long getId();

    void setId(Long id);

    List<AcReceiptItem> getItems();

    void setItems(List<AcReceiptItem> items);
}
