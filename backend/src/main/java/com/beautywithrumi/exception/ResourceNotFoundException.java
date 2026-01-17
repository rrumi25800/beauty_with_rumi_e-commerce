package com.beautywithrumi.exception;

/**
 * Custom exception for resource not found scenarios
 * 
 * Demonstrates OOP principles:
 * - Inheritance: Extends RuntimeException
 * - Encapsulation: Wraps error information
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
