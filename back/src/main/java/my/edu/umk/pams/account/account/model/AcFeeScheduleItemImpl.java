package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
@Entity(name = "AcFeeScheduleItem")
@Table(name = "AC_FEE_SCDL_ITEM")
public class AcFeeScheduleItemImpl implements AcFeeScheduleItem {

    @Id
    @GeneratedValue(generator = "SQ_AC_FEE_SCDL_ITEM")
    @SequenceGenerator(name = "SQ_AC_FEE_SCDL_ITEM", sequenceName = "SQ_AC_FEE_SCDL_ITEM", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount;

    @NotNull
    @OneToOne(targetEntity = AcChargeCodeImpl.class)
    @JoinColumn(name = "CHARGE_CODE_ID")
    private AcChargeCode chargeCode;

    @NotNull
    @OneToOne(targetEntity = AcFeeScheduleImpl.class)
    @JoinColumn(name = "SCHEDULE_ID")
    private AcFeeSchedule schedule;

    @Embedded
    private AcMetadata metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public AcChargeCode getChargeCode() {
        return chargeCode;
    }

    @Override
    public void setChargeCode(AcChargeCode chargeCode) {
        this.chargeCode = chargeCode;
    }

    @Override
    public AcFeeSchedule getSchedule() {
        return schedule;
    }

    @Override
    public void setSchedule(AcFeeSchedule schedule) {
        this.schedule = schedule;
    }

    public AcMetadata getMetadata() {
        return metadata;
    }

    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcFeeScheduleItem.class;
    }


}
