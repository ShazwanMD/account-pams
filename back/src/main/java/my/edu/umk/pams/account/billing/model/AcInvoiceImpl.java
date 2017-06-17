package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.*;
import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
@Indexed
@Entity(name = "AcInvoice")
@Table(name = "AC_INVC")
public class AcInvoiceImpl implements AcInvoice {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_INVC")
    @SequenceGenerator(name = "SQ_AC_INVC", sequenceName = "SQ_AC_INVC", allocationSize = 1)
    private Long id;

    @Field
    @NotNull
    @Column(name = "REFERENCE_NO")
    private String referenceNo;

    @Field
    @Column(name = "INVOICE_NO")
    private String invoiceNo;

    @Field
    @Column(name = "SOURCE_NO")
    private String sourceNo;

    @Field
    @Column(name = "AUDIT_NO")
    private String auditNo;

    @Field
    @NotNull
    @Column(name = "DESCRIPTION")
    private String description;

    @Field
    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Field
    @Column(name = "BALANCE_AMOUNT")
    private BigDecimal balanceAmount = BigDecimal.ZERO;

    @Field
    @Column(name = "PAID")
    private boolean paid = false;

    @Field
    @NotNull
    @Column(name = "ISSUED_DATE")
    private Date issuedDate;

    @Field
    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @Field
    @Column(name = "REMOVE_COMMENT")
    private String removeComment;

    @IndexedEmbedded
    @ManyToOne(targetEntity = AcAccountImpl.class)
    @JoinColumn(name = "ACCOUNT_ID")
    private AcAccount account;

    @IndexedEmbedded
    @NotNull
    @OneToOne(targetEntity = AcAcademicSessionImpl.class)
    @JoinColumn(name = "SESSION_ID")
    private AcAcademicSession session;

    @IndexedEmbedded
    @OneToMany(targetEntity = AcInvoiceItemImpl.class, mappedBy = "invoice")
    private List<AcInvoiceItem> items;

    @IndexedEmbedded
    @OneToMany(targetEntity = AcAccountChargeImpl.class, mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<AcAccountCharge> charges;

    @IndexedEmbedded
    @OneToMany(targetEntity = AcDebitNoteImpl.class, mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<AcDebitNote> debitNotes;

    @IndexedEmbedded
    @OneToMany(targetEntity = AcCreditNoteImpl.class, mappedBy = "invoice", fetch = FetchType.LAZY)
    private List<AcCreditNote> creditNotes;

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

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
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
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
    public boolean isPaid() {
        return paid;
    }

    @Override
    public void setPaid(boolean paid) {
        this.paid = paid;
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
    public AcAcademicSession getSession() {
        return session;
    }

    @Override
    public void setSession(AcAcademicSession session) {
        this.session = session;
    }

    public List<AcAccountCharge> getCharges() {
        return charges;
    }

    public void setCharges(List<AcAccountCharge> charges) {
        this.charges = charges;
    }

    @Override
    public List<AcDebitNote> getDebitNotes() {
        return debitNotes;
    }

    @Override
    public void setDebitNotes(List<AcDebitNote> debitNotes) {
        this.debitNotes = debitNotes;
    }

    @Override
    public List<AcCreditNote> getCreditNotes() {
        return creditNotes;
    }

    @Override
    public void setCreditNotes(List<AcCreditNote> creditNotes) {
        this.creditNotes = creditNotes;
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
        return AcInvoice.class;
    }

}
