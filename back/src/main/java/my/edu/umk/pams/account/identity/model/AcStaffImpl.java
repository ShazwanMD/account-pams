package my.edu.umk.pams.account.identity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import my.edu.umk.pams.account.common.model.AcFacultyCode;
import my.edu.umk.pams.account.common.model.AcFacultyCodeImpl;

/**
 * @author canang technologies
 * @since 7/2/2015.
 */
@Entity(name = "AcStaff")
@Table(name = "AC_STAF")
public class AcStaffImpl extends AcActorImpl implements AcStaff {

    public AcStaffImpl() {
        super();
        setActorType(AcActorType.STAFF);
    }
    
    @OneToOne(targetEntity = AcFacultyCodeImpl.class)
	@JoinColumn(name = "FACULTY_ID")
	private AcFacultyCode facultyCode;

    @Column(name = "STAFF_TYPE")
	private AcStaffType staffType;
    
	@Column(name = "STAFF_CATEGORY")
	private String staffCategory;
	
	@Column(name = "DEPT_CODE")
	private String staffDeptCode;
    
    @Override
	public AcFacultyCode getFacultyCode() {
		return facultyCode;
	}

    @Override
	public void setFacultyCode(AcFacultyCode facultyCode) {
		this.facultyCode = facultyCode;
	}
    
    @Override
	public AcStaffType getStaffType() {
		return staffType;
	}

    @Override
	public void setStaffType(AcStaffType staffType) {
		this.staffType = staffType;
	}
    
    @Override
    public String getStaffNo() {
        return getIdentityNo();
    }

    @Override
    public void setStaffNo(String staffNo) {
        setIdentityNo(staffNo);
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcStaff.class;
    }


	@Override
	public String getStaffCategory() {
		return staffCategory;
	}
	@Override
	public void setStaffCategory(String staffCategory) {
		this.staffCategory = staffCategory;
	}

	public String getStaffDeptCode() {
		return staffDeptCode;
	}

	public void setStaffDeptCode(String staffDeptCode) {
		this.staffDeptCode = staffDeptCode;
	}
	
	

	
}
