package my.edu.umk.pams.account.identity.model;

import java.util.List;

import my.edu.umk.pams.account.common.model.AcFacultyCode;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcStudent extends AcActor {

    String getMatricNo();

    void setMatricNo(String matricNo);

    List<AcSponsorship> getSponsorships();

    void setSponsorships(List<AcSponsorship> sponsorships);
    
    AcFacultyCode getFacultyCode();
    
    void setFacultyCode(AcFacultyCode facultyCode);
}
