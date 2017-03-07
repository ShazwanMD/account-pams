package my.edu.umk.pams.account.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
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
        TestWorkflowConfig.class,
        TestAccessConfig.class,
        TestCacheConfig.class,
})
@PropertySource("classpath:application.properties")
public class TestAppConfiguration {


}
