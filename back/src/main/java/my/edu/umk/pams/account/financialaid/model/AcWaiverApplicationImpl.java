package my.edu.umk.pams.account.financialaid.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.identity.model.AcStudentImpl;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.math.BigDecimal;


@Entity(name = "AcWaiverApplication")
@Table(name = "AC_WAVR_APLN")
public class AcWaiverApplicationImpl implements AcWaiverApplication {

    @Id
    @GeneratedValue(generator = "SQ_AC_WAVR_APLN")
    @SequenceGenerator(name = "SQ_AC_WAVR_APLN", sequenceName = "SQ_AC_WAVR_APLN", allocationSize = 1)
    @Column(name = "ID")
    private Long id;


    @NotNull
    @ManyToOne(targetEntity = AcStudentImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;
    
    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @Column(name = "AUDIT_NO")
    private String auditNo;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;
    
    @NotNull
    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance;
    
    @NotNull
    @Column(name = "EFFECTIVE_BALANCE", nullable = false)
    private BigDecimal effectiveBalance;
    
    @NotNull
    @Column(name = "WAIVED_AMOUNT", nullable = false)
    private BigDecimal waivedAmount;
    
    @NotNull
    @Column(name = "GRACED_AMOUNT", nullable = false)
    private BigDecimal gracedAmount;
    
    @Column(name = "MEMO")
    private String memo;
    
    @Column(name = "REASON")
    private String reason;
    
    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @Column(name = "REMOVE_COMMENT")
    private String removeComment;

    @Embedded
    private AcMetadata metadata;
    
    @Embedded
    private AcFlowdata flowdata;

    @Override
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
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
    public String getReferenceNo() {
        return referenceNo;
    }

    @Override
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
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
    public String getAuditNo() {
        return auditNo;
    }

    @Override
    public void setAuditNo(String auditNo) {
        this.auditNo = auditNo;
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
    public BigDecimal getBalance() {
        return balance;
    }
    
    @Override
    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
    
    @Override
    public BigDecimal getEffectiveBalance() {
        return effectiveBalance;
    }
    
    @Override
    public void setEffectiveBalance(BigDecimal effectiveBalance) {
        this.effectiveBalance = effectiveBalance;
    }
    
    @Override
    public BigDecimal getWaivedAmount() {
        return gracedAmount;
    }
    
    @Override
    public void setWaivedAmount(BigDecimal waivedAmount) {
        this.waivedAmount = waivedAmount;
    }
    
    @Override
    public BigDecimal getGracedAmount() {
        return gracedAmount;
    }
    
    @Override
    public void setGracedAmount(BigDecimal gracedAmount) {
        this.gracedAmount = gracedAmount;
    }
    

    @Override
    public String getMemo() {
        return memo;
    }
    
    @Override
    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
    }
    
    @Override
    public String getReason() {
        return reason;
    }
    
    @Override
    public String getCancelComment() {
        return cancelComment;
    }

    @Override
    public void setCancelComment(String cancelComment) {
        this.cancelComment = cancelComment;
    }

    @Override
    public String getRemoveComment() {
        return removeComment;
    }

    @Override
    public void setRemoveComment(String removeComment) {
        this.removeComment = removeComment;
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
    public AcFlowdata getFlowdata() {
        return flowdata;
    }

    @Override
    public void setFlowdata(AcFlowdata flowdata) {
        this.flowdata = flowdata;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcWaiverApplication.class;
    }

  
}
