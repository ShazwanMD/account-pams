package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.core.AcMetaObject;
import my.edu.umk.pams.account.web.module.account.vo.AccountChargeType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
public interface AcAccountCharge extends AcMetaObject {

    String getReferenceNo();

    void setReferenceNo(String referenceNo);

    String getSourceNo();

    void setSourceNo(String sourceNo);

    String getDescription();

    void setDescription(String description);

    BigDecimal getAmount();

    void setAmount(BigDecimal amount);

    void setChargeDate(Date chargeDate);

    Date getChargeDate();

    Integer getOrdinal();

    void setOrdinal(Integer ordinal);

    AcAccountChargeType getChargeType();

    void setChargeType(AcAccountChargeType chargeType);

    AcAccount getAccount();

    void setAccount(AcAccount account);

    AcAcademicSession getSession();

    void setSession(AcAcademicSession session);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);

    AcCohortCode getCohortCode();

    void setCohortCode(AcCohortCode cohortCode);

    AcStudyMode getStudyMode();

    void setStudyMode(AcStudyMode studyMode);


}
