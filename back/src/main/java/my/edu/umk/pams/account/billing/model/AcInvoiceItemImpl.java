package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.model.AcChargeCodeImpl;
import my.edu.umk.pams.account.common.model.AcTaxCode;
import my.edu.umk.pams.account.common.model.AcTaxCodeImpl;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
@Entity(name = "AcInvoiceItem")
@Table(name = "AC_INVC_ITEM")
public class AcInvoiceItemImpl implements AcInvoiceItem {

    @Id
    @GeneratedValue(generator = "SQ_AC_INVC_ITEM")
    @SequenceGenerator(name = "SQ_AC_INVC_ITEM", sequenceName = "SQ_AC_INVC_ITEM", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount = BigDecimal.ZERO;
    
    @Column(name = "TAX_AMOUNT")
    private BigDecimal taxAmount = BigDecimal.ZERO;
    
    @Column(name = "NET_AMOUNT")
    private BigDecimal netAmount = BigDecimal.ZERO;

    @NotNull
    @ManyToOne(targetEntity = AcChargeCodeImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "CHARGE_CODE_ID")
    private AcChargeCode chargeCode;

    @NotNull
    @ManyToOne(targetEntity = AcInvoiceImpl.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "INVOICE_ID")
    private AcInvoice invoice;

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
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    @Override
    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }
    
    @Override
    public BigDecimal getTaxAmount() {
		return taxAmount;
	}

    @Override
    public void setTaxAmount(BigDecimal taxAmount) {
		this.taxAmount = taxAmount;
	}

    @Override
    public BigDecimal getNetAmount() {
		return netAmount;
	}

    @Override
    public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
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
    public AcInvoice getInvoice() {
        return invoice;
    }

    @Override
    public void setInvoice(AcInvoice invoice) {
        this.invoice = invoice;
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
        return AcInvoiceItem.class;
    }
}
