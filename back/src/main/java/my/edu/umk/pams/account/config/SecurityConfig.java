package my.edu.umk.pams.account.config;

import my.edu.umk.pams.account.security.integration.AcAutoLoginAuthenticationProvider;
import my.edu.umk.pams.account.security.integration.jwt.RestAuthenticationEntryPoint;
import my.edu.umk.pams.account.security.integration.jwt.filter.LoginAuthenticationFilter;
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
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AcAutoLoginAuthenticationProvider autoLoginAuthenticationProvider;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Autowired
    private Environment env;

    @Autowired(required = false)
    @Qualifier(value = "userDetailService")
    private UserDetailsService userDetailService;

    private static String API_URL = "/api/**";
    private static String LOGIN_URL = "/api/authentication/login**";

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/api/integration/**")
                .antMatchers("/servlet/**")
                .antMatchers(HttpMethod.GET, "/index.html")
                .antMatchers(HttpMethod.GET, "/**.ico")
                .antMatchers(HttpMethod.GET, "/**.woff")
                .antMatchers(HttpMethod.GET, "/**.woff2")
                .antMatchers(HttpMethod.GET, "/**.js")
                .antMatchers(HttpMethod.GET, "/**.css")
                .antMatchers(HttpMethod.GET, "/assets/**")
                .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources", "/configuration/security", "/swagger-ui.html", "/webjars/**");
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
                .antMatchers("/api/system/**").hasRole("USER")
                .antMatchers("/api/identity/**").hasRole("USER")
                .antMatchers("/api/account/**").hasRole("USER")
                .antMatchers("/api/billing/**").hasRole("USER")
                .antMatchers("/api/financialaid/**").hasRole("USER")
                .antMatchers("/api/marketing/**").hasRole("USER")
                .antMatchers("/download/**").hasRole("USER")
                .and()
                .addFilterAfter(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(loginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
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
            corsConfiguration.addAllowedMethod("DELETE");
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

    private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(API_URL);
        jwtAuthenticationFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationFilter;
    }

    private LoginAuthenticationFilter loginAuthenticationFilter() throws Exception {
        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter(LOGIN_URL);
        loginAuthenticationFilter.setAuthenticationFailureHandler(jwtAuthenticationFailureHandler());
        loginAuthenticationFilter.setAuthenticationSuccessHandler(jwtAuthenticationSuccessHandler());
        loginAuthenticationFilter.setAuthenticationManager(authenticationManagerBean());
        loginAuthenticationFilter.afterPropertiesSet();
        return loginAuthenticationFilter;
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

