package my.edu.umk.pams.account.billing.chain;

import org.apache.commons.chain.impl.ChainBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author PAMS
 */
@Component("chargeAttachProcessor")
public class ChargeAttachProcessor {

    private static Logger LOG = LoggerFactory.getLogger(ChargeAttachProcessor.class);

    @Autowired
    @Qualifier("chargeAttachChain")
    private ChainBase chainBase;

    public void process(ChargeContext context) throws Exception {
        try {
            chainBase.execute(context);
        } catch (Exception e) {
            LOG.debug("error occured", e);
            throw e;
        }
    }
}
