package my.edu.umk.pams.account.web.module.billing.vo;

/**
 * @author PAMS
 */
public enum ReceiptItemType {
    ACCOUNT_CHARGE,		//0
    DEBIT_NOTE;	//1

	
	public static ReceiptItemType get(int index) {
        return values()[index];
    }
}
