package my.edu.umk.pams.account.web.module.financialaid.vo;

public enum WaiverApplicationType {
    REBATE,            
    DISCOUNT;
    
    public static WaiverApplicationType get(int index){
        return values()[index];
    }
}
