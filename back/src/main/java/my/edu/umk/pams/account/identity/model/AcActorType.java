package my.edu.umk.pams.account.identity.model;

import my.edu.umk.pams.account.web.module.identity.vo.ActorType;

/**
 * @author canang.technologies
 * @since 1/11/2014
 */
public enum AcActorType {
    STAFF, //0
    STUDENT, //1
    SPONSOR; //2
	
    public static AcActorType get(int index) {
        return values()[index];
    }
}
