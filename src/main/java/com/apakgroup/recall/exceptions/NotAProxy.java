package com.apakgroup.recall.exceptions;


public class NotAProxy extends RuntimeException {

    private static final long serialVersionUID = 8228033632708579150L;

    public NotAProxy() {
        super();
    }

    public NotAProxy(final String message) {
        super(message);
    }

    public NotAProxy(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotAProxy(final Throwable cause) {
        super(cause);
    }
}

