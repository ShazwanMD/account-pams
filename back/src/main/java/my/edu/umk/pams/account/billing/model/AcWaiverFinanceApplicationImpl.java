package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAcademicSessionImpl;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;
import my.edu.umk.pams.account.financialaid.model.AcWaiverApplication;

@Entity(name = "AcWaiverFinanceApplication")
@Table(name = "AC_WAVR_FNCE_APLN")
public class AcWaiverFinanceApplicationImpl implements AcWaiverFinanceApplication {

    @Id
    @GeneratedValue(generator = "SQ_AC_WAVR_FNCE_APLN")
    @SequenceGenerator(name = "SQ_AC_WAVR_FNCE_APLN", sequenceName = "SQ_AC_WAVR_FNCE_APLN", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;
    
    @NotNull
    @Column(name = "BALANCE", nullable = false)
    private BigDecimal balance = BigDecimal.ZERO;
    
    @NotNull
    @Column(name = "EFFECTIVE_BALANCE", nullable = false)
    private BigDecimal effectiveBalance = BigDecimal.ZERO;
    
    @NotNull
    @Column(name = "WAIVED_AMOUNT", nullable = false)
    private BigDecimal waivedAmount = BigDecimal.ZERO;
    
    @NotNull
    @Column(name = "GRACED_AMOUNT", nullable = false)
    private BigDecimal gracedAmount = BigDecimal.ZERO;

    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @Column(name = "AUDIT_NO")
    private String auditNo;

    @Column(name = "DESCRIPTION")
    private String description;
    
    @Column(name = "MEMO")
    private String memo;
    
    @Column(name = "REASON")
    private String reason;
    
    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @Column(name = "REMOVE_COMMENT")
    private String removeComment;

    @NotNull
    @ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

    @NotNull
    @OneToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID")
    private AcAcademicSession session;

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
    public String getReferenceNo() {
        return referenceNo;
    }

    @Override
    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
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
        return waivedAmount;
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
    public String getMemo() {
        return memo;
    }

    @Override
    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String getReason() {
        return reason;
    }

    @Override
    public void setReason(String reason) {
        this.reason = reason;
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
