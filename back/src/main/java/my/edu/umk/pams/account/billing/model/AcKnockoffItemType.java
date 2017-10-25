package my.edu.umk.pams.account.billing.model;

/**
 * @author PAMS
 */
public enum AcKnockoffItemType {
    ACCOUNT_CHARGE,		//0
    DEBIT_NOTE;		//1


	
	public static AcKnockoffItemType get(int index) {
        return values()[index];
    }
}
