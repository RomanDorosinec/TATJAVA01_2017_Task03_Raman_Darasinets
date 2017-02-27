package com.epam.task3.service.exception;

/**
 * Class of errors of Service layer.
 */
public class ServiceException extends Exception {
    public ServiceException() {
        super();
    }

    public ServiceException(Exception e) {
        super(e);
    }
}
