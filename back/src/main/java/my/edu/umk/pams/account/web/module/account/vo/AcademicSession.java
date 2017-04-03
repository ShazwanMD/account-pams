package my.edu.umk.pams.account.web.module.account.vo;

import my.edu.umk.pams.account.web.module.core.vo.MetaObject;

/**
 * @author PAMS
 */
public class AcademicSession extends MetaObject {
    private String code;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
