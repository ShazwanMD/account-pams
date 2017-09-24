package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.account.model.AcAccountChargeImpl;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
@Entity(name = "AcReceiptAccountChargeItem")
@Table(name = "AC_RCPT_CHRG_ITEM")
public class AcReceiptAccountChargeItemImpl implements AcReceiptAccountChargeItem {

    @Id
    @GeneratedValue(generator = "SQ_AC_CHRG_ITEM")
    @SequenceGenerator(name = "SQ_AC_CHRG_ITEM", sequenceName = "SQ_AC_CHRG_ITEM", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "DUE_AMOUNT", nullable = false)
    private BigDecimal dueAmount = BigDecimal.ZERO;

    @NotNull
    @Column(name = "APPLIED_AMOUNT", nullable = false)
    private BigDecimal appliedAmount = BigDecimal.ZERO;

    @Column(name = "ADJUSTED_AMOUNT")
    private BigDecimal adjustedAmount;

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount;

    @Column(name = "PRICE")
    private BigDecimal price;

    @Column(name = "UNIT")
    private Integer unit;
    
    @ManyToOne(targetEntity = AcAccountChargeImpl.class)
    @JoinColumn(name = "ACCOUNT_CHARGE_ID")
    private AcAccountCharge accountCharge;

    @NotNull
    @ManyToOne(targetEntity = AcReceiptImpl.class)
    @JoinColumn(name = "RECEIPT_ID")
    private AcReceipt receipt;

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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    @Override
    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    @Override
    public BigDecimal getAppliedAmount() {
        return appliedAmount;
    }

    @Override
    public void setAppliedAmount(BigDecimal appliedAmount) {
        this.appliedAmount = appliedAmount;
    }

    @Override
    public BigDecimal getAdjustedAmount() {
        return adjustedAmount;
    }

    @Override
    public void setAdjustedAmount(BigDecimal adjustedAmount) {
        this.adjustedAmount = adjustedAmount;
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
    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public Integer getUnit() {
        return unit;
    }

    @Override
    public void setUnit(Integer unit) {
        this.unit = unit;
    }
   
    @Override
    public AcAccountCharge getAccountCharge() {
		return accountCharge;
	}

    @Override
	public void setAccountCharge(AcAccountCharge accountCharge) {
		this.accountCharge = accountCharge;
	}

	@Override
    public AcReceipt getReceipt() {
        return receipt;
    }

    @Override
    public void setReceipt(AcReceipt receipt) {
        this.receipt = receipt;
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
        return AcReceiptAccountChargeItem.class;
    }
}
