package my.edu.umk.pams.account.web.module.account.vo;

/**
 * @author PAMS
 */
public enum AccountChargeType {
    ADMISSION,
    COMPOUND;

    public static AccountChargeType get(int index){
        return values()[index];
    }
}
