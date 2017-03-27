package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "AcStateCode")
@Table(name = "AC_STTE_CODE")
public class AcStateCodeImpl implements AcStateCode {

    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "SQ_AC_STTE_CODE")
    @SequenceGenerator(name = "SQ_AC_STTE_CODE", sequenceName = "SQ_AC_STTE_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(targetEntity = AcCountryCodeImpl.class)
    @JoinColumn(name = "COUNTRY_CODE_ID")
    private AcCountryCode countryCode;

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
    public AcCountryCode getCountryCode() {
        return countryCode;
    }

    @Override
    public void setCountryCode(AcCountryCode countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public AcMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcStateCode.class;
    }
}
