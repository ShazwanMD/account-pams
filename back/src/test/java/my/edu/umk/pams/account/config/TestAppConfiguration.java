package my.edu.umk.pams.account.config;

import com.tngtech.jgiven.integration.spring.EnableJGiven;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJGiven
@EnableWebSecurity
@EnableScheduling
@EnableBatchProcessing
@EnableTransactionManagement
@EnableCaching
@ComponentScan(basePackages = {

        // testing
        "my.edu.umk.pams.bdd.stage",

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
        ChargeChainConfig.class,
})
@PropertySource("classpath:application.properties")
public class TestAppConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public String configureJGivenReportPlainText() {
        final String key = "jgiven.report.text";
        final String value = env.getProperty(key) == null ? "true" : env.getProperty(key);
        return System.setProperty(key, value);
    }
}
