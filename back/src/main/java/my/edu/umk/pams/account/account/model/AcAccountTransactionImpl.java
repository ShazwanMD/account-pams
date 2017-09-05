package my.edu.umk.pams.account.account.model;

import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
@Entity(name = "AcAccountTransaction")
@Table(name = "AC_ACCT_TRSN")
public class AcAccountTransactionImpl implements AcAccountTransaction {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_ACCT_TRSN")
    @SequenceGenerator(name = "SQ_AC_ACCT_TRSN", sequenceName = "SQ_AC_ACCT_TRSN", allocationSize = 1)
    private Long id;

    @Column(name = "SOURCE_NO", nullable = false)
    private String sourceNo;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;
    
    @NotNull
    @Column(name = "BALANCE_AMOUNT", nullable = false)
    private BigDecimal balanceAmount = BigDecimal.ZERO;
    
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @NotNull
    @Column(name = "POSTED_DATE", nullable = false)
    private Date postedDate;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TRANSACTION_CODE", nullable = false)
    private AcAccountTransactionCode transactionCode;

    //@NotNull
    @ManyToOne(targetEntity = AcChargeCodeImpl.class)
    @JoinColumn(name = "CHARGE_CODE_ID", nullable = true)
    private AcChargeCode chargeCode;

    @NotNull
    @OneToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID", nullable = false)
    private AcAcademicSession session;

    @NotNull
    @ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

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
    public String getSourceNo() {
        return sourceNo;
    }

    @Override
    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    @Override
    public Date getPostedDate() {
        return postedDate;
    }

    @Override
    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
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
    public AcChargeCode getChargeCode() {
        return chargeCode;
    }

    @Override
    public void setChargeCode(AcChargeCode chargeCode) {
        this.chargeCode = chargeCode;
    }

    @Override
    public AcAcademicSession getSession() {
        return session;
    }

    @Override
    public void setSession(AcAcademicSession session) {
        this.session = session;
    }

    @Override
    public AcAccount getAccount() {
        return account;
    }

    @Override
    public void setAccount(AcAccount account) {
        this.account = account;
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
        return AcAccountTransaction.class;
    }
}
