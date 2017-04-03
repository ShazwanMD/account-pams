package my.edu.umk.pams.account.web.module.identity.vo;

import java.util.List;

/**
 * @author PAMS
 */
public class Student extends Actor {

    List<Sponsorship> sponsorships;

    public List<Sponsorship> getSponsorships() {
        return sponsorships;
    }

    public void setSponsorships(List<Sponsorship> sponsorships) {
        this.sponsorships = sponsorships;
    }
}
