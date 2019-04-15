package com.example.oauthexample.configure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class UserDetailServiceConfigure {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Bean
    UserDetailsService userDetailsService(){
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails ud1 =  User.withUsername("marissa").password(passwordEncoder.encode("koala")).roles("USER").build();
        UserDetails ud2 =  User.withUsername("paul").password(passwordEncoder.encode("emu")).roles("USER").build();
        manager.createUser(ud1);
        manager.createUser(ud2);
        return manager;
    }
}
