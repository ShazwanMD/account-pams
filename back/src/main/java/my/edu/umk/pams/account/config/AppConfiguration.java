package my.edu.umk.pams.account.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableIntegration
@EnableBatchProcessing
@EnableJms
@EnableTransactionManagement
@EnableCaching
@ComponentScan(basePackages = {
        // internals
        "my.edu.umk.pams.account.identity",
        "my.edu.umk.pams.account.security",
        "my.edu.umk.pams.account.system",
        "my.edu.umk.pams.account.workflow",

        // modules
        "my.edu.umk.pams.account.common",
        "my.edu.umk.pams.account.billing",
        "my.edu.umk.pams.account.account",
        "my.edu.umk.pams.account.financialaid",
        "my.edu.umk.pams.account.marketing",

        // web modules
        "my.edu.umk.pams.account.web.module.identity",
        "my.edu.umk.pams.account.web.module.common",
        "my.edu.umk.pams.account.web.module.billing",
        "my.edu.umk.pams.account.web.module.financialaid",
        "my.edu.umk.pams.account.web.module.account",
        "my.edu.umk.pams.account.web.module.marketing",

}
)
@Import({
        DatasourceConfig.class,
        SecurityConfig.class,
        AccessConfig.class,
        WorkflowConfig.class,
        CacheConfig.class,
        SwaggerConfig.class,
        ChargeChainConfig.class,
        IntegrationConfig.class,
        JmsConfig.class,
//        EmailConfig.class,
//        VelocityConfig.class,
//        ThreadingConfig.class,
})
@PropertySource("classpath:application.properties")
public class AppConfiguration {
}
