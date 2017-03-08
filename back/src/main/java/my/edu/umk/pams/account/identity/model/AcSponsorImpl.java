package my.edu.umk.pams.account.identity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "AcSponsor")
@Table(name = "AC_SPONSOR")
public class AcSponsorImpl extends AcActorImpl implements AcSponsor {

    @Column(name = "CODE_SPONSOR")
    private String codeSponsor;

    public AcSponsorImpl() {
        setActorType(AcActorType.SPONSOR);
    }

    @Override
    public String getCodeSponsor() {
        return codeSponsor;
    }

    @Override
    public void setCodeSponsor(String codeSponsor) {
        this.codeSponsor = codeSponsor;
    }

    @Override
    public Class<?> getInterfaceClass() {
        return AcSponsor.class;
    }
}
