package my.edu.umk.pams.account.web.module.common.vo;

public enum PaymentMethod {
    CASH,
    CHEQUE,
    EFT,
    BANK_DRAFT,
    PO_MO,
    BANKERS_CHEQUE,
    TELEGRAPHIC_TRANSFER;;

    public static PaymentMethod get(int index){
        return values()[index];
    }
}
