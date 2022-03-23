package org.matusikl.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.matusikl.controlleradvice.CustomAccessDeniedHandler;
import org.matusikl.controlleradvice.CustomAuthenticationEntryPoint;
import org.matusikl.filter.CustomAuthenticationFilter;
import org.matusikl.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static org.springframework.http.HttpMethod.GET;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan(basePackages = {"org.matusikl.service", "org.matusikl.controller", "org.matusikl.config", "org.matusikl.controlleradvice"})
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomSecurityConfig(UserDetailsService userDetailsService,
                                CustomAuthenticationEntryPoint authenticationEntryPoint,
                                CustomAccessDeniedHandler accessDeniedHandler,
                                ObjectMapper objectMapper){
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean(), objectMapper);
        customAuthenticationFilter.setFilterProcessesUrl("/management-system/login");
        http.csrf().disable();
        http.formLogin().loginProcessingUrl("/management-system/login1")/*.defaultSuccessUrl("/management-system/swagger-ui.html")*/;
        http.authorizeRequests().antMatchers("/management-system/login/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/management-system/employees/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/management-system/roles/**").hasAnyAuthority("ROLE_DIRECTOR");
        http.authorizeRequests().anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(objectMapper), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
