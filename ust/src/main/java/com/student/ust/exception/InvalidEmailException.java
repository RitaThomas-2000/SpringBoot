package com.student.ust.exception;

/**
 * The type Invalid email exception.
 */
public class InvalidEmailException extends BusinessException {
    /**
     * Instantiates a new Invalid email exception.
     */
    public InvalidEmailException(){
        super("Invalid email format");
    }
}
