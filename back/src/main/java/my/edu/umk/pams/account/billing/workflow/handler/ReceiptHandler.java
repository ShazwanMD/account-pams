package my.edu.umk.pams.account.billing.workflow.handler;

import my.edu.umk.pams.account.core.model.AcDocument;
import my.edu.umk.pams.account.workflow.integration.registry.DocumentHandler;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * todo(faizal): change to AcReceipt later
 * @author PAMS
 */
@Component
public class ReceiptHandler implements DocumentHandler<AcDocument> {

    @Override
    public String process(AcDocument document, Map<String, Object> variables) {
        return null;
    }
}
