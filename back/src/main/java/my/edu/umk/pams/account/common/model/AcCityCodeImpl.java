package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "AcCityCode")
@Table(name = "AC_CITY_CODE")
public class AcCityCodeImpl implements AcCityCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_CITY_CODE")
    @SequenceGenerator(name = "SQ_AC_CITY_CODE", sequenceName = "SQ_AC_CITY_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(targetEntity = AcStateCodeImpl.class)
    @JoinColumn(name = "STATE_CODE_ID")
    private AcStateCode stateCode;

    @Embedded
    private AcMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public AcStateCode getStateCode() {
        return stateCode;
    }

    @Override
    public void setStateCode(AcStateCode stateCode) {
        this.stateCode = stateCode;
    }

    public AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcCityCode.class;
    }
}
