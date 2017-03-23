package my.edu.umk.pams.account.identity.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.As;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import my.edu.umk.pams.account.identity.model.*;
import my.edu.umk.pams.account.identity.service.IdentityService;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@JGivenStage
public class WhenIAddASponsor extends Stage<WhenIAddASponsor> {

    private static final Logger LOG = LoggerFactory.getLogger(WhenIAddASponsor.class);

    @Autowired
    private IdentityService identityService;

    @Autowired
    private SessionFactory sessionFactory;

    @ProvidedScenarioState
    private AcSponsor sponsor;

    @ProvidedScenarioState
    private AcUser user;

    public WhenIAddASponsor() {
    }

    @As("I add sponsor user account")
    public WhenIAddASponsor I_add_a_sponsor_user_and_account() {
        user = new AcUserImpl();
        user.setEmail("newsponsor@sponsor.com");
        user.setPassword("abc123");
        user.setRealName("Mohd Sponsor");
        user.setUsername("sponsor");
        user.setEnabled(true);
        user.setLocked(true);
        identityService.saveUser(user);
        sessionFactory.getCurrentSession().refresh(user);
        Assert.notNull(user, "User is not found");

        String sponsorNo = "SPNSR-" + System.currentTimeMillis();
        sponsor = new AcSponsorImpl();
        sponsor.setIdentityNo(sponsorNo);
        sponsor.setName("TNB");
        sponsor.setActorType(AcActorType.SPONSOR);
        sponsor.setSponsorType(AcSponsorType.DIRECT);
        sponsor.setPhone("037443355");
        sponsor.setFax("097445566");
        sponsor.setEmail("sponsorship@tnb.my");
        identityService.saveSponsor(sponsor);
        sponsor = identityService.findSponsorBySponsorNo(sponsorNo);
        Assert.notNull(user, "Sponsor is not found");

        user.setActor(sponsor);
        identityService.updateUser(user);

        return self();
    }
}
