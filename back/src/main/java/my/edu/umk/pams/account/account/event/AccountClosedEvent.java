package my.edu.umk.pams.account.account.event;

import my.edu.umk.pams.connector.payload.AccountPayload;

/**
 */
public class AccountClosedEvent extends AccountEvent{

    public AccountClosedEvent(AccountPayload payload) {
        super(payload);
    }
}
