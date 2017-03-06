package my.edu.umk.pams.account.system.model;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcAudit extends my.edu.umk.pams.account.core.AcMetaObject {

    String getMessage();

    void setMessage(String message);

    Long getUserId();

    void setUserId(Long userId);

    Long getObjectId();

    void setObjectId(Long objectId);

    String getClassName();

    void setClassName(String className);
}
