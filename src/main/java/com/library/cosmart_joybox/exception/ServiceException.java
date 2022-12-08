package com.library.cosmart_joybox.exception;

public class ServiceException extends Exception{
    public ServiceException(String message, Throwable err) {
        super(message, err);
    }

    public ServiceException(String message) {
        super(message);
    }
}
