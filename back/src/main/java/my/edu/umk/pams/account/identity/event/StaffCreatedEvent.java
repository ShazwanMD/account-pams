package my.edu.umk.pams.account.identity.event;


import my.edu.umk.pams.account.identity.model.AcStaff;

/**
 * @author canang technologies
 * @since 22/6/2015.
 */
public class StaffCreatedEvent extends StaffEvent {

    public StaffCreatedEvent(AcStaff Staff) {
        super(Staff);
    }

}
