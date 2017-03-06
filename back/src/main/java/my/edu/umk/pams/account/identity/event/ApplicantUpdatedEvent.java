package my.edu.umk.pams.account.identity.event;

import my.edu.umk.pams.account.identity.model.AcStudent;
import org.springframework.context.ApplicationEvent;

/**
 * @author canang technologies
 * @since 22/6/2015.
 */
public class ApplicantUpdatedEvent extends ApplicationEvent {

    public ApplicantUpdatedEvent(AcStudent applicant) {
        super(applicant);
    }
}
