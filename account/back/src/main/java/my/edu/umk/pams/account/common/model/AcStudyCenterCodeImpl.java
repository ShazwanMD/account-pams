package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "AcStudyCenterCode")
@Table(name = "AC_STDY_CNTR_CODE")
public class AcStudyCenterCodeImpl implements AcStudyCenterCode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_STDY_CNTR_CODE")
    @SequenceGenerator(name = "SQ_AC_STDY_CNTR_CODE", sequenceName = "SQ_AC_STDY_CNTR_CODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Embedded
    private AcMetadata metadata;

    @Override
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

    public AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcStudyCenterCode.class;
    }
}
