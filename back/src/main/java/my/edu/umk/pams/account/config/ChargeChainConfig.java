package my.edu.umk.pams.account.config;

import my.edu.umk.pams.account.billing.chain.AdmissionChargeAttachChain;
import my.edu.umk.pams.account.billing.chain.CompoundChargeAttachChain;
import org.apache.commons.chain.Command;
import org.apache.commons.chain.impl.ChainBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * todo: comment
 *
 * @author canang technologies
 */
@Configuration
public class ChargeChainConfig {

    @Autowired
    private AdmissionChargeAttachChain admissionChargeAttachChain;

    @Autowired
    private CompoundChargeAttachChain compoundChargeAttachChain;

    @Bean
    public ChainBase chargeAttachChain() {
        ChainBase chainBase = new ChainBase(new Command[]{
                admissionChargeAttachChain,
                compoundChargeAttachChain
        });
        return chainBase;
    }

    @Bean
    public ChainBase chargeDetachChain() {
        ChainBase chainBase = new ChainBase(new Command[]{
                admissionChargeAttachChain,
                compoundChargeAttachChain
        });
        return chainBase;
    }
}
