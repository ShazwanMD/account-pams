package my.edu.umk.pams.account.web.module.billing.vo;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.ObjectMapper;
import my.edu.umk.pams.account.web.module.core.vo.Task;

import java.io.IOException;

/**
 * @author PAMS
 */
public class InvoiceTask extends Task {

    private Invoice invoice;

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
