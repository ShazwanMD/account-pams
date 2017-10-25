package my.edu.umk.pams.account.billing.model;

/**
 * @author PAMS
 */
public enum AcWaiverItemType {
    ACCOUNT_CHARGE,		//0
    DEBIT_NOTE;		//1


	
	public static AcWaiverItemType get(int index) {
        return values()[index];
    }
}
