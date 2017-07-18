package my.edu.umk.pams.account.web.module.billing.vo;

public enum ReceiptType {
    ELECTRONIC,
    BURSARY,
    BILLING;

    public static ReceiptType get(int index){
        return values()[index];
    }
}
