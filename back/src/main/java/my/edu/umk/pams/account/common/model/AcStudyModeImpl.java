package my.edu.umk.pams.account.common.model;


import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "AcStudyMode")
@Table(name = "AC_STDY_MODE")
public class AcStudyModeImpl implements AcStudyMode {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_STDY_MODE")
    @SequenceGenerator(name = "SQ_AC_STDY_MODE", sequenceName = "SQ_AC_STDY_MODE", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "PREFIX", nullable = false)
    private String prefix;

    @NotNull
    @Column(name = "DESCRIPTION_MS", nullable = false)
    private String descriptionMs;
    
    @NotNull
    @Column(name = "DESCRIPTION_EN", nullable = false)
    private String descriptionEn;
    
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
    public String getPrefix() {
        return prefix;
    }

    @Override
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public String getDescriptionMs() {
        return descriptionMs;
    }

    @Override
    public void setDescriptionMs(String descriptionMs) {
        this.descriptionMs = descriptionMs;
    }

    @Override
    public String getDescriptionEn() {
        return descriptionEn;
    }

    @Override
    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
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
        return AcStudyMode.class;
    }

}
