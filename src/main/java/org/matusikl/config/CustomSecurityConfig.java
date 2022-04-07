package org.matusikl.config;

import org.matusikl.controlleradvice.CustomAccessDeniedHandler;
import org.matusikl.controlleradvice.CustomAuthenticationEntryPoint;
import org.matusikl.filter.CustomAuthorizationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.PATCH;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@ComponentScan(basePackages = {"org.matusikl.service", "org.matusikl.controller", "org.matusikl.config", "org.matusikl.controlleradvice", "org.matusikl.filter"})
public class CustomSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final CustomAuthenticationEntryPoint authenticationEntryPoint;
    private final CustomAccessDeniedHandler accessDeniedHandler;
    private final CustomAuthorizationFilter customAuthorizationFilter;

    @Autowired
    public CustomSecurityConfig(UserDetailsService userDetailsService,
                                CustomAuthenticationEntryPoint authenticationEntryPoint,
                                CustomAccessDeniedHandler accessDeniedHandler,
                                CustomAuthorizationFilter customAuthorizationFilter){
        this.userDetailsService = userDetailsService;
        this.authenticationEntryPoint = authenticationEntryPoint;
        this.accessDeniedHandler = accessDeniedHandler;
        this.customAuthorizationFilter = customAuthorizationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().disable();
        http.authorizeRequests().antMatchers("/management-system/v3/api-docs/**",
                "/management-system/tokens/**",
                "/management-system/configuration/ui",
                "/management-system/swagger-resources/**",
                "/management-system/configuration/security",
                "/management-system/swagger-ui.html",
                "/management-system/swagger-ui/**",
                "/management-system/webjars/**").permitAll();
        http.authorizeRequests().antMatchers(GET, "/management-system/employees/**", "/management-system/tasks/**", "/management-system/accounts/**").hasAnyRole("OFFICE_WORKER", "MANAGER", "ADMIN", "DIRECTOR");
        http.authorizeRequests().antMatchers(GET, "/management-system/jobs/**", "/management-system/roles/**", "/management-system/laptops/**").hasAnyRole("MANAGER", "ADMIN", "DIRECTOR");
        http.authorizeRequests().antMatchers(PATCH, "/management-system/employees/**").hasAnyRole("ADMIN", "MANAGER");
        http.authorizeRequests().antMatchers(DELETE, "/management-system/employees/**", "/management-system/tasks/**").hasAnyRole("ADMIN", "MANAGER");
        http.authorizeRequests().antMatchers(DELETE, "/management-system/accounts/**", "/management-system/jobs/**", "/management-system/roles/**", "/management-system/laptops/**").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(POST, "/management-system/tasks/**").hasAnyRole("MANAGER", "ADMIN", "DIRECTOR");
        http.authorizeRequests().antMatchers(POST, "/management-system/employees/**").hasAnyRole("ADMIN", "DIRECTOR");
        http.authorizeRequests().antMatchers(POST, "/management-system/laptops/**", "/management-system/jobs/**", "/management-system/roles/**", "/management-system/accounts/**").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers(PUT, "/management-system/employees/**").hasAnyRole("MANAGER", "ADMIN", "DIRECTOR");
        http.authorizeRequests().antMatchers(PUT, "/management-system/tasks/**", "/management-system/accounts/**").hasAnyRole("OFFICE_WORKER", "MANAGER", "ADMIN", "DIRECTOR");
        http.authorizeRequests().antMatchers(PUT,  "/management-system/jobs/**", "/management-system/roles/**", "/management-system/laptops/**").hasAnyRole("ADMIN");
        http.authorizeRequests().anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
                .and().exceptionHandling().accessDeniedHandler(accessDeniedHandler)
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(customAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    protected PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
