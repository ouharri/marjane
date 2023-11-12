package com.marjane.services;

import com.marjane.Repositories.UserRepository;
import com.marjane.exceptions.ResourceNotCreatedException;
import com.marjane.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;


    @Test
    void getById_UserExists_ReturnsUser() {
        // Arrange
        UUID userId = UUID.randomUUID();
        User user = new User();
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        User result = userService.getById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(user, result);
    }

    @Test
    void getById_UserDoesNotExist_ThrowsException() {
        // Arrange
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotCreatedException.class, () -> userService.getById(userId));
    }

    @Test
    void getUser_UserExists_ReturnsUser() {
        // Arrange
        String key = "test@example.com";
        User user = new User();
        when(userRepository.findByPersonalInfoPhoneNumberOrPersonalInfoEmail(key, key)).thenReturn(Optional.of(user));

        // Act
        Optional<User> result = userService.getUser(key);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void getUser_UserDoesNotExist_ReturnsEmpty() {
        // Arrange
        String key = "test@example.com";
        when(userRepository.findByPersonalInfoPhoneNumberOrPersonalInfoEmail(key, key)).thenReturn(Optional.empty());

        // Act
        Optional<User> result = userService.getUser(key);

        // Assert
        assertTrue(result.isEmpty());
    }
}