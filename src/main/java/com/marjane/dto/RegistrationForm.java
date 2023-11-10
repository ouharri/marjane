package com.marjane.dto;

import com.marjane.models.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Data transfer class that is used to transfer data, needed to register new {@link User} entities.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationForm {
    @NotNull(message = "FirstName must be present")
    @Size(min = 1, message = "Firstname cannot be empty")
    @Size(max = 30, message = "Firstname is too long")
    private String firstname;

    @Size(max = 30, message = "Firstname is too long")
    private String lastname;

    @NotNull(message = "Phone number must be present")
    @Pattern(regexp = "0\\d{9}", message = "Phone number must match the format '0XXXXXXXXX'")
    @Column(unique = true)
    private String phoneNumber;

    @Email(message = "Email was not provided")
    @Size(max = 80, message = "Email is too long")
    @Column(unique = true)
    private String email;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, message = "Password is too short")
    private String password;

    /**
     * Converts current {@link RegistrationForm} object to an instance of {@link User}.
     *
     * @return a {@link User} instance
     */
    public User toModel() {
        var u = new User();
        var pi = new User.PersonalInfo();
        pi.setFirstname(firstname);
        pi.setLastname(lastname);
        pi.setPhoneNumber(phoneNumber);
        pi.setEmail(email);

        u.setPersonalInfo(pi);
        u.setHashPassword(password);
        return u;
    }
}
