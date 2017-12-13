package my.edu.umk.pams.account.common.model;

import java.util.List;

import my.edu.umk.pams.account.core.AcMetaObject;

public interface AcProgramCode extends AcMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    AcFacultyCode getFacultyCode();

    void setFacultyCode(AcFacultyCode facultyCode);
    
    List<AcCohortCode> getCohortCode();
    
    void setCohortCode(List<AcCohortCode> cohortCode);
    
    AcStudyCenterCode getStudyCenterCode();

	void setStudyCenterCode(AcStudyCenterCode studyCenterCode);
}
