package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.Pending;
import com.tngtech.jgiven.integration.spring.JGivenStage;

@JGivenStage
public class WhenIWantGiveCompoundsDiscountStudent extends Stage <WhenIWantGiveCompoundsDiscountStudent> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenIWantGiveCompoundsDiscountStudent.class);

	@Pending
	@As("I want to give compounds discount to student")
	public WhenIWantGiveCompoundsDiscountStudent I_want_to_give_compounds_discount_to_student_$(String code){
		
		return self();
	}
}
