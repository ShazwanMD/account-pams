package my.edu.umk.pams.account.account.model;

/**
 * @author PAMS
 */
public enum  AcAccountChargeType {
    ADMISSION,		//0
    ACADEMIC,		//1
    ACADEMIC_LATE,	//2
    ENROLLMENT,		//3
    ENROLLMENT_LATE,//4
    SECURITY,		//5
    COMPOUND,		//6	
    STUDENT_AFFAIRS,//7
    LOAN,			//8
	RECEIPT,		//9
	KNOCKOFF,
	WAIVER;
	

	public static AcAccountChargeType get(int index) {
        return values()[index];
    }
}
