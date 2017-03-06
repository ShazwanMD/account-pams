package my.edu.umk.pams.account.system.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Entity(name = "AcReferenceNo")
@Table(name = "AC_RFRN_NO")
public class AcReferenceNoImpl implements AcReferenceNo {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_RFRN_NO")
    @SequenceGenerator(name = "SQ_AC_RFRN_NO", sequenceName = "SQ_AC_RFRN_NO", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotNull
    @Column(name = "PREFIX")
    private String prefix;

    @NotNull
    @Column(name = "SEQUENCE_FORMAT")
    private String sequenceFormat;

    @NotNull
    @Column(name = "REFERENCE_FORMAT")
    private String referenceFormat;

    @NotNull
    @Column(name = "INCREMENT_VALUE")
    private Integer incrementValue;

    @NotNull
    @Column(name = "CURRENT_VALUE")
    private Integer currentValue;

    @Embedded
    private AcMetadata metadata;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSequenceFormat() {
        return sequenceFormat;
    }

    public void setSequenceFormat(String sequenceFormat) {
        this.sequenceFormat = sequenceFormat;
    }

    public String getReferenceFormat() {
        return referenceFormat;
    }

    public void setReferenceFormat(String referenceFormat) {
        this.referenceFormat = referenceFormat;
    }

    public Integer getIncrementValue() {
        return incrementValue;
    }

    public void setIncrementValue(Integer incrementValue) {
        this.incrementValue = incrementValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }

    public AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcReferenceNo.class;
    }
}

