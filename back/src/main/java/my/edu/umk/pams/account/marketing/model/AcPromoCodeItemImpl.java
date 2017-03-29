package my.edu.umk.pams.account.marketing.model;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.core.AcMetadata;

/**
 * @author PAMS
 */
@Entity(name = "AcPromoCodeItem")
@Table(name = "AC_PRCD_ITEM")
public class AcPromoCodeItemImpl implements AcPromoCodeItem {

	@Id
	@GeneratedValue(generator = "SQ_AC_PRCD_ITEM")
	@SequenceGenerator(name = "SQ_AC_PRCD_ITEM", sequenceName = "SQ_AC_PRCD_ITEM", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@NotNull
	@Column(name = "SOURCE_NO")
	private String sourceNo;

	@NotNull
	@Column(name = "APPLIED")
	private boolean applied;

	@NotNull
	@ManyToOne(targetEntity = AcPromoCodeImpl.class)
	@JoinColumn(name = "PROMO_CODE_ID")
	private AcPromoCode promoCode;

	@ManyToOne(targetEntity = AcAccountImpl.class)
	@JoinColumn(name = "ACCOUNT_ID", nullable = true)
	private AcAccount account;

	@Embedded
	private AcMetadata metadata;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getSourceNo() {
		return sourceNo;
	}

	@Override
	public void setSourceNo(String sourceNo) {
		this.sourceNo = sourceNo;
	}

	@Override
	public boolean getApplied() {
		return applied;
	}

	@Override
	public void setApplied(boolean applied) {
		this.applied = applied;
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
		return AcPromoCode.class;
	}

	@Override
	public AcPromoCode getPromoCode() {
		return promoCode;
	}

	@Override
	public void setPromoCode(AcPromoCode promoCode) {
		this.promoCode = promoCode;
	}

	@Override
	public AcAccount getAccount() {
		return account;
	}

	@Override
	public void setAccount(AcAccount account) {
		this.account = account;
	}

}
