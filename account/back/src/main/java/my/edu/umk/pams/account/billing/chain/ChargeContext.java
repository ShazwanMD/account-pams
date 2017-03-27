package my.edu.umk.pams.account.billing.chain;

import my.edu.umk.pams.account.account.model.AcAccountCharge;
import my.edu.umk.pams.account.billing.model.AcInvoice;
import org.apache.commons.lang.Validate;

import java.util.HashMap;

/**
 * @author PAMS
 */
public class ChargeContext extends HashMap implements ChainContext {

    private static final String CHARGE = "charge";
    private static final String INVOICE = "invoice";

    public ChargeContext(AcInvoice invoice, AcAccountCharge charge) {
        Validate.notNull(charge, "not null");
        put(INVOICE, invoice);
        put(CHARGE, charge);
    }

    public AcInvoice getInvoice() {
        return (AcInvoice) get(INVOICE);
    }

    public AcAccountCharge getCharge() {
        return (AcAccountCharge) get(CHARGE);
    }
}
