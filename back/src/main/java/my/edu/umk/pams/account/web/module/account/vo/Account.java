package my.edu.umk.pams.account.web.module.account.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.billing.vo.Invoice;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;
import my.edu.umk.pams.account.web.module.identity.vo.Actor;

import java.io.IOException;

/**
 * @author PAMS
 */
public class Account extends MetaObject {

    private String code;
    private Actor actor;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }


    @JsonCreator
    public static Account create(String jsonString) {
        Account o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, Account.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
