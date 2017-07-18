package my.edu.umk.pams.account.web.module.core.vo;

import java.math.BigDecimal;
import java.util.Date;

import my.edu.umk.pams.account.web.module.billing.vo.ReceiptType;
import my.edu.umk.pams.account.web.module.common.vo.PaymentMethod;

public class Task {

    private Long id;
    private String taskId;
    private String taskName;
    private String referenceNo;
    private String sourceNo;
    private String description;
    private String candidate;
    private String assignee;
    private BigDecimal totalAmount = new BigDecimal(0.00);
    private FlowState flowState;
    private MetaState metaState;
    
    private Date receivedDate;
    private PaymentMethod paymentMethod;
    private ReceiptType receiptType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCandidate() {
        return candidate;
    }

    public void setCandidate(String candidate) {
        this.candidate = candidate;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }
    

    public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	
	public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
	public ReceiptType getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(ReceiptType receiptType) {
        this.receiptType = receiptType;
    }

	public FlowState getFlowState() {
        return flowState;
    }

    public void setFlowState(FlowState flowState) {
        this.flowState = flowState;
    }

    public MetaState getMetaState() {
        return metaState;
    }

    public void setMetaState(MetaState metaState) {
        this.metaState = metaState;
    }
}
