package my.edu.umk.pams.account.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableWebSecurity
@EnableScheduling
@EnableBatchProcessing
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
})
@Import({
        TestDatasourceConfig.class,
        TestSecurityConfig.class,
        TestWorkflowConfig.class,
        TestAccessConfig.class,
        TestCacheConfig.class,
})
@PropertySource("classpath:application.properties")
public class TestAppConfiguration {


}
