package com.group.senebank.config;

import com.group.senebank.security.JwtAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        {
//        http
//                .authorizeHttpRequests((authz) -> authz
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .anyRequest().authenticated()
//                        .and().formLogin()
//                        .loginPage("/login")
//                        .usernameParameter("email")
//                        .permitAll()
//                        .and()
//                        .rememberMe()
//                        .key("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
//                        .and()
//                        .logout().permitAll()
//                        .hasAnyAuthority("ADMIN", "USER")
//                        .sessionManagement()
//                        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//                );
            http.csrf().disable().authorizeRequests().anyRequest().permitAll();

//        http.authorizeHttpRequests().antMatchers("/login").permitAll()
//                .antMatchers("/admin/**").hasAuthority("Admin")
//                .hasAnyAuthority("Admin", "User")
//                .anyRequest().authenticated()
//                .and().formLogin()
//                .loginPage("/login")
//                .usernameParameter("email")
//                .permitAll()
//                .and()
//                .rememberMe().key("AbcdEfghIjklmNopQrsTuvXyz_0123456789")
//                .and()
//                .logout().permitAll();
//
//        http.headers().frameOptions().sameOrigin();
//
        }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.inMemoryAuthentication().withUser("user")
//                .password(passwordEncoder().encode("password")).roles("USER");
//    }

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
    }
}
