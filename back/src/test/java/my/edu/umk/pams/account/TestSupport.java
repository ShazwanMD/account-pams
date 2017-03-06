package my.edu.umk.pams.account;

import my.edu.umk.pams.account.identity.model.AcUser;
import my.edu.umk.pams.account.identity.model.AcUserImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

public class TestSupport {

    private static final Logger LOG = LoggerFactory.getLogger(TestSupport.class);

    @Autowired
    private SessionFactory sessionFactory;

    protected AcUser currentUser;

    public void before() {
        Session session = sessionFactory.getCurrentSession();

        // prepare currentUser
        currentUser = new AcUserImpl();
        currentUser.setUsername("root");
        currentUser.setRealName("Root Admin");
        currentUser.setPassword("abc123");
        currentUser.setEmail("root@umk.edu.my");

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

    public void after() {
    }
}