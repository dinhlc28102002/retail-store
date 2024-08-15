package com.rs.retailstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        UserDetails user = User.builder()
                .username("itkuiuser")
                .password("{bcrypt}$2a$10$6OUWBktH0OxGJlPoxhYPNu9XLHU4D1PvZOg/RnznuGxh352Snao36")
                .roles("USER")
                .build();

        UserDetails admin = User.builder()
                .username("itkuiadmin")
                .password("{bcrypt}$2a$12$69pgcnZXLsQGPH3Yhvu9HenyJuzvqrl6l8MBr3tWw9OcwaIXaIacm")
                .roles("USER","ADMIN")
                .build();

        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
        users.createUser(admin);
        return users;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
