package my.edu.umk.pams.account.billing.model;

import java.util.List;

/**
 * @author PAMS
 */
public interface AcReceipt {


    Long getId();

    void setId(Long id);
    

    String getDescription();
    
    void setDescription(String description);
    
    
    String getReceiptNumber();
    
    void setReceiptNumber(String receiptnumber);


/*    List<AcReceiptItem> getItems();

    void setItems(List<AcReceiptItem> items);*/
}
