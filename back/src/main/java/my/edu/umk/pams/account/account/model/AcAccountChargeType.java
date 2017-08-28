package my.edu.umk.pams.account.account.model;

/**
 * @author PAMS
 */
public enum  AcAccountChargeType {
    ADMISSION,
    ACADEMIC,
    ACADEMIC_LATE,
    ENROLLMENT,
    ENROLLMENT_LATE,
    SECURITY,
    COMPOUND,
    STUDENT_AFFAIRS,
    LOAN;
	

	public static AcAccountChargeType get(int index) {
        return values()[index];
    }
}
