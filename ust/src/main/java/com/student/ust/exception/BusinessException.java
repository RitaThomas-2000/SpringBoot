package com.student.ust.exception;

/**
 * The type Business exception.
 */
public class BusinessException extends RuntimeException{
    /**
     * Instantiates a new Business exception.
     *
     * @param invalid_email_format the invalid email format
     */
    public BusinessException(String invalid_email_format){
        super(invalid_email_format);
    }
}
