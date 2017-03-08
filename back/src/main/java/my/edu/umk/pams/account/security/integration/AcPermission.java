package my.edu.umk.pams.account.security.integration;

import org.springframework.security.acls.domain.BasePermission;

/**
 * @author canang technologies
 * @since 1/12/14
 */
public class AdPermission extends BasePermission {

    private static final long serialVersionUID = 1L;

    public static final AdPermission VIEW = new AdPermission(1 << 0, 'V'); //   0000001 // 1
    public static final AdPermission CREATE = new AdPermission(1 << 1, 'C'); // 0000010 // 2
    public static final AdPermission UPDATE = new AdPermission(1 << 2, 'U'); // 0000100 // 4
    public static final AdPermission DELETE = new AdPermission(1 << 3, 'D'); // 0001000 // 8
    public static final AdPermission CANCEL = new AdPermission(1 << 4, 'K'); // 0010000 // 16
    public static final AdPermission PRINT = new AdPermission(1 << 5, 'P'); //  0100000 // 32
    public static final AdPermission ADMIN = new AdPermission(1 << 6, 'A'); //  1000000 // 64

    protected AdPermission(int mask, char code) {
        super(mask, code);
    }

    protected AdPermission(int mask) {
        super(mask);
    }
}
