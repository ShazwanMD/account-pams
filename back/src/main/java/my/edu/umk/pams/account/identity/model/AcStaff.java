package my.edu.umk.pams.account.identity.model;

public interface AcStaff extends AcActor {

    String getStaffNo();
    
    String getStaffDeptCode();
    
    String getStaffCategory();

    void setStaffNo(String staffNo);
    
    void setStaffDeptCode(String staffDeptCode);

    void setStaffCategory(String staffCategory);
}
