package my.edu.umk.pams.account.web.module.account.vo;

public enum AcademicSemester {
    SEMESTER_1, // 0
    SEMESTER_2, // 1
    SEMESTER_3; // 2

    public static AcademicSemester get(int index){
        return values()[index];
    }
}
