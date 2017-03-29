package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;

public class ThenICanViewIndividualInvoice extends Stage <ThenICanViewIndividualInvoice> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateInvoiceIndividual.class);
	
	public ThenICanViewIndividualInvoice I_can_view_individual_invoice(){
		return self();
	}

}
