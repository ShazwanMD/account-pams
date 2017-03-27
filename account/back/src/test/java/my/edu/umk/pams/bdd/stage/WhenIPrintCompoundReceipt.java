package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class WhenIPrintCompoundReceipt extends Stage<WhenIPrintCompoundReceipt> {
	
	public WhenIPrintCompoundReceipt I_print_compound_receipt(){
		
		
		return self();
	}

	public WhenIPrintCompoundReceipt I_print_compound_receipt_for_studentNo_$(String studentNo) {
		return null;
	}
}
