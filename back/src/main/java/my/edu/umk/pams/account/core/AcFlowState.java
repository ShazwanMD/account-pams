package my.edu.umk.pams.account.core;

/**
 * @author canang technologies
 * @since 20/4/2015.
 */
public enum AcFlowState {
    NEW,                    // 1
    DRAFTED,                // 1
    REQUESTED,              // 2
    REGISTERED,             // 3
    PREPARED,               // 4
    VERIFIED,               // 5
    UPPER_VERIFIED,         // 6
    CHECKED,                // 7
    APPROVED,               // 8
    UPPER_APPROVED,         // 9
    SELECTED,               // 10
    EVALUATED,              // 11
    CANCELLED,              // 12
    REJECTED,               // 13
    REMOVED,                // 14
    COMPLETED,              // 16
    ARCHIVED;               // 17

    AcFlowState() {
    }
}
