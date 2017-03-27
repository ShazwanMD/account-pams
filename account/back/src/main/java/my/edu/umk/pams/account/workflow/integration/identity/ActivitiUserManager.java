package my.edu.umk.pams.account.workflow.integration.identity;

import my.edu.umk.pams.account.identity.dao.AcGroupDao;
import my.edu.umk.pams.account.identity.dao.AcUserDao;
import my.edu.umk.pams.account.identity.model.AcGroup;
import my.edu.umk.pams.account.identity.model.AcUser;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.impl.Page;
import org.activiti.engine.impl.UserQueryImpl;
import org.activiti.engine.impl.persistence.entity.IdentityInfoEntity;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.activiti.engine.impl.persistence.entity.UserEntityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author canang technologies
 * @since 1/19/14
 */
@Component("activitiUserManager")
public class ActivitiUserManager extends UserEntityManager {

    private static final Logger LOG = LoggerFactory.getLogger(ActivitiUserManager.class);

    @Autowired
    private AcGroupDao groupDao;

    @Autowired
    private AcUserDao userDao;

    @Autowired
    private ActivitiUserQuery userQuery;

    public ActivitiUserManager() {
        super();
    }

    @Override
    public User createNewUser(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertUser(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean checkPassword(String userId, String password) {
        return Boolean.TRUE;
    }

    @Override
    public UserEntity findUserById(String userId) {
        LOG.info("finding user {}", userId);
        return new ActivitiUser(userDao.findByUsername(userId));
    }

    @Override
    public void deleteUser(String userId) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Group> findGroupsByUser(String userId) {
        LOG.debug("username:" + userId);
        AcUser byUsername = userDao.findByUsername(userId);
        List<AcGroup> principalGroups = groupDao.findMemberships(byUsername);
        List<Group> groups = new ArrayList<Group>();
        for (AcGroup group : principalGroups) {
            LOG.debug("group:" + group);
            ActivitiGroup g = new ActivitiGroup(group);
            groups.add(g);
        }
        return groups;
    }

    @Override
    public UserQuery createNewUserQuery() {
        return userQuery;
    }

    @Override
    public List<User> findUserByQueryCriteria(UserQueryImpl query, Page page) {
        if (query.getId() != null) {
            List<User> result = new ArrayList<User>();
            result.add(findUserById(query.getId()));
            return result;
        } else {
            throw new UnsupportedOperationException();
        }
    }


    @Override
    public long findUserCountByQueryCriteria(UserQueryImpl query) {
        return findUserByQueryCriteria(query, null).size();
    }

    @Override
    public IdentityInfoEntity findUserInfoByUserIdAndKey(String userId, String key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> findUserInfoKeysByUserIdAndType(String userId, String type) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void flush() {
        super.flush();
    }
}
