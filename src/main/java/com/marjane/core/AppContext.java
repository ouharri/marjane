package com.marjane.core;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import lombok.extern.slf4j.Slf4j;

/**
 * This class provides beans for the application context and seeds the database.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Configuration
@EnableWebMvc
@RequiredArgsConstructor
@ComponentScan(basePackages = { "com.marjane" })
@Slf4j
public class AppContext {
    private final UserDetailsService userDetailsService;

    /**
     * Provides an authentication provider for the application.
     *
     * @return The authentication provider.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        log.info("Creating an authentication provider bean.");
        var provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    /**
     * Provides a password encoder for the application.
     *
     * @return The password encoder.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        log.info("Creating a password encoder bean.");
        return new BCryptPasswordEncoder();
    }

    /**
     * Provides an authentication manager for the application.
     *
     * @param configuration The authentication configuration.
     * @return The authentication manager.
     * @throws Exception In case of an error while configuring the authentication manager.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        log.info("Creating an authentication manager bean.");
        return configuration.getAuthenticationManager();
    }
}