package my.edu.umk.pams.account.identity.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Entity(name = "AcApplication")
@Table(name = "AC_APCN")
public class AcStudentImpl extends AcActorImpl implements AcStudent {

    @Override
    public String getApplicationNo() {
        return getIdentityNo();
    }

    @Override
    public void setApplicationNo(String applicationNo) {
        setIdentityNo(applicationNo);
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcStudent.class;
    }

}
