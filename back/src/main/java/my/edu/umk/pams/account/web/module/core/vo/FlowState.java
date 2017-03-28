package my.edu.umk.pams.account.web.module.core.vo;

/**
 * @author PAMS
 */
public enum FlowState {
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
    COMPLETED,              // 15
    ARCHIVED;               // 16

    public static FlowState get(int index) {
        return values()[index];
    }

}
