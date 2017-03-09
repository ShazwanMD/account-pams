package my.edu.umk.pams.bdd.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.integration.spring.JGivenStage;
import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.dao.AcStudentDao;
import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.model.AcUserImpl;
import my.edu.umk.pams.account.security.service.SecurityService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.sql.Timestamp;

/**
 * Created by PAMS on 3/8/2017.
 */
@JGivenStage
@ContextConfiguration(classes = TestAppConfiguration.class)
public class GivenIAmAUser extends Stage<GivenIAmAUser> {

    private static final Logger LOG = LoggerFactory.getLogger(GivenIAmAUser.class);

    @Autowired
    @ProvidedScenarioState
    private AcStudentDao studentDao;

    @Autowired
    @ProvidedScenarioState
    private SecurityService securityService;

    @Autowired
    @ProvidedScenarioState
    private SessionFactory sessionFactory;

    @ProvidedScenarioState
    private AcUser currentUser;

    private void makeAdminUser() {
//        Session session = sessionFactory.getCurrentSession();
        Session session = securityService.getCurrentSession();

        // prepare currentUser
        currentUser = new AcUserImpl();
        currentUser.setUsername("adamadmin");
        currentUser.setRealName("Adam Admin");
        currentUser.setPassword("adamPa55w0rd");
        currentUser.setEmail("adam.admin@umk.edu.my");

        // prepare metadata
        my.edu.umk.pams.account.core.AcMetadata metadata = new my.edu.umk.pams.account.core.AcMetadata();
        metadata.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        metadata.setCreatorId(0L);
        metadata.setState(my.edu.umk.pams.account.core.AcMetaState.ACTIVE);
        currentUser.setMetadata(metadata);

        session.save(currentUser);
        session.flush();
        session.refresh(currentUser);
    }

    public GivenIAmAUser I_am_an_admin_user() {
        makeAdminUser();

        Assert.notNull(currentUser, "We have no current user");

        return self();
    }

}
