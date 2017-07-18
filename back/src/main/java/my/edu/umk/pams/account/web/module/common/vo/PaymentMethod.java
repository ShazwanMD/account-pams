package my.edu.umk.pams.account.web.module.common.vo;

public enum PaymentMethod {
    CASH,
    CHEQUE,
    EFT,
    BANK_DRAFT;

    public static PaymentMethod get(int index){
        return values()[index];
    }
}
