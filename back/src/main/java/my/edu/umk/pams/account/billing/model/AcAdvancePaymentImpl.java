package my.edu.umk.pams.account.billing.model;

import java.math.BigDecimal;
import java.util.Date;

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

import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;

@Entity(name = "AcAdvancePayment")
@Table(name = "AC_ADVC_PYMT")
public class AcAdvancePaymentImpl implements AcAdvancePayment{

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_ADVC_PYMT")
    @SequenceGenerator(name = "SQ_AC_ADVC_PYMT", sequenceName = "SQ_AC_ADVC_PYMT", allocationSize = 1)
    private Long id;

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

    @Column(name = "AMOUNT")
    private BigDecimal amount = BigDecimal.ZERO;

    @NotNull
    @Column(name = "ISSUED_DATE")
    private Date issuedDate;
    
    @ManyToOne(targetEntity = AcInvoiceImpl.class)
    @JoinColumn(name = "INVOICE_ID")
    private AcInvoice invoice;
    
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
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public Date getIssuedDate() {
        return issuedDate;
    }

    @Override
    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
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
        return AcAdvancePayment.class;
    }

    
}