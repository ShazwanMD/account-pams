package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class WhenIGenerateInvoiceIndividual extends Stage <WhenIGenerateInvoiceIndividual>{
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenIGenerateInvoiceIndividual.class);
	
	
	public WhenIGenerateInvoiceIndividual I_generate_invoice_by_individual$(String matric_No){
		return self();
	}
	

}
