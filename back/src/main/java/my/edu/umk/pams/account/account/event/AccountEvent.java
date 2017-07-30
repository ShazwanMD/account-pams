package my.edu.umk.pams.account.account.event;

import org.springframework.context.ApplicationEvent;

import my.edu.umk.pams.connector.payload.AccountPayload;

/**
 */
public class AccountEvent extends ApplicationEvent {

    private AccountPayload payload;

    public AccountEvent(AccountPayload payload) {
        super(payload);
        this.payload = payload;
    }

    public AccountPayload getPayload() {
        return payload;
    }

    public void setPayload(AccountPayload payload) {
        this.payload = payload;
    }
}
