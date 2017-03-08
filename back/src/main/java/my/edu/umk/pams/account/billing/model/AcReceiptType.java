package my.edu.umk.pams.account.billing.model;

/**
 * @author PAMS
 */
public enum AcReceiptType {
    ELECTRONIC,
    BURSARY,
    BILLING;

    public static AcReceiptType get(Integer index) {
        return values()[index];
    }

}
