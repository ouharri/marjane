package com.marjane.controllers;

import com.marjane.Enums.Access;
import com.marjane.Repositories.UserRepository;
import com.marjane.dto.AuthEntity;
import com.marjane.dto.LoginForm;
import com.marjane.dto.RegistrationForm;
import com.marjane.exceptions.ResourceNotCreatedException;
import com.marjane.exceptions.ResourceNotFoundException;
import com.marjane.jwt.JwtService;
import com.marjane.models.User;
import com.marjane.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Web controller that handles requests associated with {@link User}. <br>
 *
 * @author Ouharri Outman
 * @version 1.0
 * @see UserRepository
 * @see RegistrationForm
 * @see UserService
 */
@RestController
@Slf4j
@RequestMapping("/api/v2/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers new {@link User} and generates new JWT authentication token for him. <br><br>
     * HTTP method: {@code POST} <br>
     * Endpoint: /users/register <br>
     *
     * @param registrationForm a data transfer object for registering a {@link User}
     * @param bindingResult    a Hibernate Validator object which keeps all
     *                         validation violations.
     * @return JWT for registered user
     */
    @PostMapping("/register")
    public AuthEntity registerUser(@Valid @RequestBody RegistrationForm registrationForm,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        User userToCreate = registrationForm.toModel();

        userToCreate.setAccess(Access.USER);
        userToCreate.setHashPassword(passwordEncoder.encode(registrationForm.getPassword()));

        userService.registerUser(userToCreate);

        return new AuthEntity(jwtService.createToken(userToCreate), userToCreate.getUserId(), Access.USER.name());
    }

    /**
     * Receives {@link LoginForm} from a client and authenticates user by provided credentials.
     * If a user exists, generates JWT token for them.
     *
     * @param loginForm     objects with user credentials
     * @param bindingResult a Hibernate Validator object which keeps all validation violations
     * @return JWT token for authenticated user
     */
    @PostMapping("/login")
    public AuthEntity loginUser(@Valid @RequestBody LoginForm loginForm,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new IllegalArgumentException("Invalid login form");
        }

        Optional<User> userOptional = userService.getUser(loginForm.getEmail());

        if (userOptional.isEmpty() || !passwordEncoder.matches(loginForm.getPassword(), userOptional.get().getHashPassword())) {
            throw new ResourceNotFoundException("Invalid credentials");
        }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginForm.getEmail(),
                            loginForm.getPassword()
                    )
            );
        } catch (BadCredentialsException ex) {
            log.error("Authentication failed for user: " + loginForm.getEmail(), ex);
            throw new ResourceNotFoundException("Invalid credentials");
        } catch (AuthenticationException ex) {
            log.error("Authentication failed for user: " + loginForm.getEmail(), ex);
            throw new ResourceNotFoundException("Authentication failed");
        }

        User authenticatedUser = userOptional.get();
        String jwtToken = jwtService.createToken(authenticatedUser);

        return new AuthEntity(jwtToken, authenticatedUser.getUserId(), authenticatedUser.getAccess().name());
    }

    @PostMapping("/add-admin")
    public AuthEntity registerAdmin(@Valid @RequestBody RegistrationForm registrationForm,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ResourceNotCreatedException(bindingResult);

        User userToCreate = registrationForm.toModel();

        userToCreate.setAccess(Access.ADMINISTRATOR);
        userToCreate.setHashPassword(passwordEncoder.encode(registrationForm.getPassword()));

        userService.registerAdmin(userToCreate);

        return new AuthEntity(jwtService.createToken(userToCreate), userToCreate.getUserId(), Access.USER.name());
    }

}
