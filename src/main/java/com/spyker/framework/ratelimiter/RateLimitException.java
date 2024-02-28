package com.spyker.framework.ratelimiter;

public class RateLimitException extends RuntimeException {

    public RateLimitException() {
        super();
    }

    /**
     * Constructs a new runtime exception with the specified detail message. The cause is not
     * initialized, and may subsequently be initialized by a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for later retrieval by the
     *     {@link #getMessage()} method.
     */
    public RateLimitException(String message) {
        super(message);
    }
}