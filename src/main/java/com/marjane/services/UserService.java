package com.marjane.services;

import com.marjane.libs.RepositoryI;
import com.marjane.Repositories.UserRepository;
import com.marjane.exceptions.ResourceNotCreatedException;
import com.marjane.jwt.JwtService;
import com.marjane.libs.RepositoryImplementation;
import com.marjane.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final JwtService jwtService;

    /**
     * Uses {@link RepositoryI} implementation to register and save new {@link User} in the data storage. <br><br>
     * Re-throws a {@link ResourceNotCreatedException} if {@link RepositoryI} object throws an exception.
     *
     * @param user {@link User} that should be registered
     */
    public void registerUser(User user) {
//        var matches = thisNaturalIdExists(user);
//        if (matches.size() != 0)
//            throw new ResourceNotCreatedException(matches);

        Optional<User> newUser = Optional.empty();

        try {
            newUser = repository.create(user);
        } catch(Exception e) {
            e.printStackTrace();
        }

        if (newUser.isEmpty())
            throw new ResourceNotCreatedException("Could not create this user");
    }
}
