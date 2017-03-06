package my.edu.umk.pams.account.billing.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



@Entity(name = "AcRcpt")
@Table(name = "AC_RCPT")
public abstract class AcReceiptImpl implements AcReceipt {
   
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(generator = "SQ_AC_RCPT")
    
    @SequenceGenerator(name = "SQ_AC_RCPT", sequenceName = "SQ_AC_RCPT", allocationSize = 1)
    private Long id;
    
	@Column(name = "RECEIPT_NO")
    private String receiptNo;

    @Column(name = "DESCRIPTION")
    private String description;


    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getReceiptNo() {
		return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
		this.receiptNo = receiptNo;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String getReceiptNumber() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setReceiptNumber(String receiptnumber) {
		// TODO Auto-generated method stub
		
	}

 
}
