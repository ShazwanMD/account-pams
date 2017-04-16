package my.edu.umk.pams.account.web.module.billing.vo;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.core.vo.Task;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author PAMS
 */
public class InvoiceTask extends Task {

    private String accountCode;
    private BigDecimal totalAmount;
    private BigDecimal balanceAmount;
    private Date issuedDate;
    private Invoice invoice;

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    @JsonCreator
    public static InvoiceTask create(String jsonString) {
        InvoiceTask o = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            o = mapper.readValue(jsonString, InvoiceTask.class);
        } catch (IOException e) {
            // handle
        }
        return o;
    }
}
