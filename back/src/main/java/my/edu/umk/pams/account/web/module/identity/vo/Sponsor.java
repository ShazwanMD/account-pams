package my.edu.umk.pams.account.web.module.identity.vo;

import java.util.List;

import my.edu.umk.pams.account.web.module.account.vo.Sponsorship;


/**
 * @author PAMS
 */
public class Sponsor extends Actor {

    List<Sponsorship> sponsorships;


    public List<Sponsorship> getSponsorships() {
        return sponsorships;
    }

    public void setSponsorships(List<Sponsorship> sponsorships) {
        this.sponsorships = sponsorships;
    }
}