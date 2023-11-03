package com.marjane.exceptions;

/**
 * This class is used when an attempt to find an entity has thrown an exception.
 *
 * @author Ouharri Outman
 * @version 1.0
 */
public class ResourceNotFoundException extends ResourceException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
