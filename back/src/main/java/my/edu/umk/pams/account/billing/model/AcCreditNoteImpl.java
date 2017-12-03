package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;
import my.edu.umk.pams.account.account.model.AcChargeCodeImpl;
import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author PAMS
 */
@Entity(name = "AcCreditNote")
@Table(name = "AC_CDIT_NOTE")
public class AcCreditNoteImpl implements AcCreditNote {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_CDIT_NOTE")
    @SequenceGenerator(name = "SQ_AC_CDIT_NOTE", sequenceName = "SQ_AC_CDIT_NOTE", allocationSize = 1)
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

    @Column(name = "TOTAL_AMOUNT")
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @NotNull
    @Column(name = "ISSUED_DATE")
    private Date issuedDate;
    
//  @NotNull
    @Column(name = "CREDITNOTE_DATE")
    private Date creditNoteDate;

    @Column(name = "CANCEL_COMMENT")
    private String cancelComment;

    @Column(name = "REMOVE_COMMENT")
    private String removeComment;

    @OneToMany(targetEntity = AcCreditNoteItemImpl.class, mappedBy = "creditNote")
    private List<AcCreditNoteItem> items;

    @ManyToOne(targetEntity = AcInvoiceImpl.class)
    @JoinColumn(name = "INVOICE_ID")
    private AcInvoice invoice;
    
/*    @ManyToOne(targetEntity = AcChargeCodeImpl.class)
    @JoinColumn(name = "CHARGE_CODE_ID")
    private AcChargeCode chargeCode;*/

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
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    @Override
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
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
    public Date getCreditNoteDate() {
        return creditNoteDate;
    }

    @Override
    public void setCreditNoteDate(Date creditNoteDate) {
        this.creditNoteDate = creditNoteDate;
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
    public List<AcCreditNoteItem> getItems() {
        return items;
    }

    @Override
    public void setItems(List<AcCreditNoteItem> items) {
        this.items = items;
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
    public AcFlowdata getFlowdata() {
        return flowdata;
    }

    @Override
    public void setFlowdata(AcFlowdata flowdata) {
        this.flowdata = flowdata;
    }
    
/*    @Override
    public AcChargeCode getChargeCode(){
        return chargeCode;
    };

    @Override
    public void setChargeCode(AcChargeCode chargeCode) {
        this.chargeCode = chargeCode;
    };*/

    @Override
    public Class<?> getInterfaceClass() {
        return AcCreditNote.class;
    }
}
