package my.edu.umk.pams.account.config;

import my.edu.umk.pams.account.billing.chain.AcademicChargeAttachChain;
import my.edu.umk.pams.account.billing.chain.EnrollmentChargeAttachChain;
import my.edu.umk.pams.account.billing.chain.EnrollmentLateChargeAttachChain;
import my.edu.umk.pams.account.billing.chain.SecurityChargeAttachChain;
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
    private EnrollmentChargeAttachChain enrollmentChargeAttachChain;

    @Autowired
    private EnrollmentLateChargeAttachChain enrollmentLateChargeAttachChain;

    @Autowired
    private AcademicChargeAttachChain academicChargeAttachChain;

    @Autowired
    private SecurityChargeAttachChain securityChargeAttachChain;

    @Bean
    public ChainBase chargeAttachChain() {
        ChainBase chainBase = new ChainBase(new Command[]{
                academicChargeAttachChain,
                enrollmentChargeAttachChain,
                enrollmentLateChargeAttachChain,
                securityChargeAttachChain
        });
        return chainBase;
    }

    @Bean
    public ChainBase chargeDetachChain() {
        ChainBase chainBase = new ChainBase(new Command[]{
                academicChargeAttachChain,
                enrollmentChargeAttachChain,
                enrollmentLateChargeAttachChain,
                securityChargeAttachChain
        });
        return chainBase;
    }
}
