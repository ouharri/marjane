package com.marjane.services;

import com.marjane.Repositories.UserRepository;
import com.marjane.exceptions.ResourceNotCreatedException;
import com.marjane.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Service class for managing operations related to users.
 * Handles communication between the controller and the repository for User entities.
 *
 * @version 1.0
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * Register a new user.
     *
     * @param user The user to be registered.
     */
    public void registerUser(User user) {
        addUser(user);
    }

    /**
     * Get a user by phone number or email.
     *
     * @param key The phone number or email of the user.
     * @return Optional containing the user if found, otherwise empty.
     */
    public Optional<User> getUser(String key) {
        return repository.findByPersonalInfoPhoneNumberOrPersonalInfoEmail(key, key);
    }

    /**
     * Register a new admin user. Requires SUPER_ADMINISTRATOR authority.
     *
     * @param user The admin user to be registered.
     */
    @PreAuthorize("hasAuthority('SUPER_ADMINISTRATOR')")
    public void registerAdmin(User user) {
        addUser(user);
    }

    private void addUser(User user) {
        var matches = thisNaturalIdExists(user);
        if (!matches.isEmpty())
            throw new ResourceNotCreatedException(matches);

        User person = null;

        try {
            person = repository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        assert person != null;
        if (person.getUserId() == null)
            throw new ResourceNotCreatedException("Could not create this user");
    }

    /**
     * Checks if the phone number or the email of the object is already used in an existing {@link User} object.
     *
     * @param user The user object to check.
     * @return A {@link Map} that contains decisions about the uniqueness of the phone number
     * and the email of the specified {@link User}.
     */
    private Map<String, String> thisNaturalIdExists(User user) {
        Map<String, String> matches = new HashMap<>();

        try {
            var phoneNumberMatch = repository.findByPersonalInfoPhoneNumber(user.getPersonalInfo().getPhoneNumber());
            if (phoneNumberMatch.isPresent())
                matches.put("phoneNumber", "User with this phone number already exists");
        } catch (Exception ignored) {
        }

        try {
            var emailMatch = repository.findByPersonalInfoEmail(user.getPersonalInfo().getEmail());
            if (emailMatch.isPresent())
                matches.put("email", "User with this email already exists");
        } catch (Exception ignored) {
        }

        return matches;
    }

    /**
     * Get a user by ID.
     *
     * @param id The ID of the user.
     * @return The user if found, otherwise throw ResourceNotCreatedException.
     */
    public User getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotCreatedException("Could not find this user"));
    }
}