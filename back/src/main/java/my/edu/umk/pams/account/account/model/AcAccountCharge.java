package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.common.model.AcCohortCode;
import my.edu.umk.pams.account.common.model.AcSecurityChargeCode;
import my.edu.umk.pams.account.common.model.AcStudyMode;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.core.AcFlowdata;
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

	String getCode();

	void setCode(String code);

	AcSecurityChargeCode getSecurityChargeCode();

	void setSecurityChargeCode(AcSecurityChargeCode securityChargeCode);

	AcTaxCode getTaxCode();

	void setTaxCode(AcTaxCode taxCode);

	Boolean getInclusive();

	void setInclusive(Boolean inclusive);

	BigDecimal getTaxAmount();

	void setTaxAmount(BigDecimal taxAmount);

	void setNetAmount(BigDecimal netAmount);

	BigDecimal getNetAmount();

	BigDecimal getBalanceAmount();

	void setBalanceAmount(BigDecimal balanceAmount);

	Boolean getPaid();

	void setPaid(Boolean paid);

	AcFlowdata getFlowdata();

	void setFlowdata(AcFlowdata flowdata);

}
