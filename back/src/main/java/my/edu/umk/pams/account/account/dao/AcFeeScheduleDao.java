package my.edu.umk.pams.account.account.dao;

import my.edu.umk.pams.account.account.model.AcFeeSchedule;
import my.edu.umk.pams.account.account.model.AcFeeScheduleItem;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcResidencyCode;
import my.edu.umk.pams.account.common.model.AcStudyCenterCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcUser;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author PAMS
 */
public interface AcFeeScheduleDao extends GenericDao<Long, AcFeeSchedule> {

    AcFeeSchedule findByCode(String code);

    AcFeeSchedule findByCohortCodeAndResidencyCodeAndStudyMode(AcCohortCode cohortCode, AcResidencyCode residencyCode, AcStudyMode studyMode);

    AcFeeSchedule findFeeScheduleByCohortCodeAndResidencyCodeAndStudyModeAndStudyCenterCode(AcCohortCode cohortCode, AcResidencyCode residencyCode, AcStudyMode studyMode, AcStudyCenterCode studyCenter);
    
    AcFeeScheduleItem findItemById(Long id);

    List<AcFeeSchedule> find(String filter, Integer offset, Integer limit);

    List<AcFeeScheduleItem> findItems(AcFeeSchedule schedule);

    Integer count(String filter);

    Integer count(AcCohortCode cohortCode);

    Integer countItem(AcFeeSchedule schedule);

    boolean hasSchedule(AcCohortCode cohortCode);

    void addItem(AcFeeSchedule account, AcFeeScheduleItem item, AcUser user);

    void updateItem(AcFeeSchedule account, AcFeeScheduleItem item, AcUser user);

    void deleteItem(AcFeeSchedule account, AcFeeScheduleItem item, AcUser user);
    
    BigDecimal sumTotalAmount(AcFeeSchedule schedule, AcUser user);
}
