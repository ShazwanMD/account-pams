package my.edu.umk.pams.account.web.module.identity.vo;

/**
 * @author PAMS
 */
public enum ActorType {

    STAFF, //0
    STUDENT, //1
    SPONSOR; //2

    public static ActorType get(int index) {
        return values()[index];
    }

}
