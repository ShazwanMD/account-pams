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
        "my.edu.umk.pams.account",
}
)
@Import({
        DatasourceConfig.class,
        WorkflowConfig.class,
        CacheConfig.class,
        EmailConfig.class,
        VelocityConfig.class,
        SecurityConfig.class,
        AccessConfig.class,
//        ThreadingConfig.class,
        SwaggerConfig.class
})
@PropertySource("classpath:application.properties")
public class AppConfiguration {
}
