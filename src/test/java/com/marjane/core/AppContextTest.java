package com.marjane.core;

import com.marjane.core.AppContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class AppContextTest {

    @InjectMocks
    private AppContext appContext;

    @Test
    void authenticationProviderBean() {
        AuthenticationProvider authenticationProvider = appContext.authenticationProvider();
        assertNotNull(authenticationProvider);
    }

    @Test
    void passwordEncoderBean() {
        PasswordEncoder passwordEncoder = appContext.passwordEncoder();
        assertNotNull(passwordEncoder);
    }

}

