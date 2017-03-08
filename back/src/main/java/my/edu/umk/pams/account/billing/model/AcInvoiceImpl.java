package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
@Entity(name = "AcInvoice")
@Table(name = "AC_INVC")
public class AcInvoiceImpl implements AcInvoice {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_INVC")
    @SequenceGenerator(name = "SQ_AC_INVC", sequenceName = "SQ_AC_INVC", allocationSize = 1)
    private Long id;

    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Column(name = "RECEIPT_NO")
    private String receiptNo;

    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @Column(name = "AUDIT_NO")
    private String auditNo;

    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @NotNull
    @Column(name = "ISSUED_DATE")
    private Date issuedDate;

    @ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @Column(name = "REMOVE_COMMENT")
    private String removeComment;

    @OneToMany(targetEntity = AcInvoiceItemImpl.class, mappedBy = "invoice")
    private List<AcInvoiceItem> items;

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

    public String getReceiptNo() {
        return receiptNo;
    }

    public void setReceiptNo(String receiptNo) {
        this.receiptNo = receiptNo;
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
    public Date getIssuedDate() {
        return issuedDate;
    }

    @Override
    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
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
    public List<AcInvoiceItem> getItems() {
        return items;
    }

    @Override
    public void setItems(List<AcInvoiceItem> items) {
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
        return AcInvoice.class;
    }

    @Override
    public AcFlowdata getFlowdata() {
        return flowdata;
    }

    @Override
    public void setFlowdata(AcFlowdata flowdata) {
        this.flowdata = flowdata;
    }
}
