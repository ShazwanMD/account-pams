package my.edu.umk.pams.account.identity.dao;


import my.edu.umk.pams.account.core.GenericDao;
import my.edu.umk.pams.account.identity.model.AcStaff;

import java.util.List;

/**
 * @author team canang
 * @since 6/6/2015.
 */
public interface AcStaffDao extends GenericDao<Long, AcStaff> {

    AcStaff findByStaffNo(String staffNo);

    AcStaff findByNricNo(String nricNo);

    AcStaff findByEmail(String email);

    AcStaff findByName(String name);

    List<AcStaff> find(String filter, Integer offset, Integer limit);

    Integer count(String filter);

    boolean isExists(String staffNo);

    boolean isEmailExists(String email);

}
