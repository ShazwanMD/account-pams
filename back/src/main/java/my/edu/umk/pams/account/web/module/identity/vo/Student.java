package my.edu.umk.pams.account.web.module.identity.vo;

import my.edu.umk.pams.account.web.module.common.vo.CohortCode;
import my.edu.umk.pams.account.web.module.common.vo.ResidencyCode;

import java.util.List;

/**
 * @author PAMS
 */
public class Student extends Actor {

    ResidencyCode residencyCode;
    CohortCode cohortCode;
    List<Sponsorship> sponsorships;

    public ResidencyCode getResidencyCode() {
        return residencyCode;
    }

    public void setResidencyCode(ResidencyCode residencyCode) {
        this.residencyCode = residencyCode;
    }

    public CohortCode getCohortCode() {
        return cohortCode;
    }

    public void setCohortCode(CohortCode cohortCode) {
        this.cohortCode = cohortCode;
    }

    public List<Sponsorship> getSponsorships() {
        return sponsorships;
    }

    public void setSponsorships(List<Sponsorship> sponsorships) {
        this.sponsorships = sponsorships;
    }
}
