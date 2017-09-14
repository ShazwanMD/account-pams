package my.edu.umk.pams.account.financialaid.model;

public enum AcWaiverApplicationType {
    REBATE,            // 0
    DISCOUNT;          // 1
    
    public static AcWaiverApplicationType get(Integer index) {
        return values()[index];
    }
}
