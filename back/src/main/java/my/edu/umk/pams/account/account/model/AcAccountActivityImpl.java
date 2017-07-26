package my.edu.umk.pams.account.account.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.core.AcMetadata;

/**
 */
@Entity(name = "AcAccountActivity")
@Table(name = "AC_ACCT_ACTV")
public class AcAccountActivityImpl implements AcAccountActivity {
	
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SEQ_ACCT_ACTV")
    @SequenceGenerator(name = "SEQ_ACCT_ACTV", sequenceName = "SEQ_ACCT_ACTV", allocationSize = 1)
    private Long id;
    
    @Column(name = "SOURCE_NO", nullable = false)
    private String sourceNo;
    
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TRANSACTION_CODE", nullable = false)
    private AcAccountTransactionCode transactionCode;
    
    @NotNull
    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private BigDecimal totalAmount;

    @Transient
    private Integer transactionCodeOrdinal;
    
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
    public String getSourceNo() {
        return sourceNo;
    }

    @Override
    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    @Override
    public AcAccountTransactionCode getTransactionCode() {
        return transactionCode;
    }

    @Override
    public void setTransactionCode(AcAccountTransactionCode transactionCode) {
        this.transactionCode = transactionCode;
    }

    @Override
    public Integer getTransactionCodeOrdinal() {
        return transactionCodeOrdinal;
    }

    @Override
    public void setTransactionCodeOrdinal(Integer transactionCodeOrdinal) {
        this.transactionCodeOrdinal = transactionCodeOrdinal;
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
    public AcMetadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(AcMetadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcAccountActivity.class;
    }
   
}
