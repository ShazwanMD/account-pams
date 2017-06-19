package my.edu.umk.pams.account.account.model;

/**
 * @author PAMS
 */
public enum  AcAccountChargeType {
    ADMISSION,
    ACADEMIC_LATE,
    ENROLLMENT,
    ENROLLMENT_LATE,
    SECURITY,
    COMPOUND,
    STUDENT_AFFAIRS;
	

	public static AcAccountChargeType get(int index) {
        return values()[index];
    }
}
