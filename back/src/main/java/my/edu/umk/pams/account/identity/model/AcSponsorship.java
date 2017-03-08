package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.core.AcMetaObject;

/**
 * ?? todo: coverage
 *
 * @author PAMS
 */
public interface AcSponsorship extends AcMetaObject {

    AcSponsor getSponsor();

    void setSponsor(AcSponsor sponsor);

    AcStudent getStudent();

    void setStudent(AcStudent student);
}
 