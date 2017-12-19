package my.edu.umk.pams.account.billing.event;

import my.edu.umk.pams.account.billing.model.AcInvoice;
import my.edu.umk.pams.connector.payload.AccountPayload;

import org.springframework.context.ApplicationEvent;

/**
 * @author PAMS
 */
public abstract class InvoiceEvent extends ApplicationEvent {

//    private AcInvoice invoice;
//
//    public InvoiceEvent(AcInvoice invoice) {
//        super(invoice);
//        this.invoice = invoice;
//    }
//
//    public AcInvoice getInvoice() {
//        return invoice;
//    }
//
//    public void setInvoice(AcInvoice invoice) {
//        this.invoice = invoice;
//    }

	private AccountPayload payload;
	
	public InvoiceEvent(AccountPayload payload){
		super(payload);
		this.payload = payload;
	}

	public AccountPayload getPayload() {
		return payload;
	}

	public void setPayload(AccountPayload payload) {
		this.payload = payload;
	}
	
	
	
}
