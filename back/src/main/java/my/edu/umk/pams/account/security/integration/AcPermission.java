package my.edu.umk.pams.account.security.integration;

import org.springframework.security.acls.domain.BasePermission;

/**
 * @author canang technologies
 * @since 1/12/14
 */
public class AcPermission extends BasePermission {

    private static final long serialVersionUID = 1L;

    public static final AcPermission VIEW = new AcPermission(1 << 0, 'V'); //   0000001 // 1
    public static final AcPermission CREATE = new AcPermission(1 << 1, 'C'); // 0000010 // 2
    public static final AcPermission UPDATE = new AcPermission(1 << 2, 'U'); // 0000100 // 4
    public static final AcPermission DELETE = new AcPermission(1 << 3, 'D'); // 0001000 // 8
    public static final AcPermission CANCEL = new AcPermission(1 << 4, 'K'); // 0010000 // 16
    public static final AcPermission PRINT = new AcPermission(1 << 5, 'P'); //  0100000 // 32
    public static final AcPermission ADMIN = new AcPermission(1 << 6, 'A'); //  1000000 // 64

    protected AcPermission(int mask, char code) {
        super(mask, code);
    }

    protected AcPermission(int mask) {
        super(mask);
    }
}
