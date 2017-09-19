package my.edu.umk.pams.account.account.model;

/**
 * @author PAMS
 */
public enum AcAccountTransactionCode {
    INVOICE,  // 0
    RECEIPT,  // 1
    VOUCHER,  // 2
    REFUND,   // 3
    DEBIT_NOTE,   // 4
    CREDIT_NOTE,   // 5
    ADHOC,   // 6
	ADVANCE_PAYMENT, // 7
	KNOCKOFF;		//8

    public static AcAccountTransactionCode get(int index) {
        return values()[index];
    }
}
