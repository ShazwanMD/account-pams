package my.edu.umk.pams.account.identity.model;

/**
 * ?? todo: coverage
 *
 * @author PAMS
 */
public interface AcSponsorship {


    Long getId();

    void setId(Long id);

    AcSponsor getSponsor();

    void setSponsor(AcSponsor sponsor);

    AcStudent getStudent();

    void setStudent(AcStudent student);
}
 