package my.edu.umk.pams.account.system.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Entity(name = "AcConfiguration")
@Table(name = "AC_CNFG")
@Configuration
@EnableScheduling
public class AcConfigurationImpl implements AcConfiguration {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_CNFG")
    @SequenceGenerator(name = "SQ_AC_CNFG", sequenceName = "SQ_AC_CNFG", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CONFIG_KEY", nullable = false)
    private String key;

    @Column(name = "CONFIG_VALUE")
    private String value;

    @Column(name = "CONFIG_VALUE_DOUBLE")
    private Double valueDouble;

    @Column(name = "CONFIG_VALUE_LONG")
    private Long valueLong;

    @Column(name = "CONFIG_VALUE_BYTEA")
    private byte[] valueByteArray;

    @Column(name = "DESCRIPTION")
    private String description;

    @Embedded
    private AcMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public byte[] getValueByteArray() {
        return valueByteArray;
    }

    public void setValueByteArray(byte[] valueByteArray) {
        this.valueByteArray = valueByteArray;
    }

    public Double getValueDouble() {
        return valueDouble;
    }

    public void setValueDouble(Double valueDouble) {
        this.valueDouble = valueDouble;
    }

    public Long getValueLong() {
        return valueLong;
    }

    public void setValueLong(Long valueLong) {
        this.valueLong = valueLong;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcConfiguration.class;
    }

    @Transient
    public Integer getValueAsInteger() {
        if (null != getValue())
            return new Integer(getValue());
        else return 0;
    }

    @Transient
    public Double getValueAsDouble() {
        if (null != getValue())
            return new Double(getValue());
        else return 0d;
    }

    @Transient
    public Long getValueAsLong() {
        if (null != getValue())
            return new Long(getValue());
        else return 0l;
    }

    @Transient
    public BigDecimal getValueAsBigDecimal() {
        if (null != getValue())
            return new BigDecimal(getValue());
        else return BigDecimal.ZERO;
    }

    @Transient
    public Boolean getValueAsBoolean() {
        if (null != getValue())
            return new Boolean(getValue());
        else return Boolean.FALSE;
    }
}
