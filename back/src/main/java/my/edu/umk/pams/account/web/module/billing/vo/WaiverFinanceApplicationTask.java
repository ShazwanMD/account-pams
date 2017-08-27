package my.edu.umk.pams.account.web.module.billing.vo;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.core.vo.Task;
import my.edu.umk.pams.account.web.module.billing.vo.WaiverFinanceApplication;
import my.edu.umk.pams.account.web.module.billing.vo.WaiverFinanceApplicationTask;

public class WaiverFinanceApplicationTask extends Task {

    private Account account;
    private WaiverFinanceApplication application;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public WaiverFinanceApplication getApplication() {
        return application;
    }

    public void setApplication(WaiverFinanceApplication application) {
        this.application = application;
    }

    @JsonCreator
    public static WaiverFinanceApplicationTask create(String jsonString) {
    	WaiverFinanceApplicationTask o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, WaiverFinanceApplicationTask.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }

}

