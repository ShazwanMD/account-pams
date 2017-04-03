package my.edu.umk.pams.account.web.module.billing.vo;


import my.edu.umk.pams.account.web.module.core.vo.Task;

/**
 * @author PAMS
 */
public class ReceiptTask extends Task {

    private Receipt receipt;

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}
