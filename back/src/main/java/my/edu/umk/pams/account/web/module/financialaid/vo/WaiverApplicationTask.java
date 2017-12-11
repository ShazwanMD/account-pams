package my.edu.umk.pams.account.web.module.financialaid.vo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.account.vo.Account;
import my.edu.umk.pams.account.web.module.core.vo.Task;

import java.io.IOException;

/**
 * @author PAMS
 */
public class WaiverApplicationTask extends Task {

    private Account account;
    private WaiverApplication application;
    private WaiverApplicationType waiverType;
    private GraduateCenterType graduateCenterType;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public WaiverApplication getApplication() {
        return application;
    }

    public void setApplication(WaiverApplication application) {
        this.application = application;
    }
    
    public WaiverApplicationType getWaiverType() {
		return waiverType;
	}

	public void setWaiverType(WaiverApplicationType waiverType) {
		this.waiverType = waiverType;
	}
	
	public GraduateCenterType getGraduateCenterType() {
		return graduateCenterType;
	}

	public void setGraduateCenterType(GraduateCenterType graduateCenterType) {
		this.graduateCenterType = graduateCenterType;
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

