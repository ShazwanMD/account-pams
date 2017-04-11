package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetaObject;

import java.math.BigDecimal;

/**
 * @author PAMS
 */
public interface AcFeeScheduleItem extends AcMetaObject{

    /**
     *
     * @return
     */
    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    /**
     *
     * @return
     */
    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

    /**
     *
     * @return
     */
    AcFeeSchedule getSchedule();

    void setSchedule(AcFeeSchedule schedule);

}
