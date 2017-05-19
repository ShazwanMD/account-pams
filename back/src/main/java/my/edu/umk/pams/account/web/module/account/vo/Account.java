package my.edu.umk.pams.account.web.module.account.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;
import my.edu.umk.pams.account.web.module.identity.vo.Actor;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
public class Account extends MetaObject {

    private String code;
    private String name; // transient
    private String email;// transient
    private Actor actor;

    // transient
    private BigDecimal balance;
    private BigDecimal effectiveBalance;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getEffectiveBalance() {
        return effectiveBalance;
    }

    public void setEffectiveBalance(BigDecimal effectiveBalance) {
        this.effectiveBalance = effectiveBalance;
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
