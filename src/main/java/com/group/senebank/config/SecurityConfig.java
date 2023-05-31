package com.group.senebank.config;

import com.group.senebank.security.JwtAuthenticationFilter;
import com.group.senebank.security.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http = http.csrf().disable();

        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> response.sendError(
                                HttpServletResponse.SC_UNAUTHORIZED,
                                ex.getMessage()
                        )
                )
                .and();

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .authorizeRequests()
                .antMatchers("/v2/api-docs/**").permitAll()
                .antMatchers("/swagger-ui/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/user/register").permitAll()
                .antMatchers("/user/authorize").permitAll()
                .antMatchers("/user/{userId}").hasAnyAuthority(Role.ADMIN, Role.USER)
                .antMatchers("/transaction/**").hasAnyAuthority(Role.ADMIN, Role.USER)
                .antMatchers("/account/**").hasAnyAuthority(Role.ADMIN, Role.USER)
                .antMatchers("/admin/**").hasAuthority(Role.ADMIN)
                .anyRequest().authenticated()
                .and();
    }
}
