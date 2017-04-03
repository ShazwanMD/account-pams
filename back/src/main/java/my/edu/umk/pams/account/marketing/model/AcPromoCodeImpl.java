package my.edu.umk.pams.account.marketing.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcPromoCode")
@Table(name = "AC_PRMO_CODE")
public class AcPromoCodeImpl implements AcPromoCode {

	@Id
	@GeneratedValue(generator = "SQ_AC_PRMO_CODE")
	@SequenceGenerator(name = "SQ_AC_PRMO_CODE", sequenceName = "SQ_AC_PRMO_CODE", allocationSize = 1)
	@Column(name = "ID")
	private Long id;

	@Column(name = "VALUE", nullable = false)
	private BigDecimal value = BigDecimal.ZERO;

	@NotNull
	@Column(name = "CODE", nullable = false)
	private String code;

	@NotNull
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@NotNull
	@Column(name = "PROMO_CODE_TYPE")
	private AcPromoCodeType promoCodeType;

	@NotNull
	@Column(name = "QUANTITY")
	private int quantity;

	@NotNull
	@Column(name = "EXPIRY_DATE")
	private Date expiryDate;

	@OneToMany(targetEntity = AcPromoCodeItemImpl.class, mappedBy = "promoCode", fetch = FetchType.LAZY)
	private List<AcPromoCodeItemImpl> items;

	@Embedded
	private AcMetadata metadata;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long Id) {
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
	public BigDecimal getValue() {
		return value;
	}

	@Override
	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public AcPromoCodeType getPromoCodeType() {
		return promoCodeType;
	}

	@Override
	public void setPromoCodeType(AcPromoCodeType promoCodeType) {
		this.promoCodeType = promoCodeType;
	}

	@Override
	public Integer getQuantity() {
		return quantity;
	}

	@Override
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Override
	public Date getExpiryDate() {
		return expiryDate;
	}

	@Override
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public List<AcPromoCodeItemImpl> getItems() {
		return items;
	}

	@Override
	public void setItems(List<AcPromoCodeItemImpl> items) {
		this.items = items;
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

}
