package my.edu.umk.pams.account.billing.model;

/**
 * @author PAMS
 */
public enum AcReceiptItemType {
    ACCOUNT_CHARGE,		//0
    DEBIT_NOTE;		//1


	
	public static AcReceiptItemType get(int index) {
        return values()[index];
    }
}
