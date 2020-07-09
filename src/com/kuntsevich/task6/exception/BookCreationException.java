package com.kuntsevich.task6.exception;

public class BookCreationException extends Exception {

    public BookCreationException() {
        super();
    }

    public BookCreationException(String message) {
        super(message);
    }

    public BookCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookCreationException(Throwable cause) {
        super(cause);
    }
}
