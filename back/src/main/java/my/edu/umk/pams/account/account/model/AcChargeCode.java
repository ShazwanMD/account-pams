package my.edu.umk.pams.account.account.model;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * @author PAMS
 */
public interface AcChargeCode extends AcMetaObject{

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    AcChargeCodeType getChargeType();

    void setChargeType(AcChargeCodeType type);

    Integer getPriority();

    void setPriority(Integer priority);
    
    void setTaxCode(AcTaxCode taxCode);

    AcTaxCode getTaxCode();

	Boolean getInclusive();

	void setInclusive(Boolean inclusive);

}
