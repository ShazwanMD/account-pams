package my.edu.umk.pams.account.identity.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author canang technologies
 * @since 1/31/14
 */
@Entity(name = "AcStudent")
@Table(name = "AC_STDN")
public class AcStudentImpl extends AcActorImpl implements AcStudent {

    public AcStudentImpl() {
        setActorType(AcActorType.STUDENT);
    }

    @Override
    public String getMatricNo() {
        return getIdentityNo();
    }

    @Override
    public void setMatricNo(String matricNo) {
        setIdentityNo(matricNo);
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcStudent.class;
    }

}
