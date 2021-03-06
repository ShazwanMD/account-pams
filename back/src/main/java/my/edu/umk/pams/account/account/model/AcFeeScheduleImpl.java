package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.common.model.*;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
@Entity(name = "AcFeeSchedule")
@Table(name = "AC_FEE_SCDL")
public class AcFeeScheduleImpl implements AcFeeSchedule {

    @Id
    @GeneratedValue(generator = "SQ_AC_FEE_SCDL")
    @SequenceGenerator(name = "SQ_AC_FEE_SCDL", sequenceName = "SQ_AC_FEE_SCDL", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "CODE", unique = true, nullable = false)
    private String code;

    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;
    
    @NotNull
    @Column(name = "STATUS")
    private Boolean status = true;

    @NotNull
    @OneToOne(targetEntity = AcCohortCodeImpl.class)
    @JoinColumn(name = "COHORT_CODE_ID", nullable = false)
    private AcCohortCode cohortCode;

    @NotNull
    @OneToOne(targetEntity = AcResidencyCodeImpl.class)
    @JoinColumn(name = "RESIDENCY_CODE_ID", nullable = false)
    private AcResidencyCode residencyCode;

    @NotNull
    @OneToOne(targetEntity = AcStudyModeImpl.class)
    @JoinColumn(name = "STUDY_MODE_ID", nullable = false)
    private AcStudyMode studyMode;
    
    @OneToOne(targetEntity = AcStudyCenterCodeImpl.class)
    @JoinColumn(name = "STUDY_CENTER_ID", nullable = true)
    private AcStudyCenterCode studyCenterCode;

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
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public AcCohortCode getCohortCode() {
        return cohortCode;
    }

    @Override
    public void setCohortCode(AcCohortCode cohortCode) {
        this.cohortCode = cohortCode;
    }

    @Override
    public AcResidencyCode getResidencyCode() {
        return residencyCode;
    }

    @Override
    public void setResidencyCode(AcResidencyCode residencyCode) {
        this.residencyCode = residencyCode;
    }

    @Override
    public AcStudyMode getStudyMode() {
        return studyMode;
    }

    @Override
    public void setStudyMode(AcStudyMode studyMode) {
        this.studyMode = studyMode;
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
    public Boolean getStatus() {
		return status;
	}

    @Override
    public void setStatus(Boolean status) {
		this.status = status;
	}

    @Override
    public AcStudyCenterCode getStudyCenterCode() {
		return studyCenterCode;
	}

    @Override
	public void setStudyCenterCode(AcStudyCenterCode studyCenterCode) {
		this.studyCenterCode = studyCenterCode;
	}

	@Override
    public Class<?> getInterfaceClass() {
        return AcFeeSchedule.class;
    }
}
