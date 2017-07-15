package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.account.web.module.core.vo.Document;

public class AdvancePayment extends Document {

	    private String referenceNo;
	    private String sourceNo;
	    private String auditNo;
	    private String description;
	    private BigDecimal amount = BigDecimal.ZERO;
	    private Date issuedDate;
	    private Invoice invoice;
	    
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

		public String getAuditNo() {
			return auditNo;
		}

		public void setAuditNo(String auditNo) {
			this.auditNo = auditNo;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public BigDecimal getAmount() {
			return amount;
		}

		public void setAmount(BigDecimal amount) {
			this.amount = amount;
		}

		public Invoice getInvoice() {
			return invoice;
		}

		public void setInvoice(Invoice invoice) {
			this.invoice = invoice;
		}
		
		@JsonCreator
	    public static AdvancePayment create(String jsonString) {
			AdvancePayment o = null;
	        try {
	            ObjectMapper mapper = new ObjectMapper();
	            o = mapper.readValue(jsonString, AdvancePayment.class);
	        } catch (IOException e) {
	            // handle
	        }
	        return o;
	    }

}
