package com.marjane.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * An implementation of {@link UserDetailsService} interface that extracts
 * user data by its phone number (username) from a database and wraps it
 * in the {@link UserDetails} object.
 *
 * @author Maksym Panov
 * @version 1.0
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
//    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return null;
    }
}