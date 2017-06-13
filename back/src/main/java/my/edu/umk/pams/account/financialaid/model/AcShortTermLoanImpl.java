package my.edu.umk.pams.account.financialaid.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import my.edu.umk.pams.account.account.model.AcAcademicSession;
import my.edu.umk.pams.account.account.model.AcAcademicSessionImpl;
import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcShortTermLoan")
@Table(name = "AC_STL")
public class AcShortTermLoanImpl implements AcShortTermLoan{

    @Id
    @GeneratedValue(generator = "SQ_AC_STL")
    @SequenceGenerator(name = "SQ_AC_STL", sequenceName = "SQ_AC_STL", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    
    @NotNull
    @Column(name = "REFERENCE_NO", unique = true, nullable = false)
    private String referenceNo;

    @Column(name = "SOURCE_NO", unique = true)
    private String sourceNo;
    
    @NotNull
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;
    
    @NotNull
    @OneToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID")
    private AcAcademicSession session;
    
    @NotNull
    @OneToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

    @Column(name = "AMOUNT", nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;
 
    @Column(name = "STL_STATUS", nullable = false)
    private AcShortTermLoanStatus status = AcShortTermLoanStatus.NEW;
    
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
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
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

    public void setAccount(AcAccount account) {
        this.account = account;
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
    public AcShortTermLoanStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(AcShortTermLoanStatus status) {
        this.status = status;
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
        return AcShortTermLoan.class;
    }
}
