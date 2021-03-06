package my.edu.umk.pams.account.common.model;

public interface AcStateCode extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    AcCountryCode getCountryCode();

    void setCountryCode(AcCountryCode countryCode);
}
