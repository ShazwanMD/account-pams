package my.edu.umk.pams.account.identity.dao;


import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcStudent;

import java.util.List;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public interface AcStudentDao extends GenericDao<Long, AcStudent> {

    AcStudent findByStudentNo(String studentNo);

    List<AcStudent> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);
}
