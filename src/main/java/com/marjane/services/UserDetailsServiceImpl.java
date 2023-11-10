package com.marjane.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * An implementation of {@link UserDetailsService} interface that extracts
 * user data by its email (username) from a database and wraps it
 * in the {@link UserDetails} object.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var users = userService.getUser(email);
        if (users.isEmpty())
            throw new UsernameNotFoundException(String.format("There is no user with phone number %s", email));
        return users.get();
    }
}