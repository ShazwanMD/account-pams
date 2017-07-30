package my.edu.umk.pams.account.account.event;

import my.edu.umk.pams.connector.payload.AccountPayload;

/**
 */
public class AccountRevisedEvent extends AccountEvent{

    public AccountRevisedEvent(AccountPayload payload) {
        super(payload);
    }
}
