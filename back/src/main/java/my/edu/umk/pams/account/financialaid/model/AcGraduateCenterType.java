package my.edu.umk.pams.account.financialaid.model;

public enum AcGraduateCenterType {
    CPS,            // 0
    MGSEB;          // 1
    
    public static AcGraduateCenterType get(Integer index) {
        return values()[index];
    }
}
