package my.edu.umk.pams.account.system.model;

/**
 * @author canang technologies
 * @since 1/27/14
 */
public interface AcEmailQueue extends my.edu.umk.pams.account.core.AcMetaObject {

    String getTo();

    String getCc();

    String getBcc();

    String getSubject();

    String getBody();

    Integer getRetryCount();

    String getCode();

    void setCode(String code);

    void setTo(String to);

    void setSubject(String subject);

    void setBody(String body);

    void setRetryCount(Integer retryCount);

    AcEmailQueueStatus getQueueStatus();

    void setQueueStatus(AcEmailQueueStatus status);

}
