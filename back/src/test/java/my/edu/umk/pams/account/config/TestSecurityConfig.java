package my.edu.umk.pams.account.config;

import my.edu.umk.pams.account.security.integration.AdAutoLoginAuthenticationProvider;
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
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 */
@Configuration
public class TestSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdAutoLoginAuthenticationProvider autoLoginAuthenticationProvider;

    @Autowired
    private Environment env;

    @Autowired(required = false)
    @Qualifier(value = "userDetailService")
    private UserDetailsService userDetailService;

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .and()
                .formLogin()
                .loginProcessingUrl(env.getProperty("security.processing.url"))
                .defaultSuccessUrl(env.getProperty("security.success.url"))
                .failureUrl(env.getProperty("security.failure.url"))
                .loginPage(env.getProperty("security.login.page"))
                .permitAll()
                .and()
                .logout()
                .logoutUrl(env.getProperty("security.logout.url"))
                .logoutSuccessUrl(env.getProperty("security.logout.success.url"))
                .invalidateHttpSession(true).and()
                .sessionManagement().maximumSessions(1).and().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/*").permitAll()
                .antMatchers("/gxt/**").permitAll()
                .antMatchers("/canang/**").hasRole("USER")
                .antMatchers("/secure/**").hasRole("USER")
                .antMatchers("/rest/**").permitAll()
                .anyRequest().permitAll();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        SystemWideSaltSource saltSource = new SystemWideSaltSource();
        saltSource.setSystemWideSalt("");
        provider.setSaltSource(saltSource);
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(new PlaintextPasswordEncoder());
        builder.authenticationProvider(provider);  // auto login
        builder.authenticationProvider(autoLoginAuthenticationProvider)  // auto login
               .userDetailsService(userDetailService)
               .passwordEncoder(new ShaPasswordEncoder());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

}
