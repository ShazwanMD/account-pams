package my.edu.umk.pams.account.web.module.financialaid.vo;

public enum GraduateCenterType {
  CPS,
  MGSEB;
    
    public static GraduateCenterType get(int index){
        return values()[index];
    }
}
