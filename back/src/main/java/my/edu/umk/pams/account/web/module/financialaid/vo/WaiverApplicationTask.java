package my.edu.umk.pams.account.web.module.financialaid.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.billing.vo.InvoiceTask;
import my.edu.umk.pams.account.web.module.core.vo.Task;

import java.io.IOException;

/**
 * @author PAMS
 */
public class WaiverApplicationTask extends Task {

    private WaiverApplication application;

    public WaiverApplication getApplication() {
        return application;
    }

    public void setApplication(WaiverApplication application) {
        this.application = application;
    }

    @JsonCreator
    public static WaiverApplicationTask create(String jsonString) {
        WaiverApplicationTask o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, WaiverApplicationTask.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}

