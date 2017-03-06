package my.edu.umk.pams.account.identity.event;


import my.edu.umk.pams.account.identity.model.AcStudent;

/**
 * @author canang technologies
 * @since 22/6/2015.
 */
public class StudentCreatedEvent extends StudentEvent {

    public StudentCreatedEvent(AcStudent vendor) {
        super(vendor);
    }

}
