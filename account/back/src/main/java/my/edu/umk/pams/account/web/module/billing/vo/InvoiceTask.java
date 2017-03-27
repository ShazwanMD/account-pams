package my.edu.umk.pams.account.web.module.billing.vo;


import my.edu.umk.pams.account.web.module.core.vo.Task;

/**
 * @author PAMS
 */
public class InvoiceTask extends Task {

    private Invoice invoice;

    public Invoice getBill() {
        return invoice;
    }

    public void setBill(Invoice invoice) {
        this.invoice = invoice;
    }
}
