package my.edu.umk.pams.account.common.model;

public interface AcCohortCode extends my.edu.umk.pams.account.core.AcMetaObject {

    String getCode();

    void setCode(String code);

    String getDescription();

    void setDescription(String description);

    AcProgramCode getProgramCode();

    void setProgramCode(AcProgramCode programCode);

    // todo(hajar): link ke student
//    List<AcStudent> getStudent();
//
//	void setStudent(List<AcStudent> student);
}
