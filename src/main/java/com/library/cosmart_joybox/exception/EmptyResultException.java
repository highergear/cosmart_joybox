package com.library.cosmart_joybox.exception;

public class EmptyResultException extends Exception {
    public EmptyResultException(String message, Throwable err) {
        super(message, err);
    }

    public EmptyResultException(String message) {
        super(message);
    }
}
