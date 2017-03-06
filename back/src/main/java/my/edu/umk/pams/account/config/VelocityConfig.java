package my.edu.umk.pams.account.config;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import java.io.IOException;

@Configuration
public class VelocityConfig {

    @Bean
    public VelocityEngine velocityEngine() throws IOException {
        VelocityEngineFactoryBean factoryBean = new VelocityEngineFactoryBean();
        factoryBean.setResourceLoaderPath("classpath:/velocity/");
        factoryBean.setPreferFileSystemAccess(false);
        return factoryBean.createVelocityEngine();
    }
}

