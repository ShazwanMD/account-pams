package my.edu.umk.pams.account.common.model;

/**
 * @author PAMS
 */
public enum AcPaymentMethod {
    CASH("CASH"),

    CHEQUE("CEK"),

    EFT("KREDIT KE BANK"),

    BANK_DRAFT("BANK DERAF");

    private String description;

    public static AcPaymentMethod get(int index) {
        return values()[index];
    }

    AcPaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
