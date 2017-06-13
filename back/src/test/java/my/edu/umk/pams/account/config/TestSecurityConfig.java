package my.edu.umk.pams.account.config;

import my.edu.umk.pams.account.security.integration.AcAutoLoginAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.SystemWideSaltSource;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 */
@TestConfiguration
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
