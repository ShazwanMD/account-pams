package my.edu.umk.pams.account.identity.event;

import my.edu.umk.pams.account.identity.model.AcStaff;

/**
 * @author canang technologies
 * @since 22/6/2015.
 */
public class StaffUpdatedEvent extends StaffEvent {

    public StaffUpdatedEvent(AcStaff staff) {
        super(staff);
    }
}
