package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcAccount;
import my.edu.umk.pams.account.account.model.AcAccountImpl;
import my.edu.umk.pams.account.common.model.AcPaymentMethod;
import my.edu.umk.pams.account.core.AcFlowdata;
import my.edu.umk.pams.account.core.AcMetadata;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity(name = "AcReceipt")
@Table(name = "AC_RCPT")
@Inheritance(strategy = InheritanceType.JOINED)
public class AcReceiptImpl implements AcReceipt {

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(generator = "SQ_AC_RCPT")
	@SequenceGenerator(name = "SQ_AC_RCPT", sequenceName = "SQ_AC_RCPT", allocationSize = 1)
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

	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "RECEIPT_TYPE")
	private AcReceiptType receiptType;

	@Enumerated(value = EnumType.ORDINAL)
	@Column(name = "PAYMENT_METHOD")
	private AcPaymentMethod paymentMethod;

	@NotNull
	@Column(name = "TOTAL_APPLIED")
	private BigDecimal totalApplied = BigDecimal.ZERO;

	@NotNull
	@Column(name = "TOTAL_RECEIVED")
	private BigDecimal totalReceived = BigDecimal.ZERO;

	@NotNull
	@Column(name = "TOTAL_AMOUNT")
	private BigDecimal totalAmount = new BigDecimal(0.00);

	@NotNull
	@Column(name = "RECEIVED_DATE")
	private Date receivedDate;

	@ManyToOne(targetEntity = AcAccountImpl.class)
	@JoinColumn(name = "ACCOUNT_ID")
	private AcAccount account;

	@Column(name = "CANCEL_COMMENT")
	private String cancelComment;

	@Column(name = "REMOVE_COMMENT")
	private String removeComment;

	@OneToMany(targetEntity = AcReceiptItemImpl.class, mappedBy = "receipt")
	private List<AcReceiptItem> items;
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity = AcInvoiceImpl.class)
    @JoinTable(name = "AC_RCPT_INVC", joinColumns = {
            @JoinColumn(name = "RECEIPT_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "INVOICE_ID",
                    nullable = false, updatable = false)})
    private Set<AcInvoice> invoices;

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
	public String getReceiptNo() {
		return receiptNo;
	}

	@Override
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
	public AcReceiptType getReceiptType() {
		return receiptType;
	}

	@Override
	public void setReceiptType(AcReceiptType receiptType) {
		this.receiptType = receiptType;
	}

	@Override
	public AcPaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	@Override
	public void setPaymentMethod(AcPaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	@Override
	public BigDecimal getTotalApplied() {
		return totalApplied;
	}

	@Override
	public void setTotalApplied(BigDecimal totalApplied) {
		this.totalApplied = totalApplied;
	}

	@Override
	public BigDecimal getTotalReceived() {
		return totalReceived;
	}

	@Override
	public void setTotalReceived(BigDecimal totalReceived) {
		this.totalReceived = totalReceived;
	}

	@Override
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	@Override
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	public AcAccount getAccount() {
		return account;
	}

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
	public List<AcReceiptItem> getItems() {
		return items;
	}

	@Override
	public void setItems(List<AcReceiptItem> items) {
		this.items = items;
	}
	
	@Override
	public Set<AcInvoice> getInvoices() {
		return invoices;
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
		return AcReceipt.class;
	}

}
