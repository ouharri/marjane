package com.marjane.exceptions;

import com.marjane.models.User;


/**
 * The parent for all exceptions, associated with resources, such as {@link User} etc.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
public class ResourceException extends RuntimeException {
    public ResourceException() {}

    public ResourceException(String message) {
        super(message);
    }
}
