package com.marjane.exceptions;

import com.marjane.models.Person;


/**
 * The parent for all exceptions, associated with resources, such as {@link Person} etc.
 *
 * @author Maksym Panov
 * @version 1.0
 */
public class ResourceException extends RuntimeException {
    public ResourceException() {}

    public ResourceException(String message) {
        super(message);
    }
}
