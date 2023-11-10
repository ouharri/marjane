package com.marjane.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

/**
 * DTO object which is used to transfer user credentials during authentication and authorization processes.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Getter
public class LoginForm {
    @Email(message = "Email was not provided")
    @NotEmpty(message = "email must be present")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Password field should not be empty")
    private String password;
}