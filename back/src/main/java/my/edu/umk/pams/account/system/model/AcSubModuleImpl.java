package my.edu.umk.pams.account.system.model;


import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author canang technologies
 * @since 4/18/14
 */
@Entity(name = "AcSubModule")
@Table(name = "AC_SMDL")
public class AcSubModuleImpl implements AcSubModule, Serializable {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_SMDL")
    @SequenceGenerator(name = "SQ_AC_SMDL", sequenceName = "SQ_AC_SMDL", allocationSize = 1)
    private Long id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "ORDINAL")
    private Integer ordinal = 0;

    @Column(name = "ENABLED")
    private boolean enabled = true;

    @OneToOne(targetEntity = AcModuleImpl.class)
    @JoinColumn(name = "MODULE_ID")
    private AcModule module;

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

    @Override
    public Integer getOrdinal() {
        return ordinal;
    }

    @Override
    public void setOrdinal(Integer ordinal) {
        this.ordinal = ordinal;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public AcModule getModule() {
        return module;
    }

    public void setModule(AcModule module) {
        this.module = module;
    }

    public AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcSubModule.class;
    }

}
