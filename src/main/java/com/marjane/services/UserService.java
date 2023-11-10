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

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * @param user {@link User} that should be registered
     */
    public void registerUser(User user) {
        addUser(user);
    }

    public Optional<User> getUser(String key) {
        return repository.findByPersonalInfoPhoneNumberOrPersonalInfoEmail(key, key);
    }


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
     * Checks if the phone number or the email of object is already used in existing {@link User} object.
     *
     * @param user an object to check
     * @return a {@link Map} that contains decision about uniqueness of the phone number
     * and the email of specified {@link User}
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


}