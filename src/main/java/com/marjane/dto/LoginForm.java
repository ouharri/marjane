package com.marjane.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

/**
 * DTO object which is used to transfer user credentials during authentication and authorization processes.
 *
 * @author Maksym Panov
 * @version 1.0
 */
@Getter
public class LoginForm {
    @NotEmpty(message = "Phone number must be present")
//    @Pattern(regexp = "0\\d{9}", message = "Phone number must match the format '0XXXXXXXXX'")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Password field should not be empty")
    private String password;
}