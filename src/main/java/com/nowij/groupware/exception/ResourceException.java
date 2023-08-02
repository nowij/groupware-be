package com.nowij.groupware.exception;

public abstract class ResourceException extends RuntimeException {
    public ResourceException(String message) {
        super(message);
    }

    public ResourceException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();
}
