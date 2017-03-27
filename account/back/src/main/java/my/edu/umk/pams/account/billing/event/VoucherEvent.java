package my.edu.umk.pams.account.billing.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author PAMS
 */
public abstract class VoucherEvent extends ApplicationEvent {

    public VoucherEvent() {
        super(null);
    }

}
