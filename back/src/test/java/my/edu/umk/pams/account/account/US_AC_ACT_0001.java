package my.edu.umk.pams.account.account;

import io.jsonwebtoken.lang.Assert;
import my.edu.umk.pams.account.TestSupport;
import my.edu.umk.pams.account.config.TestAppConfiguration;
import my.edu.umk.pams.account.identity.dao.AcActorDao;
import my.edu.umk.pams.account.identity.model.AcActor;
import my.edu.umk.pams.account.identity.model.AcStaffImpl;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author PAMS
 *
 * US_AC_ACT_0001 class is an example test class
 * For naming explanation of US_AC_ACT_0001,
 * for more details, see <project>/README.md
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(classes = TestAppConfiguration.class)
public class US_AC_ACT_0001 extends TestSupport{

    private static final Logger LOG = LoggerFactory.getLogger(US_AC_ACT_0001.class);

    @Autowired
    private AcActorDao actorDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Before
    public void before() {
        super.before();
        AcActor actor = new AcStaffImpl();
        actor.setIdentityNo("ABC001");
        actor.setName("Yo Name");

        actorDao.save(actor,currentUser);
    }

    @After
    public void after(){
    }

    @Test
    @Rollback
    public void test1(){
        Assert.notNull(currentUser);
        Assert.notNull("something");
        LOG.debug("11111111111111111111");

        AcActor actor = new AcStaffImpl();
        actor.setIdentityNo("ABC123");
        actor.setName("Mah Name");

        actorDao.save(actor,currentUser);
        sessionFactory.getCurrentSession().flush();
        actorDao.refresh(actor);
    }

    @Test
    @Rollback
    public void test2(){
        Assert.notNull(currentUser);
        Assert.notNull("something");
        LOG.debug("2222222222222222");
        AcActor actor = new AcStaffImpl();
        actor.setIdentityNo("ABC12345");
        actor.setName("Noah Name");

        actorDao.save(actor,currentUser);
        sessionFactory.getCurrentSession().flush();
        actorDao.refresh(actor);

        List<AcActor> acActors = actorDao.find();
        acActors.forEach(
                acActor -> {
                    LOG.debug(acActor.getId() + " <--");
                    LOG.debug(acActor.getIdentityNo() + " <--");
                    LOG.debug(acActor.getName() + " <--");
                });
    }
}

