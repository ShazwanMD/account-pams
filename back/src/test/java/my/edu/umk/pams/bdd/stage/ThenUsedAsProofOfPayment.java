package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenUsedAsProofOfPayment extends Stage<ThenUsedAsProofOfPayment> {

	public ThenUsedAsProofOfPayment Used_as_proof_of_payment(){
		return self();
	}
}
