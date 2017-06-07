package my.edu.umk.pams.account.common.model;

public interface AcTaxCode extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    void setCode(String code);
    
    String getTaxRate();
    
    void setTaxRate(String taxRate);

    String getDescription();

    void setDescription(String description);
    
    String getAccrualType();

    void setAccrualType(String accrualType);

    String getTaxType();

    void setTaxType(String taxType);
    
    String getRate();
    
    void setRate(String rate);
    
    String getPurposeType();
    
    void setPurposeType(String purposeType);

}
