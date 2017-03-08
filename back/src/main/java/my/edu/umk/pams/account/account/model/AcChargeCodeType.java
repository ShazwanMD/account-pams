package my.edu.umk.pams.account.account.model;

/**
 * @author PAMS
 */
public enum AcChargeCodeType {
    ACADEMIC,
    HOSTEL,
    MISCELLANEOUS;

    public static AcChargeCodeType get(int index) {
        return values()[index];
    }

}
