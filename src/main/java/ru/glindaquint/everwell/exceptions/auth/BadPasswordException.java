package ru.glindaquint.everwell.exceptions.auth;

public class BadPasswordException extends RuntimeException {
    public BadPasswordException() {
        super("Password must have 5 symbols at least");
    }
}
