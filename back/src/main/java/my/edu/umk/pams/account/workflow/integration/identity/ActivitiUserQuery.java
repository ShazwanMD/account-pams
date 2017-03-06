package my.edu.umk.pams.account.workflow.integration.identity;

import my.edu.umk.pams.account.identity.dao.AcUserDao;
import my.edu.umk.pams.account.identity.model.AcUser;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canang technologies
 * @since 2/9/14
 */
@Component("activitiUserQuery")
public class ActivitiUserQuery implements UserQuery {

    private static final Logger LOG = LoggerFactory.getLogger(ActivitiUserQuery.class);

    @Autowired
    private AcUserDao userDao;

    @Override
    public UserQuery asc() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery desc() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public long count() {
        return userDao.find(0, 99999).size();
    }

    @Override
    public User singleResult() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public List<User> list() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public List<User> listPage(int firstResult, int maxResults) {
        List<User> actUsers = new ArrayList<User>();
        List<AcUser> users = userDao.find(firstResult, maxResults);
        for (AcUser user : users) {
            actUsers.add(new ActivitiUser(user));
        }
        return actUsers;
    }

    @Override
    public UserQuery userId(String id) {
        final AcUser user = userDao.findByUsername(id);
        UserQuery aSubUserQuery = new UserQuery() {

            @Override
            public User singleResult() {
                ActivitiUser actUser = new ActivitiUser(user);
                return actUser;
            }

            @Override
            public List<User> listPage(int firstResult, int maxResults) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public List<User> list() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery desc() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public long count() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery asc() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userLastNameLike(String lastNameLike) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userFullNameLike(String fullNameLike) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userLastName(String lastName) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userId(String id) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userFirstNameLike(String firstNameLike) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userFirstName(String firstName) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userEmailLike(String emailLike) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery userEmail(String email) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery potentialStarter(String procDefId) {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery orderByUserLastName() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery orderByUserId() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery orderByUserFirstName() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery orderByUserEmail() {
                throw new IllegalArgumentException("Not Impl");
            }

            @Override
            public UserQuery memberOfGroup(String groupId) {
                throw new IllegalArgumentException("Not Impl");
            }

        };

        return aSubUserQuery;
    }

    @Override
    public UserQuery userFirstName(String firstName) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userFirstNameLike(String firstNameLike) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userLastName(String lastName) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userLastNameLike(String lastNameLike) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userFullNameLike(String fullNameLike) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userEmail(String email) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery userEmailLike(String emailLike) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery memberOfGroup(String groupId) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery potentialStarter(String procDefId) {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderByUserId() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderByUserFirstName() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderByUserLastName() {
        throw new IllegalArgumentException("Not Impl");
    }

    @Override
    public UserQuery orderByUserEmail() {
        throw new IllegalArgumentException("Not Impl");
    }
}

