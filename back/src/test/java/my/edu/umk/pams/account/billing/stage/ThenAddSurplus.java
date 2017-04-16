package my.edu.umk.pams.account.billing.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class ThenAddSurplus extends Stage <ThenAddSurplus> {
	private static final Logger LOG = LoggerFactory.getLogger(ThenAddSurplus.class);

	@As("Add surplus for student")
	public ThenAddSurplus Add_surplus_for_student_$(String matricNo) {
		// TODO Auto-generated method stub
		return self();
	}
}
