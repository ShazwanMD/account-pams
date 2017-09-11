package my.edu.umk.pams.account.web.module.billing.vo;


import java.math.BigDecimal;

import my.edu.umk.pams.account.web.module.core.vo.Task;

/**
 * @author PAMS
 */
public class ReceiptTask extends Task {

    private Receipt receipt;
    private BigDecimal totalReceived;

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

	public BigDecimal getTotalReceived() {
		return totalReceived;
	}

	public void setTotalReceived(BigDecimal totalReceived) {
		this.totalReceived = totalReceived;
	}
    
}
