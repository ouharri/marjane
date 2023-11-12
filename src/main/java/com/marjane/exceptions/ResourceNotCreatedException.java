package com.marjane.exceptions;

import com.marjane.models.User;
import lombok.Getter;
import org.springframework.validation.BindingResult;

import java.util.Map;

/**
 * This class is used when an attempt of creating an entity is failed.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
@Getter
public class ResourceNotCreatedException extends ResourceException {
    private BindingResult bindingResult;
    private Map<String, String> matches;

    public ResourceNotCreatedException(String message) {
        super(message);
    }

    /**
     * This constructor is used when the exceptional situation was <br>
     * caused by validation violations.
     *
     * @param bindingResult Hibernate Validation object that wraps validation violations
     */
    public ResourceNotCreatedException(BindingResult bindingResult) {
        super();
        this.bindingResult = bindingResult;
    }

    /**
     * This constructor is used when the exceptional situation was <br>
     * caused by uniqueness violation (e.g. there is an attempt of registration of a <br>
     * {@link User} with phone number that already exists).
     *
     * @param matches {@link Map} of fields that violate uniqueness constraint
     */
    public ResourceNotCreatedException(Map<String, String> matches) {
        super();
        this.matches = matches;
    }

    public Map<String, String> getErrorMessages() {
        return this.matches;
    }
}
