package my.edu.umk.pams.account.identity.model;

public enum AcGuardianType {
    MOTHER, 
    FATHER, 
    GUARDIAN;
    
    public static AcGuardianType get(int index){
        return values()[index];
    }
}