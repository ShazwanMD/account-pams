package my.edu.umk.pams.account.system.model;

/**
 * @author canang.technologies
 * @since 7/26/14
 */
public interface AcWatch {

    Long getObjectId();

    void setObjectId(Long objectId);

    String getObjectClass();

    void setObjectClass(String objectClass);

    Long getUserId();

    void setUserId(Long userId);
}

