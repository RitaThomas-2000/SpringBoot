package com.student.ust.exception;

/**
 * The type Invalid password exception.
 */
public class InvalidPasswordException extends BusinessException {
    /**
     * Instantiates a new Invalid password exception.
     */
    public InvalidPasswordException(){
        super("invalid password format");
    }
}
