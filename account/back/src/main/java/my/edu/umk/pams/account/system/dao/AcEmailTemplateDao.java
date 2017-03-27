package my.edu.umk.pams.account.system.dao;

import my.edu.umk.pams.account.core.GenericDao;

import java.util.List;

/**
 * @author canang technologies
 * @since 9/4/2016.
 */
public interface AcEmailTemplateDao extends GenericDao<Long, my.edu.umk.pams.account.system.model.AcEmailTemplate> {

    my.edu.umk.pams.account.system.model.AcEmailTemplate findByCode(String code);

    Integer count(String filter);

    List<my.edu.umk.pams.account.system.model.AcEmailTemplate> find(String filter);

    List<my.edu.umk.pams.account.system.model.AcEmailTemplate> find(String filter, Integer offset, Integer limit);
}
