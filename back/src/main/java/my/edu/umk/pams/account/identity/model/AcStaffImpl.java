package my.edu.umk.pams.account.identity.model;

import javax.persistence.Entity;
import javax.persistence.Table;

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
	public String getStaffDeptCode() {
		// TODO Auto-generated method stub
		return getStaffDeptCode();
	}

	@Override
	public void setStaffDeptCode(String staffDeptCode) {
		// TODO Auto-generated method stub
		setStaffDeptCode(staffDeptCode);
	}

}
