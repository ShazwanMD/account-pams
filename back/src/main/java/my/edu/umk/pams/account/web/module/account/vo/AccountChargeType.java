package my.edu.umk.pams.account.web.module.account.vo;

/**
 * @author PAMS
 */
public enum AccountChargeType {
    ADMISSION,
    ACADEMIC,
    ACADEMIC_LATE,
    ENROLLMENT,
    ENROLLMENT_LATE,
    SECURITY,
    COMPOUND,
    STUDENT_AFFAIRS,
    LOAN,
	RECEIPT,
	KNOCKOFF,
	WAIVER;

    public static AccountChargeType get(int index){
        return values()[index];
    }
}
