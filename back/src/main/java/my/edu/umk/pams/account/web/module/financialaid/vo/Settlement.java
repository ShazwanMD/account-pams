package my.edu.umk.pams.account.web.module.financialaid.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.account.vo.AcademicSession;
import my.edu.umk.pams.account.web.module.core.vo.MetaObject;
import java.util.Date;
import java.io.IOException;

/**
 * @author PAMS
 */
public class Settlement extends MetaObject{

    private String referenceNo;
    private String sourceNo;
    private String description;
    private boolean executed;
    private Date issuedDate;
    private AcademicSession academicSession;

    public String getReferenceNo() {
        return referenceNo;
    }

    public void setReferenceNo(String referenceNo) {
        this.referenceNo = referenceNo;
    }

    public String getSourceNo() {
        return sourceNo;
    }

    public void setSourceNo(String sourceNo) {
        this.sourceNo = sourceNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isExecuted() {
        return executed;
    }

    public void setExecuted(boolean executed) {
        this.executed = executed;
    }
    
    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public AcademicSession getAcademicSession() {
        return academicSession;
    }

    public void setAcademicSession(AcademicSession academicSession) {
        this.academicSession = academicSession;
    }

    @JsonCreator
    public static Settlement create(String jsonString) {
        Settlement o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, Settlement.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}

