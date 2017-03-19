package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.Pending;

public class ThenCanGiveStudentInformationToBursary extends Stage <ThenCanGiveStudentInformationToBursary> {
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantUpdateStudentInformation.class);
	
	@Pending
	public ThenCanGiveStudentInformationToBursary can_give_student_information_to_Bursary(){
		return self();
	}

}
