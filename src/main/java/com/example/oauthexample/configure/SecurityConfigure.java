package com.example.oauthexample.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(passwordEncoder)
//                .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
//                .and()
//                .withUser("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");


        String pwd = passwordEncoder.encode("koala");
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("marissa").password(pwd).roles("USER")
                .and().
                withUser("paul").password(passwordEncoder.encode("emu")).roles("USER");


        System.out.println(pwd);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login.jsp").permitAll()
                .anyRequest().hasRole("USER")
                .and().exceptionHandling().accessDeniedPage("/login.jsp?authorization_error=true")
                .and()
                // TODO: put CSRF protection back into this endpoint
                .csrf()
                .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize"))
                .disable()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login.jsp")
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .failureUrl("/login.jsp?authentication_error=true")
                .loginPage("/login.jsp");
    }
}