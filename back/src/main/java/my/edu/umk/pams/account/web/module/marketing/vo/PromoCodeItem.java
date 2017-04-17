package my.edu.umk.pams.account.web.module.marketing.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

import java.io.IOException;
import java.math.BigDecimal;

/**
 * @author PAMS
 */
public class PromoCodeItem extends MetaObject {

    private String code;
    private boolean applied;
    private BigDecimal value;
    private Account account;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isApplied() {
        return applied;
    }

    public void setApplied(boolean applied) {
        this.applied = applied;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @JsonCreator
    public static PromoCodeItem create(String jsonString) {
        PromoCodeItem o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, PromoCodeItem.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}
