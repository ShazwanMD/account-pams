package my.edu.umk.pams.account.identity.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author canang technologies
 * @since 22/6/2015.
 */
public class StudentEvent extends ApplicationEvent {

    public StudentEvent(Object source) {
        super(source);
    }
}
