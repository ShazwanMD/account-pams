package my.edu.umk.pams.account.common.model;

import my.edu.umk.pams.account.identity.model.*;

import java.util.List;

public interface AcCohortCode extends my.edu.umk.pams.account.core.AcMetaObject {

	String getCode();

	void setCode(String code);

	String getDescription();

	void setDescription(String description);

	AcProgramCode getProgramCode();

	void setProgramCode(AcProgramCode programCode);

	List<AcStudent> getStudent();

	void setStudent(List<AcStudent> student);
}
