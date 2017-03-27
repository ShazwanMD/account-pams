package my.edu.umk.pams.account.system.model;

import java.math.BigDecimal;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcConfiguration extends my.edu.umk.pams.account.core.AcMetaObject {

    String getKey();

    void setKey(String value);

    String getValue();

    void setValue(String value);

    byte[] getValueByteArray();

    void setValueByteArray(byte[] value);

    String getDescription();

    void setDescription(String description);

    Integer getValueAsInteger();

    Double getValueAsDouble();

    Long getValueAsLong();

    BigDecimal getValueAsBigDecimal();

    Boolean getValueAsBoolean();

}
