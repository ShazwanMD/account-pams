package my.edu.umk.pams.account.common.model;

public interface AcTaxCode extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    void setCode(String code);
    
    String getTaxRate();
    
    void setTaxRate(String taxRate);

    String getDescription();

    void setDescription(String description);
    
}
