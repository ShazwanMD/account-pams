package my.edu.umk.pams.account.web.module.marketing.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

import java.io.IOException;

/**
 * @author PAMS
 */
public class PromoCode extends MetaObject{

    private String referenceNo;
    private String description;
    private Integer quantity;

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @JsonCreator
    public static PromoCode create(String jsonString) {
        PromoCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, PromoCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}

