package com.marjane.services;

import com.marjane.Repositories.UserRepository;
import com.marjane.exceptions.ResourceNotCreatedException;
import com.marjane.jwt.JwtService;
import com.marjane.models.Person;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    /**
     * @param user {@link Person} that should be registered
     */
    public void registerUser(Person user) {
        var matches = thisNaturalIdExists(user);
        if (!matches.isEmpty())
            throw new ResourceNotCreatedException(matches);

        Person person = null;

        try {
            person = repository.save(user);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        assert person != null;
        if (person.getUserId() == null)
            throw new ResourceNotCreatedException("Could not create this user");
    }

    public Optional<Person> getUser(String key) {
        return repository.findByPersonalInfoPhoneNumberOrPersonalInfoEmail(key, key);
    }

    /**
     * Checks if the phone number or the email of object is already used in existing {@link Person} object.
     *
     * @param user an object to check
     * @return a {@link Map} that contains decision about uniqueness of the phone number
     * and the email of specified {@link Person}
     */
    private Map<String, String> thisNaturalIdExists(Person user) {
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