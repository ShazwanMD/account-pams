package my.edu.umk.pams.account.billing.model;

import my.edu.umk.pams.account.account.model.AcChargeCode;

/**
 * @author PAMS
 */
public interface AcInvoiceItem {


    Long getId();

    void setId(Long id);

    AcChargeCode getChargeCode();

    void setChargeCode(AcChargeCode chargeCode);

    AcInvoice getInvoice();

    void setInvoice(AcInvoice invoice);
}
