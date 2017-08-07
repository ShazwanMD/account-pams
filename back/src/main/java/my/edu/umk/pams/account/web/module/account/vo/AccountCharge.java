package my.edu.umk.pams.account.web.module.account.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.common.vo.CohortCode;
import my.edu.umk.pams.account.web.module.common.vo.SecurityChargeCode;
import my.edu.umk.pams.account.web.module.common.vo.StudyMode;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
public class AccountCharge extends MetaObject {

	private String referenceNo;
	private String sourceNo;
	private String description;
	private BigDecimal amount;
	private AccountChargeType chargeType;
	private AcademicSession session;
	private CohortCode cohortCode;
	private StudyMode studyMode;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date chargeDate;
	private Integer ordinal;
	private String code;
	private SecurityChargeCode securityChargeCode;

	// transient
	private boolean invoiced;

	public String getReferenceNo() {
		return referenceNo;
	}

	public void setReferenceNo(String referenceNo) {
		this.referenceNo = referenceNo;
	}

	public String getSourceNo() {
		return sourceNo;
	}

	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public AccountChargeType getChargeType() {
		return chargeType;
	}

	public void setChargeType(AccountChargeType chargeType) {
		this.chargeType = chargeType;
	}

	public AcademicSession getSession() {
		return session;
	}

	public void setSession(AcademicSession session) {
		this.session = session;
	}

	public boolean isInvoiced() {
		return invoiced;
	}

	public void setInvoiced(boolean invoiced) {
		this.invoiced = invoiced;
	}

	public CohortCode getCohortCode() {
		return cohortCode;
	}

	public void setCohortCode(CohortCode cohortCode) {
		this.cohortCode = cohortCode;
	}

	public StudyMode getStudyMode() {
		return studyMode;
	}

	public void setStudyMode(StudyMode studyMode) {
		this.studyMode = studyMode;
	}

	public Date getChargeDate() {
		return chargeDate;
	}

	public void setChargeDate(Date chargeDate) {
		this.chargeDate = chargeDate;
	}

	public Integer getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(Integer ordinal) {
		this.ordinal = ordinal;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public SecurityChargeCode getSecurityChargeCode() {
		return securityChargeCode;
	}

	public void setSecurityChargeCode(SecurityChargeCode securityChargeCode) {
		this.securityChargeCode = securityChargeCode;
	}

	@JsonCreator
	public static AccountCharge create(String jsonString) {
		AccountCharge o = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			o = mapper.readValue(jsonString, AccountCharge.class);
		} catch (IOException e) {
			// handle
		}
		return o;
	}
}
