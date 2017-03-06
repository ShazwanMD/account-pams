package my.edu.umk.pams.account.config;

import my.edu.umk.pams.account.security.integration.AdAutoLoginAuthenticationProvider;
import my.edu.umk.pams.account.security.integration.jwt.RestAuthenticationEntryPoint;
import my.edu.umk.pams.account.security.integration.jwt.filter.JsonUsernamePasswordAuthenticationFilter;
import my.edu.umk.pams.account.security.integration.jwt.filter.JwtAuthenticationFilter;
import my.edu.umk.pams.account.security.integration.jwt.handler.JwtAuthenticationFailureHandler;
import my.edu.umk.pams.account.security.integration.jwt.handler.JwtAuthenticationSuccessHandler;
import my.edu.umk.pams.account.security.integration.jwt.provider.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.encoding.PlaintextPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.ServletException;

/**
 * http://spring.io/blog/2013/07/03/spring-security-java-config-preview-web-security/
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AdAutoLoginAuthenticationProvider autoLoginAuthenticationProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private Environment env;

    @Autowired(required = false)
    @Qualifier(value = "userDetailService")
    private UserDetailsService userDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers(HttpMethod.GET, "/index.html")
                .antMatchers(HttpMethod.GET, "/**.ico")
                .antMatchers(HttpMethod.GET, "/**.woff")
                .antMatchers(HttpMethod.GET, "/**.woff2")
                .antMatchers(HttpMethod.GET, "/**.js")
                .antMatchers(HttpMethod.GET, "/**.css")
                .antMatchers(HttpMethod.GET, "/assets/**")
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
        ;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .cors()
                .configurationSource(corsConfigurationSource())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/common/**").hasRole("USER")
                .antMatchers("/api/dashboard/**").hasRole("USER")
                .antMatchers("/api/identity/**").hasRole("USER")
                .antMatchers("/api/ledger/**").hasRole("USER")
                .antMatchers("/api/payable/**").hasRole("USER")
                .antMatchers("/api/receivable/**").hasRole("USER")
                .antMatchers("/api/billing/**").hasRole("USER")
                .antMatchers("/api/disbursement/**").hasRole("USER")
                .antMatchers("/download/**").hasRole("USER")
                .antMatchers("/login").permitAll()
                .anyRequest().permitAll()
                .and()
                .addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint());
    }

    @Bean
    public CorsFilter corsFilter() throws ServletException {
        CorsFilter corsFilter = new CorsFilter(corsConfigurationSource());
        corsFilter.afterPropertiesSet();
        return corsFilter;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        return httpServletRequest -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedMethod("POST");
            corsConfiguration.addAllowedMethod("GET");
            corsConfiguration.addAllowedMethod("PUT");
            corsConfiguration.addAllowedMethod("OPTIONS");
            corsConfiguration.addAllowedMethod("HEAD");
            corsConfiguration.addAllowedOrigin("*");
            corsConfiguration.addAllowedHeader("Content-Type");
            corsConfiguration.addAllowedHeader("Authorization");
            return corsConfiguration;
        };
    }

    @Bean
    public RestAuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.authenticationProvider(jwtAuthenticationProvider)
                .userDetailsService(userDetailService)
                .passwordEncoder(new PlaintextPasswordEncoder());
        builder.authenticationProvider(autoLoginAuthenticationProvider)  // auto login
                .userDetailsService(userDetailService)
                .passwordEncoder(new PlaintextPasswordEncoder());
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter();
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationFilter;
    }

    public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter("/api/authentication/login**");
        filter.setAuthenticationFailureHandler(jwtAuthenticationFailureHandler());
        filter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler());
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.afterPropertiesSet();
        return filter;
    }

    @Bean
    public JwtAuthenticationSuccessHandler jwtAuthenticationSuccessHandler() {
        return new JwtAuthenticationSuccessHandler();
    }

    @Bean
    public JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler() {
        return new JwtAuthenticationFailureHandler();
    }

}
