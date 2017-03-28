package my.edu.umk.pams.account.account.stage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tngtech.jgiven.Stage;


public class TheCanViewStudentsCompoundCharges extends Stage <TheCanViewStudentsCompoundCharges> {
	
	private static final Logger LOG = LoggerFactory.getLogger(WhenWantToListAllCompoundStudent.class);

	public TheCanViewStudentsCompoundCharges can_view_students_compound_charges(){
		return self();
	}
}

