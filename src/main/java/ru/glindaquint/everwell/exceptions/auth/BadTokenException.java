package ru.glindaquint.everwell.exceptions.auth;

public class BadTokenException extends RuntimeException {
    public BadTokenException() {
        super("Invalid token");
    }
}
