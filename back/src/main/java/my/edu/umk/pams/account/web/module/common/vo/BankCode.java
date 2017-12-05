package my.edu.umk.pams.account.web.module.common.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

import java.io.IOException;

/**
 * @author PAMS
 */
public class BankCode extends MetaObject {
    private String code;
    private String name;
    private String swiftCode;
//    private String ibgCode; 

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

    public String getSwiftCode() {
        return swiftCode;
    }

    public void setSwiftCode(String swiftCode) {
        this.swiftCode = swiftCode;
    }

//    public String getIbgCode() {
//        return ibgCode;
//    }
//
//    public void setIbgCode(String ibgCode) {
//        this.ibgCode = ibgCode;
//    }

    @JsonCreator
    public static BankCode create(String jsonString) {
        BankCode o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, BankCode.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
