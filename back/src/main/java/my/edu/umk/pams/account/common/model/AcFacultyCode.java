package my.edu.umk.pams.account.common.model;

import java.util.List;

import my.edu.umk.pams.account.identity.model.AcStudent;

public interface AcFacultyCode extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    List<AcStudent> getStudent();

	void setStudent(List<AcStudent> student);
}
