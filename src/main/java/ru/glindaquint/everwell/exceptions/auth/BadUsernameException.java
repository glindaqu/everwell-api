package ru.glindaquint.everwell.exceptions.auth;

public class BadUsernameException extends RuntimeException {
    public BadUsernameException() {
        super("User with given username already exists");
    }
}
