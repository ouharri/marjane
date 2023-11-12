package com.marjane.services;

import com.marjane.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void testLoadUserByUsername_UserFound() {
        // Arrange
        String email = "test@example.com";
        // create a UserDetails object or mock it based on your actual implementation
        User userDetails = mock(User.class);

        when(userService.getUser(email)).thenReturn(Optional.of(userDetails));

        // Act
        UserDetails result = userDetailsService.loadUserByUsername(email);

        // Assert
        assertEquals(userDetails, result);
        verify(userService, times(1)).getUser(email);
    }

    @Test
    void testLoadUserByUsername_UserNotFound() {
        // Arrange
        String email = "nonexistent@example.com";
        when(userService.getUser(email)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(email));
        verify(userService, times(1)).getUser(email);
    }
}
