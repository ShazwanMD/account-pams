package my.edu.umk.pams.account.web.module.account.vo;

public enum AccountTransactionCode {

    INVOICE,  // 0
    RECEIPT,  // 1
    VOUCHER,  // 2
    REFUND,   // 3
    DEBIT_NOTE,   // 4
    CREDIT_NOTE,   // 5
    ADHOC,   // 6
    ADVANCE_PAYMENT;

    public static AccountTransactionCode get(int index){
        return values()[index];
    }
}
