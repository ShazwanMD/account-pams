package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author PAMS
 */
@Entity(name = "AcAcademicSession")
@Table(name = "AC_ACDM_SESN")
public class AcAcademicSessionImpl implements AcAcademicSession {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AC_ACDM_SESN")
    @SequenceGenerator(name = "SQ_AC_ACDM_SESN", sequenceName = "SQ_AC_ACDM_SESN", allocationSize = 1)
    @Column(name = "ID", nullable = false)
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name="START_DATE", nullable = false)
    private Date startDate;

    @Column(name="END_DATE", nullable = false)
    private Date endDate;

    @Column(name = "CURRENT_", nullable = false)
    private Boolean current;

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

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public Boolean isCurrent() {
        return current;
    }

    public Boolean getCurrent() {
        return current;
    }

    public void setCurrent(Boolean current) {
        this.current = current;
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
        return AcAcademicSession.class;
    }
}
