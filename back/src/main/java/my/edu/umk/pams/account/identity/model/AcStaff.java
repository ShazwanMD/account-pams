package my.edu.umk.pams.account.identity.model;

public interface AcStaff extends AcActor {

    String getStaffNo();
    
    String getStaffDeptCode();

    void setStaffNo(String staffNo);
    
    void setStaffDeptCode(String staffDeptCode);

}
