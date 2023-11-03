package com.marjane.services;

import com.marjane.Repositories.UserRepository;
import com.marjane.exceptions.ResourceNotCreatedException;
import com.marjane.jwt.JwtService;
import com.marjane.models.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final JwtService jwtService;

    /**
     *
     * @param user {@link Person} that should be registered
     */
    public void registerUser(Person user) {
//        var matches = thisNaturalIdExists(user);
//        if (matches.size() != 0)
//            throw new ResourceNotCreatedException(matches);

        Optional<Person> newUser = Optional.empty();

        try {
            newUser = Optional.of(repository.saveAndFlush(user));
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (newUser.isEmpty())
            throw new ResourceNotCreatedException("Could not create this user");
    }
}