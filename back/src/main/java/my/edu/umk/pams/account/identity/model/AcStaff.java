package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.common.model.AcFacultyCode;

public interface AcStaff extends AcActor {

    String getStaffNo();
    
    String getStaffDeptCode();
    
    String getStaffCategory();

    void setStaffNo(String staffNo);
    
    void setStaffDeptCode(String staffDeptCode);

    void setStaffCategory(String staffCategory);
    
    AcFacultyCode getFacultyCode();

    void setFacultyCode(AcFacultyCode facultyCode);
    
    AcStaffType getStaffType();

    void setStaffType(AcStaffType staffType);
}
