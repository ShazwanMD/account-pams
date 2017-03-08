package my.edu.umk.pams.account.config;

import my.edu.umk.pams.account.security.integration.AcAutoLoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

/**
 */
@Configuration
public class TestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AcAutoLoginAuthenticationProvider autoLoginAuthenticationProvider;

    @Autowired(required = false)
    @Qualifier(value = "userDetailService")
    private UserDetailsService userDetailService;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        SystemWideSaltSource saltSource = new SystemWideSaltSource();
        saltSource.setSystemWideSalt("cx{}==>");
        provider.setSaltSource(saltSource);
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(new PlaintextPasswordEncoder());
        builder.authenticationProvider(provider);  // auto login
        builder.authenticationProvider(autoLoginAuthenticationProvider)  // auto login
                .userDetailsService(userDetailService)
                .passwordEncoder(new PlaintextPasswordEncoder());
    }

}
