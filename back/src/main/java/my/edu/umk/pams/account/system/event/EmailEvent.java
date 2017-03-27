package my.edu.umk.pams.account.system.event;

import org.springframework.context.ApplicationEvent;

import javax.mail.internet.InternetAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * @author canang technologies
 * @since 1/30/14
 */
public class EmailEvent extends ApplicationEvent {

    private InternetAddress recipient;
    private InternetAddress from;
    private String subject;
    private String body;
    private String template;
    private HashMap<String, Object> velocityModel;

    /**
     * Create a new StudentEvent.
     *
     * @param source the component that published the event (never {@code null})
     */
    public EmailEvent(Object source) {
        super(source);
    }

    public EmailEvent(String code, String to, Map<String, Object> params) {
        super(null);
    }


    public InternetAddress getFrom() {
        return from;
    }

    public void setFrom(InternetAddress from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public InternetAddress getRecipient() {
        return recipient;
    }

    public void setRecipient(InternetAddress recipients) {
        this.recipient = recipients;
    }

    public HashMap<String, Object> getVelocityModel() {
        return velocityModel;
    }

    public void setVelocityModel(HashMap<String, Object> velocityModel) {
        this.velocityModel = velocityModel;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
